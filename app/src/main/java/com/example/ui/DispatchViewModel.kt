package com.example.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.HurifixUser
import com.example.data.model.JobStatus
import com.example.data.model.RankedExpert
import com.example.data.repository.DispatchRepository
import com.example.data.sync.SyncStatus
import com.example.util.LocationHelper
import com.example.util.WhatsAppHelper
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.regex.Pattern

enum class MainTab(val title: String) {
    CUSTOMER_ORDERS("Customer Orders"),
    EXPERTS("Experts Directory")
}

enum class CustomerSubTab(val title: String) {
    DISPATCH_ORDER("Dispatch Order"),
    ORDERS("Orders")
}

enum class OrderStatusTab(val statusName: String, val label: String) {
    PENDING(JobStatus.PENDING.name, "Pending"),
    PROCESSING(JobStatus.PROCESSING.name, "Processing"),
    COMPLETED(JobStatus.COMPLETED.name, "Completed"),
    CANCELLED(JobStatus.CANCELLED.name, "Cancelled")
}

data class CustomerFormState(
    val name: String = "",
    val phone: String = "",
    val serviceType: String = "",
    val issueDescription: String = "",
    val address: String = "",
    val latitude: Double = 28.5708,
    val longitude: Double = 77.3261,
    val hasValidLocation: Boolean = true,
    val rawLocationInput: String = "28.5708, 77.3261"
)

class DispatchViewModel(private val repository: DispatchRepository) : ViewModel() {

