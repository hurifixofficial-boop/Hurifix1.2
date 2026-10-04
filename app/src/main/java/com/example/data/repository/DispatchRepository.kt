package com.example.data.repository

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class DispatchRepository(
    private val expertDao: ExpertDao,
    private val jobDao: CustomerJobDao,
    private val expertCategoryDao: ExpertCategoryDao,
    private val technicianDao: TechnicianDao? = null,
    private val customerDao: CustomerDao? = null
) {
    val allExperts: Flow<List<ExpertEntity>> = expertDao.getAllExperts()
    val availableExperts: Flow<List<ExpertEntity>> = expertDao.getAvailableExperts()
    val allJobs: Flow<List<CustomerJobEntity>> = jobDao.getAllJobs()
    val allCategories: Flow<List<ExpertCategoryEntity>> = expertCategoryDao.getAllCategories()
    val deletedJobs: Flow<List<CustomerJobEntity>> = jobDao.getDeletedJobs()
    val deletedExperts: Flow<List<ExpertEntity>> = expertDao.getDeletedExperts()

    fun getJobsForExpert(expertId: Long): Flow<List<CustomerJobEntity>> = jobDao.getJobsForExpert(expertId)

    suspend fun moveJobToRecycleBin(jobId: Long) {
        jobDao.moveToRecycleBin(jobId, System.currentTimeMillis())
    }

    suspend fun restoreJobFromRecycleBin(jobId: Long) {
        jobDao.restoreJobFromRecycleBin(jobId)
    }

    suspend fun deleteJobPermanently(jobId: Long) {
        jobDao.deleteJobById(jobId)
    }

    suspend fun moveExpertToRecycleBin(expertId: Long) {
        expertDao.moveToRecycleBin(expertId, System.currentTimeMillis())
    }

    suspend fun restoreExpertFromRecycleBin(expertId: Long) {
        expertDao.restoreExpertFromRecycleBin(expertId)
    }

    suspend fun deleteExpertPermanently(expertId: Long) {
        expertDao.deleteExpertById(expertId)
    }

    suspend fun purgeRecycleBinOlderThan30Days() {
        val thirtyDaysAgo = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000)
        jobDao.purgeJobsOlderThan(thirtyDaysAgo)
        expertDao.purgeExpertsOlderThan(thirtyDaysAgo)
    }

    suspend fun clearRecycleBin() {
        jobDao.clearRecycleBin()
        expertDao.clearRecycleBin()
    }

    suspend fun updateWelcomeMessageSent(expertId: Long, sent: Boolean) {
        expertDao.updateWelcomeMessageSent(expertId, sent)
    }

    // Technician and Customer Flow properties
    val allTechnicians: Flow<List<TechnicianEntity>>? = technicianDao?.getAllTechnicians()
    val allCustomers: Flow<List<CustomerEntity>>? = customerDao?.getAllCustomers()

    // Category operations
    suspend fun insertCategory(name: String, isDefault: Boolean = false): Long {
        val trimmed = name.trim()
        if (trimmed.isBlank()) return 0L
        return expertCategoryDao.insertCategory(
            ExpertCategoryEntity(name = trimmed, isDefault = isDefault)
        )
    }

    suspend fun deleteCategory(category: ExpertCategoryEntity) {
        expertCategoryDao.deleteCategory(category)
    }

    suspend fun deleteCategoryByName(name: String) {
        expertCategoryDao.deleteCategoryByName(name)
    }

    // Technician operations
    suspend fun insertTechnician(technician: TechnicianEntity): Long {
        val id = technicianDao?.insertTechnician(technician) ?: 0L
        expertDao.insertExpert(
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
        technicianDao?.updateTechnician(technician)
    }

    suspend fun deleteTechnician(technician: TechnicianEntity) {
        technicianDao?.deleteTechnician(technician)
    }

    suspend fun getTechnicianById(id: Long): TechnicianEntity? = technicianDao?.getTechnicianById(id)

    // Customer operations
    suspend fun insertCustomer(customer: CustomerEntity): Long =
        customerDao?.insertCustomer(customer) ?: 0L

    suspend fun updateCustomer(customer: CustomerEntity) {
        customerDao?.updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: CustomerEntity) {
        customerDao?.deleteCustomer(customer)
    }

    suspend fun getCustomerById(id: Long): CustomerEntity? = customerDao?.getCustomerById(id)

    // Expert operations
    suspend fun insertExpert(expert: ExpertEntity): Long = expertDao.insertExpert(expert)

    suspend fun updateExpert(expert: ExpertEntity) = expertDao.updateExpert(expert)

    suspend fun deleteExpert(expert: ExpertEntity) = expertDao.deleteExpert(expert)

    // Customer Job / Order operations
    suspend fun insertJob(job: CustomerJobEntity): Long {
        val id = jobDao.insertJob(job)
        customerDao?.insertCustomer(
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
        jobDao.updateJob(job)
    }

    suspend fun unassignExpertFromJob(jobId: Long) {
        jobDao.unassignExpertFromJob(jobId)
    }

    suspend fun updateJobStatus(jobId: Long, status: JobStatus) {
        jobDao.updateJobStatus(jobId, status.name)
    }

    suspend fun assignJobToExpert(
        jobId: Long,
        expert: ExpertEntity,
        distanceKm: Double
    ) {
        jobDao.updateJobDispatch(
            jobId = jobId,
            status = JobStatus.PROCESSING.name,
            expertId = expert.id,
            expertName = expert.name,
            expertPhone = expert.phone,
            distanceKm = distanceKm
        )
    }

    suspend fun updateExpertNotified(jobId: Long, sent: Boolean) {
        jobDao.updateExpertNotified(jobId, sent)
    }

    suspend fun updateCustomerNotifiedOnAssign(jobId: Long, sent: Boolean) {
        jobDao.updateCustomerNotifiedOnAssign(jobId, sent)
    }

    suspend fun updateCustomerNotifiedOnCompletion(jobId: Long, sent: Boolean) {
        jobDao.updateCustomerNotifiedOnCompletion(jobId, sent)
    }

    suspend fun updateMessageDismissedAt(jobId: Long, time: Long?) {
        jobDao.updateMessageDismissedAt(jobId, time)
    }

    suspend fun completeOrCancelJobWithReview(
        jobId: Long,
        expertId: Long?,
        isCompleted: Boolean,
        rating: Float,
        feedback: String?
    ) {
        val newStatus = if (isCompleted) JobStatus.COMPLETED.name else JobStatus.CANCELLED.name
        jobDao.completeOrCancelJobWithReview(
            jobId = jobId,
            status = newStatus,
            rating = rating,
            feedback = feedback,
            completedAt = System.currentTimeMillis()
        )

        // Update expert's review metrics in database
        if (expertId != null && expertId > 0) {
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

    suspend fun deleteJob(job: CustomerJobEntity) = jobDao.deleteJob(job)

    suspend fun restoreDatabase(
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ) {
        if (jobs.isNotEmpty()) {
            jobDao.insertJobs(jobs)
        }
        if (experts.isNotEmpty()) {
            expertDao.insertExperts(experts)
        }
        if (categories.isNotEmpty()) {
            categories.forEach { expertCategoryDao.insertCategory(it) }
        }
    }

    /**
     * Calculates distance and travel time to all experts from customer coordinates,
     * sorted in ascending order (nearest expert first).
     */
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

    suspend fun seedSampleExpertsIfEmpty() {
        // Seed default categories: Electrician and Plumber as requested
        if (expertCategoryDao.getCategoryCount() == 0) {
            expertCategoryDao.insertCategory(
                ExpertCategoryEntity(name = "Electrician", isDefault = true)
            )
            expertCategoryDao.insertCategory(
                ExpertCategoryEntity(name = "Plumber", isDefault = true)
            )
        }

        if (expertDao.getExpertCount() == 0) {
            val sampleExperts = listOf(
                ExpertEntity(
                    name = "Ramesh Sharma",
                    phone = "9871234560",
                    category = "Electrician",
                    address = "Sector 18 Market, Main Road",
                    latitude = 28.5708,
                    longitude = 77.3261,
                    isAvailable = true,
                    rating = 4.9f,
                    ratingSum = 49.0f,
                    totalRatingsCount = 10,
                    completedJobsCount = 142
                ),
                ExpertEntity(
                    name = "Sunil Verma",
                    phone = "9899887760",
                    category = "Plumber",
                    address = "Near Metro Station, Sector 29",
                    latitude = 28.5630,
                    longitude = 77.3340,
                    isAvailable = true,
                    rating = 4.7f,
                    ratingSum = 47.0f,
                    totalRatingsCount = 10,
                    completedJobsCount = 95
                )
            )
            expertDao.insertExperts(sampleExperts)
        }

        if (jobDao.getJobCount() == 0) {
            val sampleJob = CustomerJobEntity(
                customerName = "Pooja Verma",
                customerPhone = "9988776655",
                serviceType = "AC Cooling Problem",
                issueDescription = "AC not cooling and making rattling sound in bedroom",
                address = "Flat 402, Royal Towers, Sector 19",
                latitude = 28.5750,
                longitude = 77.3290,
                status = JobStatus.PENDING.name
            )
            jobDao.insertJob(sampleJob)

            val now = System.currentTimeMillis()
            val sampleCompletedJob = CustomerJobEntity(
                customerName = "Rajesh Gupta",
                customerPhone = "9812345670",
                serviceType = "Water Heater / Geyser Wiring",
                issueDescription = "Geyser tripping MCB constantly in bathroom",
                address = "Plot 88, Sector 21 Noida",
                latitude = 28.5820,
                longitude = 77.3320,
                status = JobStatus.COMPLETED.name,
                assignedExpertId = 1,
                assignedExpertName = "Ramesh Sharma",
                assignedExpertPhone = "9871234560",
                distanceKmAtDispatch = 1.6,
                ratingGiven = 5.0f,
                reviewFeedback = "Very quick response. Replaced burnt heating element wiring and tested within an hour. Excellent service!",
                createdAt = now - (68 * 60 * 1000),
                completedAt = now,
                isExpertNotified = true,
                isCustomerNotifiedOnAssign = true,
                isCustomerNotifiedOnCompletion = true
            )
            jobDao.insertJob(sampleCompletedJob)
        }
    }
}
