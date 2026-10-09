package com.example.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.JobStatus
import com.example.data.model.RankedExpert
import com.example.data.repository.DispatchRepository
import com.example.util.LocationHelper
import com.example.util.WhatsAppHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DispatchViewModel(val repository: DispatchRepository) : ViewModel() {

    private val _currentMainTab = MutableStateFlow(MainTab.CUSTOMER_ORDERS)
    val currentMainTab: StateFlow<MainTab> = _currentMainTab.asStateFlow()

    private val _currentCustomerSubTab = MutableStateFlow(CustomerSubTab.DISPATCH_ORDER)
    val currentCustomerSubTab: StateFlow<CustomerSubTab> = _currentCustomerSubTab.asStateFlow()

    private val _currentOrderStatusTab = MutableStateFlow(OrderStatusTab.PENDING)
    val currentOrderStatusTab: StateFlow<OrderStatusTab> = _currentOrderStatusTab.asStateFlow()

    val allJobs: StateFlow<List<CustomerJobEntity>> = repository.activeJobs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val deletedJobs: StateFlow<List<CustomerJobEntity>> = repository.recycleBinJobs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allExperts: StateFlow<List<ExpertEntity>> = repository.activeExperts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val deletedExperts: StateFlow<List<ExpertEntity>> = repository.recycleBinExperts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allCategories: StateFlow<List<ExpertCategoryEntity>> = repository.categories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

    private val _activeJobForNearestExperts = MutableStateFlow<CustomerJobEntity?>(null)
    val activeJobForNearestExperts: StateFlow<CustomerJobEntity?> = _activeJobForNearestExperts.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _customerForm = MutableStateFlow(CustomerOrderFormState())
    val customerForm: StateFlow<CustomerOrderFormState> = _customerForm.asStateFlow()

    fun updateName(name: String) {
        _customerForm.value = _customerForm.value.copy(name = name)
    }

    fun updatePhone(phone: String) {
        _customerForm.value = _customerForm.value.copy(phone = phone)
    }

    fun updateServiceType(serviceType: String) {
        _customerForm.value = _customerForm.value.copy(serviceType = serviceType)
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
                latitude = 0.0,
                longitude = 0.0,
                hasValidLocation = false
            )
        }
    }

    fun fetchCurrentGps(context: Context) {
        LocationHelper.fetchCurrentLocation(
            context,
            onSuccess = { lat, lon ->
                updateLocationInput("$lat, $lon")
            },
            onError = { err ->
                _statusMessage.value = err
            }
        )
    }

    fun saveCustomerOrder(
        status: JobStatus = JobStatus.PENDING,
        createdById: String,
        createdByName: String,
        createdByDesignation: String,
        onSaved: (CustomerJobEntity) -> Unit
    ) {
        viewModelScope.launch {
            val form = _customerForm.value
            val job = CustomerJobEntity(
                customerName = form.name.trim(),
                customerPhone = form.phone.trim(),
                address = form.address.trim(),
                latitude = form.latitude,
                longitude = form.longitude,
                serviceType = form.serviceType.trim(),
                issueDescription = form.issueDescription.trim(),
                status = status.statusName,
                created_by_user_id = createdById,
                created_by_user_name = createdByName,
                created_by_designation = createdByDesignation,
                createdAt = System.currentTimeMillis()
            )
            val newId = repository.insertJob(job)
            val savedJob = job.copy(id = newId)
            _customerForm.value = CustomerOrderFormState()
            onSaved(savedJob)
        }
    }

    fun onUserLoggedIn(phone: String) {
        viewModelScope.launch {
            repository.onUserLoggedIn(phone)
        }
    }

    fun onUserLoggedOut() {
        repository.onUserLoggedOut()
    }

    fun selectMainTab(tab: MainTab) {
        _currentMainTab.value = tab
    }

    fun selectCustomerSubTab(subTab: CustomerSubTab) {
        _currentCustomerSubTab.value = subTab
    }

    fun selectOrderStatusTab(tab: OrderStatusTab) {
        _currentOrderStatusTab.value = tab
    }

    fun clearStatusMessage() {
        _statusMessage.value = null
    }

    fun refreshAllData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                repository.refreshAllData()
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun addNewCategory(name: String) {
        viewModelScope.launch {
            repository.insertCategory(ExpertCategoryEntity(name = name.trim()))
        }
    }

    fun deleteCategory(category: ExpertCategoryEntity) {
        viewModelScope.launch {
            repository.deleteCategory(category.id)
        }
    }

    fun deleteExpert(expert: ExpertEntity) {
        viewModelScope.launch {
            repository.deleteExpert(expert)
        }
    }

    fun updateExpert(expert: ExpertEntity) {
        viewModelScope.launch {
            repository.updateExpert(expert)
        }
    }

    fun saveNewExpert(expert: ExpertEntity, onSaved: (ExpertEntity) -> Unit) {
        viewModelScope.launch {
            val id = repository.insertExpert(expert)
            onSaved(expert.copy(id = id))
        }
    }

    fun saveNewExpert(
        name: String,
        phone: String,
        category: String,
        address: String,
        latitude: Double,
        longitude: Double,
        photoUri: String?,
        isAvailable: Boolean,
        userId: String,
        userName: String,
        userDesignation: String,
        onSaved: (ExpertEntity) -> Unit
    ) {
        viewModelScope.launch {
            val expert = ExpertEntity(
                name = name,
                phone = phone,
                category = category,
                address = address,
                latitude = latitude,
                longitude = longitude,
                profilePicUrl = photoUri ?: "",
                isAvailable = isAvailable,
                added_by_user_id = userId,
                added_by_user_name = userName,
                added_by_designation = userDesignation,
                createdAt = System.currentTimeMillis()
            )
            val id = repository.insertExpert(expert)
            onSaved(expert.copy(id = id))
        }
    }

    fun openFindNearestExperts(job: CustomerJobEntity) {
        _activeJobForNearestExperts.value = job
    }

    fun closeFindNearestExperts() {
        _activeJobForNearestExperts.value = null
    }

    fun getNearestExpertsForJob(job: CustomerJobEntity): List<RankedExpert> {
        val currentExperts = allExperts.value.filter { it.isAvailable && !it.isDeleted }
        return currentExperts.map { expert ->
            val dist = if (job.latitude != 0.0 && job.longitude != 0.0 && expert.latitude != 0.0 && expert.longitude != 0.0) {
                LocationHelper.calculateDistance(job.latitude, job.longitude, expert.latitude, expert.longitude)
            } else 0.0
            RankedExpert(expert = expert, distanceKm = dist)
        }.sortedBy { it.distanceKm }
    }

    fun assignExpertToJob(
        job: CustomerJobEntity,
        ranked: RankedExpert,
        managedByUserId: String,
        managedByUserName: String,
        managedByDesignation: String,
        onAssigned: () -> Unit
    ) {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val updated = job.copy(
                status = JobStatus.PROCESSING.statusName,
                assignedExpertId = ranked.expert.id,
                assignedExpertName = ranked.expert.name,
                assignedExpertPhone = ranked.expert.phone,
                distanceKmAtDispatch = ranked.distanceKm,
                managed_by_user_id = managedByUserId,
                managed_by_user_name = managedByUserName,
                managed_by_designation = managedByDesignation,
                assigned_at_timestamp = currentTime,
                last_updated = currentTime
            )
            repository.updateJob(updated)
            onAssigned()
        }
    }

    fun takeoverOrder(
        job: CustomerJobEntity,
        currentUserId: String,
        currentUserName: String,
        currentUserDesignation: String,
        onDone: () -> Unit = {}
    ) {
        viewModelScope.launch {
            val updated = job.copy(
                managed_by_user_id = currentUserId,
                managed_by_user_name = currentUserName,
                managed_by_designation = currentUserDesignation,
                last_updated = System.currentTimeMillis()
            )
            repository.updateJob(updated)
            onDone()
        }
    }

    fun takeoverOrder(
        job: CustomerJobEntity,
        userId: String,
        userName: String,
        userDesignation: String
    ) {
        takeoverOrder(job, userId, userName, userDesignation, onDone = {})
    }

    fun unassignExpert(job: CustomerJobEntity) {
        viewModelScope.launch {
            val updated = job.copy(
                status = JobStatus.PENDING.statusName,
                assignedExpertId = null,
                assignedExpertName = null,
                assignedExpertPhone = null,
                distanceKmAtDispatch = null,
                assigned_at_timestamp = null,
                last_updated = System.currentTimeMillis()
            )
            repository.updateJob(updated)
        }
    }

    fun updateJob(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.updateJob(job)
        }
    }

    fun deleteJob(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.deleteJob(job)
        }
    }

    fun restoreJobFromRecycleBin(job: CustomerJobEntity) {
        viewModelScope.launch {
            repository.restoreJobFromRecycleBin(job)
        }
    }

    fun restoreJobFromRecycleBin(jobId: Long) {
        viewModelScope.launch {
            val job = deletedJobs.value.find { it.id == jobId }
            if (job != null) {
                repository.restoreJobFromRecycleBin(job)
            }
        }
    }

    fun deleteJobPermanently(jobId: Long) {
        viewModelScope.launch {
            repository.deleteJobPermanently(jobId)
        }
    }

    fun restoreExpertFromRecycleBin(expert: ExpertEntity) {
        viewModelScope.launch {
            repository.restoreExpertFromRecycleBin(expert)
        }
    }

    fun restoreExpertFromRecycleBin(expertId: Long) {
        viewModelScope.launch {
            val expert = deletedExperts.value.find { it.id == expertId }
            if (expert != null) {
                repository.restoreExpertFromRecycleBin(expert)
            }
        }
    }

    fun deleteExpertPermanently(expertId: Long) {
        viewModelScope.launch {
            repository.deleteExpertPermanently(expertId)
        }
    }

    fun emptyRecycleBin() {
        viewModelScope.launch {
            repository.clearRecycleBin()
        }
    }

    fun completeOrCancelJobWithReview(
        job: CustomerJobEntity,
        isCompleted: Boolean,
        rating: Any,
        feedback: String
    ) {
        viewModelScope.launch {
            val ratingFloat = when (rating) {
                is Float -> rating
                is Int -> rating.toFloat()
                is Double -> rating.toFloat()
                is Number -> rating.toFloat()
                else -> 5.0f
            }
            val status = if (isCompleted) JobStatus.COMPLETED.statusName else JobStatus.CANCELLED.statusName
            val updated = job.copy(
                status = status,
                ratingGiven = ratingFloat,
                reviewFeedback = feedback,
                completedAt = System.currentTimeMillis(),
                last_updated = System.currentTimeMillis()
            )
            repository.updateJob(updated)
        }
    }

    fun updateExpertNotified(jobId: Long, notified: Boolean) {
        viewModelScope.launch {
            val job = allJobs.value.find { it.id == jobId } ?: return@launch
            val updated = job.copy(isExpertNotified = notified, last_updated = System.currentTimeMillis())
            repository.updateJob(updated)
        }
    }

    fun updateCustomerNotifiedOnAssign(jobId: Long, notified: Boolean) {
        viewModelScope.launch {
            val job = allJobs.value.find { it.id == jobId } ?: return@launch
            val updated = job.copy(isCustomerNotifiedOnAssign = notified, last_updated = System.currentTimeMillis())
            repository.updateJob(updated)
        }
    }

    fun updateCustomerNotifiedOnCompletion(jobId: Long, notified: Boolean) {
        viewModelScope.launch {
            val job = allJobs.value.find { it.id == jobId } ?: return@launch
            val updated = job.copy(isCustomerNotifiedOnCompletion = notified, last_updated = System.currentTimeMillis())
            repository.updateJob(updated)
        }
    }

    fun updateWelcomeMessageSent(expertId: Long, sent: Boolean) {
        viewModelScope.launch {
            val expert = allExperts.value.find { it.id == expertId } ?: return@launch
            val updated = expert.copy(isWelcomeMessageSent = sent)
            repository.updateExpert(updated)
        }
    }

    fun markMessageLaterDismissed(jobId: Long) {
        viewModelScope.launch {
            val job = allJobs.value.find { it.id == jobId } ?: return@launch
            val updated = job.copy(assignMessageLaterDismissedAt = System.currentTimeMillis(), last_updated = System.currentTimeMillis())
            repository.updateJob(updated)
        }
    }

    fun calculateEstimatedArrivalTimeWithBuffer(distanceKm: Double): String {
        return WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(distanceKm)
    }

    fun parseAndFillFromWhatsAppText(rawText: String) {
        val coords = LocationHelper.parseCoordinatesFromText(rawText)
        val lat = coords?.first ?: 0.0
        val lon = coords?.second ?: 0.0
        val phoneMatch = Regex("\\b[6-9]\\d{9}\\b").find(rawText)?.value ?: ""
        val locStr = if (lat != 0.0 && lon != 0.0) "$lat, $lon" else ""
        _customerForm.value = _customerForm.value.copy(
            phone = phoneMatch,
            issueDescription = rawText,
            rawLocationInput = locStr,
            latitude = lat,
            longitude = lon,
            hasValidLocation = (lat != 0.0 && lon != 0.0)
        )
    }

    fun parseAndFillFromWhatsAppText(
        text: String,
        onParsed: (String, String, String, String, Double, Double, String) -> Unit
    ) {
        val coords = LocationHelper.parseCoordinatesFromText(text)
        val lat = coords?.first ?: 0.0
        val lon = coords?.second ?: 0.0
        val phoneMatch = Regex("\\b[6-9]\\d{9}\\b").find(text)?.value ?: ""
        onParsed("", phoneMatch, "", "", lat, lon, text)
    }

    fun restoreBackupData(
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ) {
        viewModelScope.launch {
            repository.restoreBackup(jobs, experts, categories)
        }
    }

    fun restoreBackupData(
        data: Map<String, Any>,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            onSuccess()
        }
    }
}

class DispatchViewModelFactory(private val repository: DispatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DispatchViewModel(repository) as T
    }
}