    val allExperts: StateFlow<List<ExpertEntity>> = repository.allExperts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allJobs: StateFlow<List<CustomerJobEntity>> = repository.allJobs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allCategories: StateFlow<List<ExpertCategoryEntity>> = repository.allCategories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val deletedJobs: StateFlow<List<CustomerJobEntity>> = repository.deletedJobs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val deletedExperts: StateFlow<List<ExpertEntity>> = repository.deletedExperts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // All Users for Admin Control Panel
    val allStaffUsers: StateFlow<List<HurifixUser>> = (repository.firestoreService?.getAllUsersFlow() ?: flowOf(emptyList()))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Sync state
    val syncStatus: StateFlow<SyncStatus> = (repository.syncManager?.syncStatus ?: flowOf(SyncStatus.IDLE))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SyncStatus.IDLE)

    val lastSyncTimestamp: StateFlow<Long> = (repository.syncManager?.lastSyncTimestamp ?: flowOf(System.currentTimeMillis()))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), System.currentTimeMillis())

    val unsyncedJobsCount: StateFlow<Int> = (repository.syncManager?.unsyncedJobsCount ?: flowOf(0))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val unsyncedExpertsCount: StateFlow<Int> = (repository.syncManager?.unsyncedExpertsCount ?: flowOf(0))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _customerForm = MutableStateFlow(CustomerFormState())
    val customerForm: StateFlow<CustomerFormState> = _customerForm.asStateFlow()

    private val _currentMainTab = MutableStateFlow(MainTab.CUSTOMER_ORDERS)
    val currentMainTab: StateFlow<MainTab> = _currentMainTab.asStateFlow()

    private val _currentCustomerSubTab = MutableStateFlow(CustomerSubTab.DISPATCH_ORDER)
    val currentCustomerSubTab: StateFlow<CustomerSubTab> = _currentCustomerSubTab.asStateFlow()

    private val _currentOrderStatusTab = MutableStateFlow(OrderStatusTab.PENDING)
    val currentOrderStatusTab: StateFlow<OrderStatusTab> = _currentOrderStatusTab.asStateFlow()

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

    private val _activeJobForNearestExperts = MutableStateFlow<CustomerJobEntity?>(null)
    val activeJobForNearestExperts: StateFlow<CustomerJobEntity?> = _activeJobForNearestExperts.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    // Realtime listener for active user session (detecting blocks / permission updates)
    private var userSessionListener: ListenerRegistration? = null

    init {
        viewModelScope.launch {
            try {
                repository.ensureDefaultCategoriesForCurrentUser()
                repository.purgeRecycleBinOlderThan30Days()
            } catch (_: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        userSessionListener?.remove()
    }

    fun onUserLoggedIn(
        phone: String,
        onSessionBlocked: () -> Unit = {},
        onPermissionsUpdated: (HurifixUser) -> Unit = {}
    ) {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        repository.setCurrentUser(cleanPhone)

        // Attach real-time Firestore session listener
        userSessionListener?.remove()
        userSessionListener = repository.firestoreService?.listenToUserSession(
            phone = cleanPhone,
            onUpdate = { user ->
                onPermissionsUpdated(user)
            },
            onBlocked = {
                onSessionBlocked()
            }
        )

        viewModelScope.launch {
            try {
                repository.ensureDefaultCategoriesForCurrentUser()
                repository.purgeRecycleBinOlderThan30Days()
                // Initial background sync
                repository.syncManager?.syncNow()
            } catch (_: Exception) {
            }
        }
        _customerForm.value = CustomerFormState()
        _activeJobForNearestExperts.value = null
        _currentMainTab.value = MainTab.CUSTOMER_ORDERS
        _currentCustomerSubTab.value = CustomerSubTab.DISPATCH_ORDER
        _currentOrderStatusTab.value = OrderStatusTab.PENDING
    }

    fun onUserLoggedOut() {
        userSessionListener?.remove()
        userSessionListener = null
        repository.setCurrentUser("")
        _customerForm.value = CustomerFormState()
        _activeJobForNearestExperts.value = null
        _currentMainTab.value = MainTab.CUSTOMER_ORDERS
        _currentCustomerSubTab.value = CustomerSubTab.DISPATCH_ORDER
        _currentOrderStatusTab.value = OrderStatusTab.PENDING
    }

    fun triggerManualSync(onFinished: ((Boolean, String) -> Unit)? = null) {
        viewModelScope.launch {
            val result = repository.syncManager?.syncNow()
            if (result != null && result.isSuccess) {
                val msg = result.getOrDefault("Sync complete!")
                _statusMessage.value = msg
                onFinished?.invoke(true, msg)
            } else {
                val err = result?.exceptionOrNull()?.localizedMessage ?: "Sync error"
                _statusMessage.value = "Sync failed: $err"
                onFinished?.invoke(false, err)
            }
        }
    }

    fun refreshAllData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.syncManager?.syncNow()
            _isRefreshing.value = false
            _statusMessage.value = "Data synchronized with cloud!"
        }
    }

    fun selectMainTab(tab: MainTab) {
        _currentMainTab.value = tab
    }

    fun selectCustomerSubTab(subTab: CustomerSubTab) {
        _currentCustomerSubTab.value = subTab
    }

    fun selectOrderStatusTab(statusTab: OrderStatusTab) {
        _currentOrderStatusTab.value = statusTab
    }

    fun clearStatusMessage() {
        _statusMessage.value = null
    }

    fun updateName(name: String) {
        _customerForm.value = _customerForm.value.copy(name = name)
    }

    fun updatePhone(phone: String) {
        _customerForm.value = _customerForm.value.copy(phone = phone)
    }

    fun updateServiceType(service: String) {
        _customerForm.value = _customerForm.value.copy(serviceType = service)
    }

    fun updateIssue(issue: String) {
        _customerForm.value = _customerForm.value.copy(issueDescription = issue)
    }

    fun updateAddress(address: String) {
        _customerForm.value = _customerForm.value.copy(address = address)
    }

    fun updateLocationInput(input: String) {
        val parsed = LocationHelper.parseCoordinatesFromText(input)
        if (parsed != null) {
            _customerForm.value = _customerForm.value.copy(
                rawLocationInput = input,
                latitude = parsed.first,
                longitude = parsed.second,
                hasValidLocation = true
            )
        } else {
            _customerForm.value = _customerForm.value.copy(
                rawLocationInput = input,
                hasValidLocation = false
            )
        }
    }

    fun setCoordinates(lat: Double, lng: Double, address: String? = null) {
        _customerForm.value = _customerForm.value.copy(
            latitude = lat,
            longitude = lng,
            rawLocationInput = "$lat, $lng",
            hasValidLocation = true,
            address = address ?: _customerForm.value.address
        )
    }

    fun fetchCurrentGps(context: Context) {
        LocationHelper.fetchCurrentLocation(
            context = context,
            onSuccess = { loc ->
                setCoordinates(loc.latitude, loc.longitude)
                _statusMessage.value = "GPS Location set: ${loc.latitude}, ${loc.longitude}"
            },
            onError = { err ->
                _statusMessage.value = err
            }
        )
    }

    fun parseAndFillFromWhatsAppText(rawText: String) {
        var detectedName = ""
        var detectedPhone = ""
        var detectedService = ""
        var detectedIssue = ""
        var detectedAddress = ""

        val phonePattern = Pattern.compile("(?:\\+91|91|0)?[6-9]\\d{9}")
        val phoneMatcher = phonePattern.matcher(rawText)
        if (phoneMatcher.find()) {
            val fullPhone = phoneMatcher.group()
            detectedPhone = fullPhone.replace(Regex("^(\\+91|91|0)"), "")
        }

        val parsedCoords = LocationHelper.parseCoordinatesFromText(rawText)

        val lower = rawText.lowercase()
        when {
            lower.contains("ac") || lower.contains("air conditioner") || lower.contains("cooling") ->
                detectedService = "AC Service & Repair"
            lower.contains("fan") || lower.contains("switch") || lower.contains("electric") || lower.contains("wiring") || lower.contains("light") ->
                detectedService = "Electrician"
            lower.contains("fridge") || lower.contains("refrigerator") ->
                detectedService = "Refrigerator Repair"
            lower.contains("plumb") || lower.contains("tap") || lower.contains("pipe") || lower.contains("leak") || lower.contains("tank") ->
                detectedService = "Plumber"
            lower.contains("wash") || lower.contains("machine") ->
                detectedService = "Washing Machine Repair"
        }

        val lines = rawText.lines().map { it.trim() }.filter { it.isNotBlank() }
        for (line in lines) {
            val lineLower = line.lowercase()
            if (lineLower.startsWith("name:") || lineLower.startsWith("customer:")) {
                detectedName = line.substringAfter(":").trim()
            } else if (lineLower.startsWith("phone:") || lineLower.startsWith("mobile:") || lineLower.startsWith("number:")) {
                if (detectedPhone.isBlank()) {
                    detectedPhone = line.substringAfter(":").trim().replace(Regex("[^0-9]"), "")
                }
            } else if (lineLower.startsWith("address:") || lineLower.startsWith("location:")) {
                detectedAddress = line.substringAfter(":").trim()
            } else if (lineLower.startsWith("problem:") || lineLower.startsWith("issue:") || lineLower.startsWith("work:")) {
                detectedIssue = line.substringAfter(":").trim()
            }
        }

        if (detectedIssue.isBlank()) {
            detectedIssue = rawText.take(120)
        }

        _customerForm.value = _customerForm.value.copy(
            name = if (detectedName.isNotBlank()) detectedName else _customerForm.value.name,
            phone = if (detectedPhone.isNotBlank()) detectedPhone else _customerForm.value.phone,
            serviceType = if (detectedService.isNotBlank()) detectedService else _customerForm.value.serviceType,
            issueDescription = detectedIssue,
            address = if (detectedAddress.isNotBlank()) detectedAddress else _customerForm.value.address,
            latitude = parsedCoords?.first ?: _customerForm.value.latitude,
            longitude = parsedCoords?.second ?: _customerForm.value.longitude,
            rawLocationInput = parsedCoords?.let { "${it.first}, ${it.second}" } ?: _customerForm.value.rawLocationInput,
            hasValidLocation = true
        )
        _statusMessage.value = "WhatsApp lead parsed successfully!"
    }

    /**
     * Saves new customer order with Duplicate Customer Check.
     * Specification #4: Before saving a new order, search Firestore & local DB to verify
     * if an order with the same customer phone number exists within the last 4 hours.
     * If found, display a warning toast and block duplicate creation.
     */
    fun saveCustomerOrder(
        status: JobStatus = JobStatus.PENDING,
        onDuplicateWarning: (String) -> Unit = {},
        onComplete: (CustomerJobEntity) -> Unit
    ) {
        val form = _customerForm.value
        val cleanPhone = form.phone.replace(Regex("[^0-9]"), "")

        viewModelScope.launch {
            if (cleanPhone.length >= 10) {
                val isDuplicate = repository.checkDuplicateCustomerOrder(cleanPhone)
                if (isDuplicate) {
                    val warning = "Duplicate Order Detected! An active order for customer ($cleanPhone) already exists within the last 4 hours."
                    _statusMessage.value = warning
                    onDuplicateWarning(warning)
                    return@launch
                }
            }

            val newJob = CustomerJobEntity(
                customerName = form.name.ifBlank { "Customer" },
                customerPhone = cleanPhone.ifBlank { form.phone },
                serviceType = form.serviceType.ifBlank { "General Repair" },
                issueDescription = form.issueDescription.ifBlank { "Service requested" },
                address = form.address.ifBlank { "Address not specified" },
                latitude = form.latitude,
                longitude = form.longitude,
                status = status.name,
                createdAt = System.currentTimeMillis(),
                last_updated = System.currentTimeMillis()
            )

            val id = repository.insertJob(newJob)
            val insertedJob = newJob.copy(id = id)
            _customerForm.value = CustomerFormState() // Reset form
            onComplete(insertedJob)
        }
    }

    fun openFindNearestExperts(job: CustomerJobEntity) {
        _activeJobForNearestExperts.value = job
    }

    fun closeFindNearestExperts() {
        _activeJobForNearestExperts.value = null
    }

    /**
     * Assigns selected expert to the given job using Firestore Real-Time Transaction Lock.
     * Specification #4: If two users attempt to assign a technician to the same order simultaneously,
     * only the first assignment succeeds.
     */
    fun assignExpertToJob(
        job: CustomerJobEntity,
        ranked: RankedExpert,
        onConflict: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            val result = repository.assignJobToExpertWithLock(
                jobId = job.id,
                expert = ranked.expert,
                distanceKm = ranked.distanceKm
            )

            if (result.isSuccess) {
                _activeJobForNearestExperts.value = null
                _currentMainTab.value = MainTab.CUSTOMER_ORDERS
                _currentCustomerSubTab.value = CustomerSubTab.ORDERS
                _currentOrderStatusTab.value = OrderStatusTab.PROCESSING
                _statusMessage.value = "Expert ${ranked.expert.name} assigned! Moved to Processing."
            } else {
                val errorMsg = result.exceptionOrNull()?.localizedMessage ?: "Assignment failed due to conflict"
                _statusMessage.value = errorMsg
                onConflict(errorMsg)
            }
        }
    }

    fun updateExpertNotified(jobId: Long, sent: Boolean) {
        viewModelScope.launch {
            repository.updateExpertNotified(jobId, sent)
        }
    }

    fun updateCustomerNotifiedOnAssign(jobId: Long, sent: Boolean) {
        viewModelScope.launch {
            repository.updateCustomerNotifiedOnAssign(jobId, sent)
        }
    }

    fun updateCustomerNotifiedOnCompletion(jobId: Long, sent: Boolean) {
        viewModelScope.launch {
            repository.updateCustomerNotifiedOnCompletion(jobId, sent)
        }
    }

    fun updateJob(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.updateJob(job)
            _statusMessage.value = "Order #${job.id} details updated successfully."
        }
    }

    fun unassignExpert(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.unassignExpertFromJob(job.id)
            _currentOrderStatusTab.value = OrderStatusTab.PENDING
            _statusMessage.value = "Expert unassigned. Order #${job.id} moved back to Pending."
        }
    }

    fun markMessageLaterDismissed(jobId: Long) {
        viewModelScope.launch {
            repository.updateMessageDismissedAt(jobId, System.currentTimeMillis())
        }
    }

    fun completeOrCancelJobWithReview(
        job: CustomerJobEntity,
        isCompleted: Boolean,
        rating: Float,
        feedback: String?
    ) {
        viewModelScope.launch {
            repository.completeOrCancelJobWithReview(
                jobId = job.id,
                expertId = job.assignedExpertId,
                isCompleted = isCompleted,
                rating = rating,
                feedback = feedback
            )

            _currentMainTab.value = MainTab.CUSTOMER_ORDERS
            _currentCustomerSubTab.value = CustomerSubTab.ORDERS
            _currentOrderStatusTab.value = if (isCompleted) OrderStatusTab.COMPLETED else OrderStatusTab.CANCELLED
            _statusMessage.value = if (isCompleted) {
                "Order #${job.id} marked Completed! Rating recorded."
            } else {
                "Order #${job.id} Cancelled."
            }
        }
    }

    fun getNearestExpertsForJob(job: CustomerJobEntity): List<RankedExpert> {
        val experts = allExperts.value
        return experts.map { expert ->
            val dist = LocationHelper.calculateDistanceKm(
                lat1 = job.latitude,
                lon1 = job.longitude,
                lat2 = expert.latitude,
                lon2 = expert.longitude
            )
            val time = LocationHelper.estimateTravelTimeMinutes(dist)
            RankedExpert(
                expert = expert,
                distanceKm = dist,
                travelTimeMinutes = time
            )
        }.sortedBy { it.distanceKm }
    }

    fun callExpert(context: Context, phone: String) {
        WhatsAppHelper.openDialer(context, phone)
    }

    fun callCustomer(context: Context, phone: String) {
        WhatsAppHelper.openDialer(context, phone)
    }

    fun saveNewExpert(expert: ExpertEntity, onSaved: (ExpertEntity) -> Unit = {}) {
        viewModelScope.launch {
            val id = repository.insertExpert(expert)
            _statusMessage.value = "Expert ${expert.name} added successfully!"
            onSaved(expert.copy(id = id))
        }
    }

    fun updateExpert(expert: ExpertEntity) {
        viewModelScope.launch {
            repository.updateExpert(expert)
            _statusMessage.value = "Expert ${expert.name} updated!"
        }
    }

    fun deleteExpert(expert: ExpertEntity) {
        viewModelScope.launch {
            repository.moveExpertToRecycleBin(expert.id)
            _statusMessage.value = "Expert '${expert.name}' moved to Recycle Bin (Kept for 30 days)."
        }
    }

    fun deleteJob(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.moveJobToRecycleBin(job.id)
            _statusMessage.value = "Order #${job.id} moved to Recycle Bin (Kept for 30 days)."
        }
    }

    fun restoreJobFromRecycleBin(jobId: Long) {
        viewModelScope.launch {
            repository.restoreJobFromRecycleBin(jobId)
            _statusMessage.value = "Order #$jobId restored to active orders!"
        }
    }

    fun deleteJobPermanently(jobId: Long) {
        viewModelScope.launch {
            repository.deleteJobPermanently(jobId)
            _statusMessage.value = "Order #$jobId deleted permanently."
        }
    }

    fun restoreExpertFromRecycleBin(expertId: Long) {
        viewModelScope.launch {
            repository.restoreExpertFromRecycleBin(expertId)
            _statusMessage.value = "Expert restored to active experts!"
        }
    }

    fun deleteExpertPermanently(expertId: Long) {
        viewModelScope.launch {
            repository.deleteExpertPermanently(expertId)
            _statusMessage.value = "Expert deleted permanently."
        }
    }

    fun emptyRecycleBin() {
        viewModelScope.launch {
            repository.clearRecycleBin()
            _statusMessage.value = "Recycle bin emptied!"
        }
    }

    fun updateWelcomeMessageSent(expertId: Long, sent: Boolean) {
        viewModelScope.launch {
            repository.updateWelcomeMessageSent(expertId, sent)
            if (sent) {
                _statusMessage.value = "Welcome message sent status updated."
            }
        }
    }

    fun unassignExpert(jobId: Long) {
        viewModelScope.launch {
            repository.unassignExpertFromJob(jobId)
            _statusMessage.value = "Expert unassigned. Order moved back to Pending."
        }
    }

    fun updateJobDetails(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.updateJob(job)
            _statusMessage.value = "Order #${job.id} details updated!"
        }
    }

    fun addNewCategory(name: String) {
        viewModelScope.launch {
            repository.insertCategory(name)
            _statusMessage.value = "Category '$name' created!"
        }
    }

    fun deleteCategory(category: ExpertCategoryEntity) {
        viewModelScope.launch {
            repository.deleteCategory(category)
            _statusMessage.value = "Category '${category.name}' deleted."
        }
    }

    fun restoreBackupData(
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ) {
        viewModelScope.launch {
            repository.restoreDatabase(jobs, experts, categories)
            _statusMessage.value = "Backup successfully restored! (${jobs.size} orders, ${experts.size} experts)"
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Admin Control Panel & User Management
    // ---------------------------------------------------------------------------------------------

    fun createStaffMember(
        name: String,
        phone: String,
        password: String,
        canAddExperts: Boolean,
        canManageOrders: Boolean,
        canAddCustomers: Boolean,
        viewOnly: Boolean,
        onResult: (Result<Unit>) -> Unit
    ) {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) {
            onResult(Result.failure(Exception("Enter a valid 10-digit mobile number")))
            return
        }
        if (name.isBlank()) {
            onResult(Result.failure(Exception("Enter staff member's name")))
            return
        }
        if (password.length < 4) {
            onResult(Result.failure(Exception("Password must be at least 4 characters")))
            return
        }

        viewModelScope.launch {
            val user = HurifixUser(
                phone = cleanPhone,
                name = name.trim(),
                password = password.trim(),
                role = HurifixUser.ROLE_STAFF,
                is_blocked = false,
                can_add_experts = canAddExperts,
                can_manage_orders = canManageOrders,
                can_add_customers = canAddCustomers,
                view_only = viewOnly,
                created_at = System.currentTimeMillis(),
                last_login = 0L
            )
            val res = repository.firestoreService?.saveStaffUser(user) ?: Result.failure(Exception("Firestore unavailable"))
            onResult(res)
        }
    }

    fun toggleUserBlocked(phone: String, currentBlocked: Boolean) {
        viewModelScope.launch {
            repository.firestoreService?.setUserBlocked(phone, !currentBlocked)
            _statusMessage.value = if (!currentBlocked) "User account blocked!" else "User account unblocked."
        }
    }

    fun updateUserPermissions(
        phone: String,
        canAddExperts: Boolean,
        canManageOrders: Boolean,
        canAddCustomers: Boolean,
        viewOnly: Boolean
    ) {
        viewModelScope.launch {
            repository.firestoreService?.updateUserPermissions(
                phone = phone,
                canAddExperts = canAddExperts,
                canManageOrders = canManageOrders,
                canAddCustomers = canAddCustomers,
                viewOnly = viewOnly
            )
            _statusMessage.value = "Permissions updated for $phone"
        }
    }

    fun updateUserPassword(phone: String, newPassword: String, onResult: (Result<Unit>) -> Unit) {
        viewModelScope.launch {
            val res = repository.firestoreService?.updateUserPassword(phone, newPassword)
                ?: Result.failure(Exception("Firestore service unavailable"))
            onResult(res)
        }
    }
}

class DispatchViewModelFactory(private val repository: DispatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DispatchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DispatchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
