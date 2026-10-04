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
import com.example.data.remote.FirestoreService
import com.example.data.sync.SyncManager
import com.example.util.LocationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class DispatchRepository(
    private val context: Context? = null,
    initialUserPhone: String = "",
    private val fallbackExpertDao: ExpertDao? = null,
    private val fallbackJobDao: CustomerJobDao? = null,
    private val fallbackCategoryDao: ExpertCategoryDao? = null,
    private val fallbackTechnicianDao: TechnicianDao? = null,
    private val fallbackCustomerDao: CustomerDao? = null
) {
    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // Secondary constructor for unit tests
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

    val firestoreService: FirestoreService? by lazy {
        context?.let { FirestoreService(it) }
    }

    val syncManager: SyncManager? by lazy {
        context?.let { SyncManager(it) }
    }

    fun setCurrentUser(phone: String) {
        _currentUserPhone.value = phone.replace(Regex("[^0-9]"), "")
    }

    /**
     * Centralized Shared Database Access:
     * All approved users (Admin and Staff) access the exact SAME centralized business database.
     */
    private fun getDb(): AppDatabase? {
        val ctx = context ?: return null
        return AppDatabase.getDatabase(ctx)
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

    // Shared Flows directly querying the central business Room DB
    val allExperts: Flow<List<ExpertEntity>> =
        if (context != null) AppDatabase.getDatabase(context).expertDao().getAllExperts()
        else fallbackExpertDao?.getAllExperts() ?: flowOf(emptyList())

    val availableExperts: Flow<List<ExpertEntity>> =
        if (context != null) AppDatabase.getDatabase(context).expertDao().getAvailableExperts()
        else fallbackExpertDao?.getAvailableExperts() ?: flowOf(emptyList())

    val allJobs: Flow<List<CustomerJobEntity>> =
        if (context != null) AppDatabase.getDatabase(context).customerJobDao().getAllJobs()
        else fallbackJobDao?.getAllJobs() ?: flowOf(emptyList())

    val allCategories: Flow<List<ExpertCategoryEntity>> =
        if (context != null) AppDatabase.getDatabase(context).expertCategoryDao().getAllCategories()
        else fallbackCategoryDao?.getAllCategories() ?: flowOf(emptyList())

    val deletedJobs: Flow<List<CustomerJobEntity>> =
        if (context != null) AppDatabase.getDatabase(context).customerJobDao().getDeletedJobs()
        else fallbackJobDao?.getDeletedJobs() ?: flowOf(emptyList())

    val deletedExperts: Flow<List<ExpertEntity>> =
        if (context != null) AppDatabase.getDatabase(context).expertDao().getDeletedExperts()
        else fallbackExpertDao?.getDeletedExperts() ?: flowOf(emptyList())

    val allTechnicians: Flow<List<TechnicianEntity>> =
        if (context != null) AppDatabase.getDatabase(context).technicianDao().getAllTechnicians()
        else fallbackTechnicianDao?.getAllTechnicians() ?: flowOf(emptyList())

    val allCustomers: Flow<List<CustomerEntity>> =
        if (context != null) AppDatabase.getDatabase(context).customerDao().getAllCustomers()
        else fallbackCustomerDao?.getAllCustomers() ?: flowOf(emptyList())

    fun getJobsForExpert(expertId: Long): Flow<List<CustomerJobEntity>> =
        if (context != null) AppDatabase.getDatabase(context).customerJobDao().getJobsForExpert(expertId)
        else fallbackJobDao?.getJobsForExpert(expertId) ?: flowOf(emptyList())

    // ---------------------------------------------------------------------------------------------
    // Duplicate Order Protection Check (Firestore + Local fallback)
    // ---------------------------------------------------------------------------------------------
    suspend fun checkDuplicateCustomerOrder(phone: String): Boolean {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) return false

        val fourHoursAgo = System.currentTimeMillis() - (4 * 60 * 60 * 1000)

        // 1. Check local centralized DB
        val localRecent = getJobDao().findRecentJobByPhone(cleanPhone, fourHoursAgo)
        if (localRecent != null) return true

        // 2. Check cloud Firestore
        return firestoreService?.isDuplicateOrderInLast4Hours(cleanPhone) ?: false
    }

    // ---------------------------------------------------------------------------------------------
    // Recycle Bin & Data Purge
    // ---------------------------------------------------------------------------------------------
    suspend fun moveJobToRecycleBin(jobId: Long) {
        val now = System.currentTimeMillis()
        getJobDao().moveToRecycleBin(jobId, now, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun restoreJobFromRecycleBin(jobId: Long) {
        val now = System.currentTimeMillis()
        getJobDao().restoreJobFromRecycleBin(jobId, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun deleteJobPermanently(jobId: Long) {
        getJobDao().deleteJobById(jobId)
    }

    suspend fun moveExpertToRecycleBin(expertId: Long) {
        val now = System.currentTimeMillis()
        getExpertDao().moveToRecycleBin(expertId, now, now)
        repositoryScope.launch {
            val expert = getExpertDao().getExpertById(expertId)
            if (expert != null) firestoreService?.pushExpertToFirestore(expert)
        }
    }

    suspend fun restoreExpertFromRecycleBin(expertId: Long) {
        val now = System.currentTimeMillis()
        getExpertDao().restoreExpertFromRecycleBin(expertId, now)
        repositoryScope.launch {
            val expert = getExpertDao().getExpertById(expertId)
            if (expert != null) firestoreService?.pushExpertToFirestore(expert)
        }
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
        val now = System.currentTimeMillis()
        getExpertDao().updateWelcomeMessageSent(expertId, sent, now)
        repositoryScope.launch {
            val expert = getExpertDao().getExpertById(expertId)
            if (expert != null) firestoreService?.pushExpertToFirestore(expert)
        }
    }

    // Category operations
    suspend fun insertCategory(name: String, isDefault: Boolean = false): Long {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return 0L
        val cat = ExpertCategoryEntity(
            name = trimmed,
            isDefault = isDefault,
            last_updated = System.currentTimeMillis()
        )
        val id = getCategoryDao().insertCategory(cat)
        repositoryScope.launch {
            firestoreService?.pushCategoryToFirestore(cat.copy(id = id))
        }
        return id
    }

    suspend fun deleteCategory(category: ExpertCategoryEntity) {
        getCategoryDao().deleteCategory(category)
    }

    suspend fun deleteCategoryByName(name: String) {
        getCategoryDao().deleteCategoryByName(name)
    }

    // Technician operations
    suspend fun insertTechnician(technician: TechnicianEntity): Long {
        val now = System.currentTimeMillis()
        val techWithTimestamp = technician.copy(last_updated = now)
        val id = getTechnicianDao()?.insertTechnician(techWithTimestamp) ?: 0L

        val expert = ExpertEntity(
            name = technician.name,
            phone = technician.contact,
            category = technician.category,
            address = technician.address,
            latitude = technician.latitude,
            longitude = technician.longitude,
            isAvailable = technician.isAvailable,
            rating = technician.rating,
            completedJobsCount = technician.completedJobsCount,
            last_updated = now
        )
        val expertId = getExpertDao().insertExpert(expert)

        repositoryScope.launch {
            firestoreService?.pushExpertToFirestore(expert.copy(id = expertId))
        }
        return id
    }

    suspend fun updateTechnician(technician: TechnicianEntity) {
        getTechnicianDao()?.updateTechnician(technician.copy(last_updated = System.currentTimeMillis()))
    }

    suspend fun deleteTechnician(technician: TechnicianEntity) {
        getTechnicianDao()?.deleteTechnician(technician)
    }

    suspend fun getTechnicianById(id: Long): TechnicianEntity? = getTechnicianDao()?.getTechnicianById(id)

    // Customer operations
    suspend fun insertCustomer(customer: CustomerEntity): Long =
        getCustomerDao()?.insertCustomer(customer.copy(last_updated = System.currentTimeMillis())) ?: 0L

    suspend fun updateCustomer(customer: CustomerEntity) {
        getCustomerDao()?.updateCustomer(customer.copy(last_updated = System.currentTimeMillis()))
    }

    suspend fun deleteCustomer(customer: CustomerEntity) {
        getCustomerDao()?.deleteCustomer(customer)
    }

    suspend fun getCustomerById(id: Long): CustomerEntity? = getCustomerDao()?.getCustomerById(id)

    // Expert operations
    suspend fun insertExpert(expert: ExpertEntity): Long {
        val now = System.currentTimeMillis()
        val expWithTimestamp = expert.copy(last_updated = now)
        val id = getExpertDao().insertExpert(expWithTimestamp)
        repositoryScope.launch {
            firestoreService?.pushExpertToFirestore(expWithTimestamp.copy(id = id))
        }
        return id
    }

    suspend fun updateExpert(expert: ExpertEntity) {
        val now = System.currentTimeMillis()
        val expWithTimestamp = expert.copy(last_updated = now, is_synced = false)
        getExpertDao().updateExpert(expWithTimestamp)
        repositoryScope.launch {
            firestoreService?.pushExpertToFirestore(expWithTimestamp)
        }
    }

    suspend fun deleteExpert(expert: ExpertEntity) = getExpertDao().deleteExpert(expert)

    // Customer Job / Order operations
    suspend fun insertJob(job: CustomerJobEntity): Long {
        val now = System.currentTimeMillis()
        val jobWithTimestamp = job.copy(last_updated = now, is_synced = false)
        val id = getJobDao().insertJob(jobWithTimestamp)

        // Mirror in customers list
        getCustomerDao()?.insertCustomer(
            CustomerEntity(
                name = job.customerName,
                contact = job.customerPhone,
                address = job.address,
                latitude = job.latitude,
                longitude = job.longitude,
                serviceRequired = job.serviceType,
                issueDescription = job.issueDescription,
                last_updated = now
            )
        )

        // Push to cloud Firestore
        repositoryScope.launch {
            firestoreService?.pushJobToFirestore(jobWithTimestamp.copy(id = id))
        }
        return id
    }

    suspend fun updateJob(job: CustomerJobEntity) {
        val now = System.currentTimeMillis()
        val updated = job.copy(last_updated = now, is_synced = false)
        getJobDao().updateJob(updated)
        repositoryScope.launch {
            firestoreService?.pushJobToFirestore(updated)
        }
    }

    suspend fun unassignExpertFromJob(jobId: Long) {
        val now = System.currentTimeMillis()
        getJobDao().unassignExpertFromJob(jobId, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun updateJobStatus(jobId: Long, status: JobStatus) {
        val now = System.currentTimeMillis()
        getJobDao().updateJobStatus(jobId, status.name, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    /**
     * Assigns technician using Firestore Transaction lock so only one assignment succeeds
     * even if two devices assign simultaneously.
     */
    suspend fun assignJobToExpertWithLock(
        jobId: Long,
        expert: ExpertEntity,
        distanceKm: Double
    ): Result<Unit> {
        val now = System.currentTimeMillis()

        // 1. Execute Firestore Transaction lock
        val cloudResult = firestoreService?.assignExpertWithLock(
            jobId = jobId,
            expertId = expert.id,
            expertName = expert.name,
            expertPhone = expert.phone,
            distanceKm = distanceKm
        )

        if (cloudResult != null && cloudResult.isFailure) {
            return cloudResult
        }

        // 2. Update local Room database
        getJobDao().updateJobDispatch(
            jobId = jobId,
            status = JobStatus.PROCESSING.name,
            expertId = expert.id,
            expertName = expert.name,
            expertPhone = expert.phone,
            distanceKm = distanceKm,
            lastUpdated = now
        )

        return Result.success(Unit)
    }

    suspend fun updateExpertNotified(jobId: Long, sent: Boolean) {
        val now = System.currentTimeMillis()
        getJobDao().updateExpertNotified(jobId, sent, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun updateCustomerNotifiedOnAssign(jobId: Long, sent: Boolean) {
        val now = System.currentTimeMillis()
        getJobDao().updateCustomerNotifiedOnAssign(jobId, sent, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun updateCustomerNotifiedOnCompletion(jobId: Long, sent: Boolean) {
        val now = System.currentTimeMillis()
        getJobDao().updateCustomerNotifiedOnCompletion(jobId, sent, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun updateMessageDismissedAt(jobId: Long, time: Long?) {
        val now = System.currentTimeMillis()
        getJobDao().updateMessageDismissedAt(jobId, time, now)
        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
        }
    }

    suspend fun completeOrCancelJobWithReview(
        jobId: Long,
        expertId: Long?,
        isCompleted: Boolean,
        rating: Float,
        feedback: String?
    ) {
        val now = System.currentTimeMillis()
        val newStatus = if (isCompleted) JobStatus.COMPLETED.name else JobStatus.CANCELLED.name
        getJobDao().completeOrCancelJobWithReview(
            jobId = jobId,
            status = newStatus,
            rating = rating,
            feedback = feedback,
            completedAt = now,
            lastUpdated = now
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

                val updatedExpert = expert.copy(
                    rating = roundedRating,
                    ratingSum = newSum,
                    totalRatingsCount = newCount,
                    completedJobsCount = if (isCompleted) expert.completedJobsCount + 1 else expert.completedJobsCount,
                    cancelledJobsCount = if (!isCompleted) expert.cancelledJobsCount + 1 else expert.cancelledJobsCount,
                    last_updated = now
                )
                expertDao.updateExpert(updatedExpert)
                repositoryScope.launch {
                    firestoreService?.pushExpertToFirestore(updatedExpert)
                }
            }
        }

        repositoryScope.launch {
            val job = getJobDao().getJobById(jobId)
            if (job != null) firestoreService?.pushJobToFirestore(job)
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
            repositoryScope.launch {
                jobs.forEach { firestoreService?.pushJobToFirestore(it) }
            }
        }
        if (experts.isNotEmpty()) {
            getExpertDao().insertExperts(experts)
            repositoryScope.launch {
                experts.forEach { firestoreService?.pushExpertToFirestore(it) }
            }
        }
        if (categories.isNotEmpty()) {
            val catDao = getCategoryDao()
            categories.forEach { catDao.insertCategory(it) }
            repositoryScope.launch {
                categories.forEach { firestoreService?.pushCategoryToFirestore(it) }
            }
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
        val catDao = getCategoryDao()
        if (catDao.getCategoryCount() == 0) {
            insertCategory("Electrician", isDefault = true)
            insertCategory("Plumber", isDefault = true)
        }
    }

    suspend fun seedSampleExpertsIfEmpty() {
        ensureDefaultCategoriesForCurrentUser()
    }
}
