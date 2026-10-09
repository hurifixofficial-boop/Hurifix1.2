package com.example.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.DispatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DispatchViewModel(val repository: DispatchRepository) : ViewModel() {
    val allExperts: Flow<List<ExpertEntity>> = repository.activeExperts
    val allJobs: Flow<List<CustomerJobEntity>> = repository.activeJobs
    val allCategories: Flow<List<ExpertCategoryEntity>> = repository.categories
    val deletedJobs: Flow<List<CustomerJobEntity>> = repository.recycleBinJobs
    val deletedExperts: Flow<List<ExpertEntity>> = repository.recycleBinExperts

    private val _currentMainTab = MutableStateFlow(MainTab.CUSTOMER_ORDERS)
    val currentMainTab: StateFlow<MainTab> = _currentMainTab.asStateFlow()

    private val _currentCustomerSubTab = MutableStateFlow(CustomerSubTab.ORDERS)
    val currentCustomerSubTab: StateFlow<CustomerSubTab> = _currentCustomerSubTab.asStateFlow()

    private val _currentOrderStatusTab = MutableStateFlow(OrderStatusTab.PENDING)
    val currentOrderStatusTab: StateFlow<OrderStatusTab> = _currentOrderStatusTab.asStateFlow()

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

    private val _activeJobForNearestExperts = MutableStateFlow<CustomerJobEntity?>(null)
    val activeJobForNearestExperts: StateFlow<CustomerJobEntity?> = _activeJobForNearestExperts.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _customerForm = MutableStateFlow(CustomerFormState())
    val customerForm: StateFlow<CustomerFormState> = _customerForm.asStateFlow()

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

    fun refreshAllData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.refreshAllData()
            _isRefreshing.value = false
        }
    }

    fun openFindNearestExperts(job: CustomerJobEntity) {
        _activeJobForNearestExperts.value = job
    }

    fun closeFindNearestExperts() {
        _activeJobForNearestExperts.value = null
    }

    fun getNearestExpertsForJob(job: CustomerJobEntity): List<RankedExpert> {
        return emptyList()
    }

    fun assignExpertToJob(
        job: CustomerJobEntity,
        ranked: RankedExpert,
        managedByUserId: String,
        managedByUserName: String,
        managedByDesignation: String
    ) {}

    fun updateExpertNotified(jobId: Long, notified: Boolean) {}
    fun markMessageLaterDismissed(jobId: Long) {}
    fun completeOrCancelJobWithReview(job: CustomerJobEntity, isCompleted: Boolean, rating: Int, feedback: String) {}
    fun updateCustomerNotifiedOnAssign(jobId: Long, notified: Boolean) {}
    fun updateCustomerNotifiedOnCompletion(jobId: Long, notified: Boolean) {}
    fun updateWelcomeMessageSent(expertId: Long, sent: Boolean) {}

    fun restoreBackupData(jobs: List<CustomerJobEntity>, experts: List<ExpertEntity>, categories: List<ExpertCategoryEntity>) {}
    fun addNewCategory(name: String) {}
    fun deleteCategory(category: ExpertCategoryEntity) {}
    fun updateExpert(expert: ExpertEntity) {}
    fun deleteExpert(expert: ExpertEntity) {}
    fun saveNewExpert(expert: ExpertEntity, onSaved: (ExpertEntity) -> Unit) {
        onSaved(expert)
    }

    fun parseAndFillFromWhatsAppText(text: String) {}
    fun fetchCurrentGps(context: Context) {}

    fun updateName(name: String) {
        _customerForm.value = _customerForm.value.copy(name = name)
    }

    fun updatePhone(phone: String) {
        _customerForm.value = _customerForm.value.copy(phone = phone)
    }

    fun updateServiceType(type: String) {
        _customerForm.value = _customerForm.value.copy(serviceType = type)
    }

    fun updateIssue(issue: String) {
        _customerForm.value = _customerForm.value.copy(issueDescription = issue)
    }

    fun updateAddress(address: String) {
        _customerForm.value = _customerForm.value.copy(address = address)
    }

    fun updateLocationInput(input: String) {
        _customerForm.value = _customerForm.value.copy(rawLocationInput = input, hasValidLocation = input.isNotBlank())
    }

    fun saveCustomerOrder(
        status: JobStatus,
        createdById: String,
        createdByName: String,
        createdByDesignation: String,
        onSaved: (CustomerJobEntity) -> Unit
    ) {
        val form = _customerForm.value
        val job = CustomerJobEntity(
            customerName = form.name,
            customerPhone = form.phone,
            serviceType = form.serviceType,
            issueDescription = form.issueDescription,
            address = form.address,
            status = status.name
        )
        onSaved(job)
    }

    fun takeoverOrder(
        job: CustomerJobEntity,
        currentUserId: String,
        currentUserName: String,
        currentUserDesignation: String,
        onDone: () -> Unit
    ) {
        onDone()
    }

    fun unassignExpert(job: CustomerJobEntity) {}
    fun updateJob(job: CustomerJobEntity) {}
    fun deleteJob(job: CustomerJobEntity) {}
    fun restoreJobFromRecycleBin(job: CustomerJobEntity) {}
    fun restoreJobFromRecycleBin(id: Long) {}
    fun deleteJobPermanently(job: CustomerJobEntity) {}
    fun deleteJobPermanently(id: Long) {}
    fun restoreExpertFromRecycleBin(expert: ExpertEntity) {}
    fun restoreExpertFromRecycleBin(id: Long) {}
    fun deleteExpertPermanently(expert: ExpertEntity) {}
    fun deleteExpertPermanently(id: Long) {}
    fun emptyRecycleBin() {}

    fun onUserLoggedIn(phone: String) {}
    fun onUserLoggedOut() {}
}

class DispatchViewModelFactory(private val repository: DispatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DispatchViewModel(repository) as T
    }
}
