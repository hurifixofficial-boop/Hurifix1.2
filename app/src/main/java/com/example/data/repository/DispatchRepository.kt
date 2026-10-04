package com.example.data.repository

import android.content.Context
import com.example.data.local.AppDatabase
import com.example.data.local.CustomerDao
import com.example.data.local.CustomerJobDao
import com.example.data.local.ExpertCategoryDao
import com.example.data.local.ExpertDao
import com.example.data.local.TechnicianDao
import com.example.data.model.CustomerEntity
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.JobStatus
import com.example.data.model.RankedExpert
import com.example.data.model.TechnicianEntity
import com.example.util.LocationHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
class DispatchRepository(
    private val context: Context? = null,
    initialUserPhone: String = "",
    private val fallbackExpertDao: ExpertDao? = null,
    private val fallbackJobDao: CustomerJobDao? = null,
    private val fallbackCategoryDao: ExpertCategoryDao? = null,
    private val fallbackTechnicianDao: TechnicianDao? = null,
    private val fallbackCustomerDao: CustomerDao? = null
) {
    // Secondary constructor for direct DAO usage / unit tests
    constructor(
        expertDao: ExpertDao,
        jobDao: CustomerJobDao,
        expertCategoryDao: ExpertCategoryDao,
        technicianDao: TechnicianDao? = null,
        customerDao: CustomerDao? = null
    ) : this(
        context = null,
        initialUserPhone = "test_user",
        fallbackExpertDao = expertDao,
        fallbackJobDao = jobDao,
        fallbackCategoryDao = expertCategoryDao,
        fallbackTechnicianDao = technicianDao,
        fallbackCustomerDao = customerDao
    )

    private val _currentUserPhone = MutableStateFlow(initialUserPhone.replace(Regex("[^0-9]"), ""))
    val currentUserPhone: StateFlow<String> = _currentUserPhone.asStateFlow()

    fun setCurrentUser(phone: String) {
        _currentUserPhone.value = phone.replace(Regex("[^0-9]"), "")
    }

    private fun getDb(): AppDatabase? {
        val ctx = context ?: return null
        val phone = _currentUserPhone.value.ifBlank { "default" }
        return AppDatabase.getDatabase(ctx, phone)
    }

    private fun getExpertDao(): ExpertDao {
        return getDb()?.expertDao() ?: fallbackExpertDao ?: throw IllegalStateException("No ExpertDao available")
    }

    private fun getJobDao(): CustomerJobDao {
        return getDb()?.customerJobDao() ?: fallbackJobDao ?: throw IllegalStateException("No CustomerJobDao available")
    }

    private fun getCategoryDao(): ExpertCategoryDao {
        return getDb()?.expertCategoryDao() ?: fallbackCategoryDao ?: throw IllegalStateException("No CategoryDao available")
    }

    private fun getTechnicianDao(): TechnicianDao? {
        return getDb()?.technicianDao() ?: fallbackTechnicianDao
    }

    private fun getCustomerDao(): CustomerDao? {
        return getDb()?.customerDao() ?: fallbackCustomerDao
    }

    val allExperts: Flow<List<ExpertEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackExpertDao?.getAllExperts() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).expertDao().getAllExperts()
        }
    }

    val availableExperts: Flow<List<ExpertEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackExpertDao?.getAvailableExperts() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).expertDao().getAvailableExperts()
        }
    }

    val allJobs: Flow<List<CustomerJobEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackJobDao?.getAllJobs() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).customerJobDao().getAllJobs()
        }
    }

    val allCategories: Flow<List<ExpertCategoryEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackCategoryDao?.getAllCategories() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).expertCategoryDao().getAllCategories()
        }
    }

    val deletedJobs: Flow<List<CustomerJobEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackJobDao?.getDeletedJobs() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).customerJobDao().getDeletedJobs()
        }
    }

    val deletedExperts: Flow<List<ExpertEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackExpertDao?.getDeletedExperts() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).expertDao().getDeletedExperts()
        }
    }

    val allTechnicians: Flow<List<TechnicianEntity>>? = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackTechnicianDao?.getAllTechnicians() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).technicianDao().getAllTechnicians()
        }
    }

    val allCustomers: Flow<List<CustomerEntity>>? = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackCustomerDao?.getAllCustomers() ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).customerDao().getAllCustomers()
        }
    }

    fun getJobsForExpert(expertId: Long): Flow<List<CustomerJobEntity>> = _currentUserPhone.flatMapLatest { phone ->
        if (context == null) {
            fallbackJobDao?.getJobsForExpert(expertId) ?: flowOf(emptyList())
        } else if (phone.isBlank()) {
            flowOf(emptyList())
        } else {
            AppDatabase.getDatabase(context, phone).customerJobDao().getJobsForExpert(expertId)
        }
    }

    suspend fun moveJobToRecycleBin(jobId: Long) {
        getJobDao().moveToRecycleBin(jobId, System.currentTimeMillis())
    }

    suspend fun restoreJobFromRecycleBin(jobId: Long) {
        getJobDao().restoreJobFromRecycleBin(jobId)
    }

    suspend fun deleteJobPermanently(jobId: Long) {
        getJobDao().deleteJobById(jobId)
    }

    suspend fun moveExpertToRecycleBin(expertId: Long) {
        getExpertDao().moveToRecycleBin(expertId, System.currentTimeMillis())
    }

    suspend fun restoreExpertFromRecycleBin(expertId: Long) {
        getExpertDao().restoreExpertFromRecycleBin(expertId)
    }

    suspend fun deleteExpertPermanently(expertId: Long) {
        getExpertDao().deleteExpertById(expertId)
    }

    suspend fun purgeRecycleBinOlderThan30Days() {
        val thirtyDaysAgo = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000)
        getJobDao().purgeJobsOlderThan(thirtyDaysAgo)
        getExpertDao().purgeExpertsOlderThan(thirtyDaysAgo)
    }

    suspend fun clearRecycleBin() {
        getJobDao().clearRecycleBin()
        getExpertDao().clearRecycleBin()
    }

    suspend fun updateWelcomeMessageSent(expertId: Long, sent: Boolean) {
        getExpertDao().updateWelcomeMessageSent(expertId, sent)
    }

    // Category operations
    suspend fun insertCategory(name: String, isDefault: Boolean = false): Long {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return 0L
        return getCategoryDao().insertCategory(
            ExpertCategoryEntity(name = trimmed, isDefault = isDefault)
        )
    }

    suspend fun deleteCategory(category: ExpertCategoryEntity) {
        getCategoryDao().deleteCategory(category)
    }

    suspend fun deleteCategoryByName(name: String) {
        getCategoryDao().deleteCategoryByName(name)
    }

    // Technician operations
    suspend fun insertTechnician(technician: TechnicianEntity): Long {
        val id = getTechnicianDao()?.insertTechnician(technician) ?: 0L
        getExpertDao().insertExpert(
            ExpertEntity(
                name = technician.name,
                phone = technician.contact,
                category = technician.category,
                address = technician.address,
                latitude = technician.latitude,
                longitude = technician.longitude,
                isAvailable = technician.isAvailable,
                rating = technician.rating,
                completedJobsCount = technician.completedJobsCount
            )
        )
        return id
    }

    suspend fun updateTechnician(technician: TechnicianEntity) {
        getTechnicianDao()?.updateTechnician(technician)
    }

    suspend fun deleteTechnician(technician: TechnicianEntity) {
        getTechnicianDao()?.deleteTechnician(technician)
    }

    suspend fun getTechnicianById(id: Long): TechnicianEntity? = getTechnicianDao()?.getTechnicianById(id)

    // Customer operations
    suspend fun insertCustomer(customer: CustomerEntity): Long =
        getCustomerDao()?.insertCustomer(customer) ?: 0L

    suspend fun updateCustomer(customer: CustomerEntity) {
        getCustomerDao()?.updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: CustomerEntity) {
        getCustomerDao()?.deleteCustomer(customer)
    }

    suspend fun getCustomerById(id: Long): CustomerEntity? = getCustomerDao()?.getCustomerById(id)

    // Expert operations
    suspend fun insertExpert(expert: ExpertEntity): Long = getExpertDao().insertExpert(expert)

    suspend fun updateExpert(expert: ExpertEntity) = getExpertDao().updateExpert(expert)

    suspend fun deleteExpert(expert: ExpertEntity) = getExpertDao().deleteExpert(expert)

    // Customer Job / Order operations
    suspend fun insertJob(job: CustomerJobEntity): Long {
        val id = getJobDao().insertJob(job)
        getCustomerDao()?.insertCustomer(
            CustomerEntity(
                name = job.customerName,
                contact = job.customerPhone,
                address = job.address,
                latitude = job.latitude,
                longitude = job.longitude,
                serviceRequired = job.serviceType,
                issueDescription = job.issueDescription
            )
        )
        return id
    }

    suspend fun updateJob(job: CustomerJobEntity) {
        getJobDao().updateJob(job)
    }

    suspend fun unassignExpertFromJob(jobId: Long) {
        getJobDao().unassignExpertFromJob(jobId)
    }

    suspend fun updateJobStatus(jobId: Long, status: JobStatus) {
        getJobDao().updateJobStatus(jobId, status.name)
    }

    suspend fun assignJobToExpert(
        jobId: Long,
        expert: ExpertEntity,
        distanceKm: Double
    ) {
        getJobDao().updateJobDispatch(
            jobId = jobId,
            status = JobStatus.PROCESSING.name,
            expertId = expert.id,
            expertName = expert.name,
            expertPhone = expert.phone,
            distanceKm = distanceKm
        )
    }

    suspend fun updateExpertNotified(jobId: Long, sent: Boolean) {
        getJobDao().updateExpertNotified(jobId, sent)
    }

    suspend fun updateCustomerNotifiedOnAssign(jobId: Long, sent: Boolean) {
        getJobDao().updateCustomerNotifiedOnAssign(jobId, sent)
    }

    suspend fun updateCustomerNotifiedOnCompletion(jobId: Long, sent: Boolean) {
        getJobDao().updateCustomerNotifiedOnCompletion(jobId, sent)
    }

    suspend fun updateMessageDismissedAt(jobId: Long, time: Long?) {
        getJobDao().updateMessageDismissedAt(jobId, time)
    }

    suspend fun completeOrCancelJobWithReview(
        jobId: Long,
        expertId: Long?,
        isCompleted: Boolean,
        rating: Float,
        feedback: String?
    ) {
        val newStatus = if (isCompleted) JobStatus.COMPLETED.name else JobStatus.CANCELLED.name
        getJobDao().completeOrCancelJobWithReview(
            jobId = jobId,
            status = newStatus,
            rating = rating,
            feedback = feedback,
            completedAt = System.currentTimeMillis()
        )

        // Update expert's review metrics in database
        if (expertId != null && expertId > 0) {
            val expertDao = getExpertDao()
            val expert = expertDao.getExpertById(expertId)
            if (expert != null) {
                val newCount = expert.totalRatingsCount + 1
                val newSum = expert.ratingSum + rating
                val newAvg = (newSum / newCount)
                val roundedRating = Math.round(newAvg * 10f) / 10f

                expertDao.updateExpert(
                    expert.copy(
                        rating = roundedRating,
                        ratingSum = newSum,
                        totalRatingsCount = newCount,
                        completedJobsCount = if (isCompleted) expert.completedJobsCount + 1 else expert.completedJobsCount,
                        cancelledJobsCount = if (!isCompleted) expert.cancelledJobsCount + 1 else expert.cancelledJobsCount
                    )
                )
            }
        }
    }

    suspend fun deleteJob(job: CustomerJobEntity) = getJobDao().deleteJob(job)

    suspend fun restoreDatabase(
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ) {
        if (jobs.isNotEmpty()) {
            getJobDao().insertJobs(jobs)
        }
        if (experts.isNotEmpty()) {
            getExpertDao().insertExperts(experts)
        }
        if (categories.isNotEmpty()) {
            val catDao = getCategoryDao()
            categories.forEach { catDao.insertCategory(it) }
        }
    }

    suspend fun findNearestExperts(
        customerLat: Double,
        customerLng: Double,
        requiredService: String? = null,
        onlyAvailable: Boolean = false
    ): List<RankedExpert> {
        val experts = if (onlyAvailable) {
            availableExperts.first()
        } else {
            allExperts.first()
        }

        return experts
            .filter { expert ->
                if (requiredService.isNullOrBlank() || requiredService == "All") {
                    true
                } else {
                    expert.category.contains(requiredService, ignoreCase = true) ||
                            expert.category.contains("All-Rounder", ignoreCase = true) ||
                            requiredService.contains(expert.category, ignoreCase = true)
                }
            }
            .map { expert ->
                val distance = LocationHelper.calculateDistanceKm(
                    lat1 = customerLat,
                    lon1 = customerLng,
                    lat2 = expert.latitude,
                    lon2 = expert.longitude
                )
                val travelMinutes = LocationHelper.estimateTravelTimeMinutes(distance)
                RankedExpert(
                    expert = expert,
                    distanceKm = distance,
                    travelTimeMinutes = travelMinutes
                )
            }
            .sortedBy { it.distanceKm }
    }

    suspend fun ensureDefaultCategoriesForCurrentUser() {
        val phone = _currentUserPhone.value
        if (context != null && phone.isNotBlank()) {
            val db = AppDatabase.getDatabase(context, phone)
            if (db.expertCategoryDao().getCategoryCount() == 0) {
                db.expertCategoryDao().insertCategory(
                    ExpertCategoryEntity(name = "Electrician", isDefault = true)
                )
                db.expertCategoryDao().insertCategory(
                    ExpertCategoryEntity(name = "Plumber", isDefault = true)
                )
            }
        } else {
            val catDao = getCategoryDao()
            if (catDao.getCategoryCount() == 0) {
                catDao.insertCategory(ExpertCategoryEntity(name = "Electrician", isDefault = true))
                catDao.insertCategory(ExpertCategoryEntity(name = "Plumber", isDefault = true))
            }
        }
    }

    suspend fun seedSampleExpertsIfEmpty() {
        ensureDefaultCategoriesForCurrentUser()
    }
}
