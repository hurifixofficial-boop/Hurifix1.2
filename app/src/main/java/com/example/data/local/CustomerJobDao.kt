package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CustomerJobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerJobDao {
    @Query("SELECT * FROM customer_jobs WHERE isDeleted = 0 ORDER BY createdAt DESC")
    fun getAllJobs(): Flow<List<CustomerJobEntity>>

    @Query("SELECT * FROM customer_jobs WHERE status = :status AND isDeleted = 0 ORDER BY createdAt DESC")
    fun getJobsByStatus(status: String): Flow<List<CustomerJobEntity>>

    @Query("SELECT * FROM customer_jobs WHERE assignedExpertId = :expertId AND isDeleted = 0 ORDER BY createdAt DESC")
    fun getJobsForExpert(expertId: Long): Flow<List<CustomerJobEntity>>

    @Query("SELECT * FROM customer_jobs WHERE isDeleted = 1 ORDER BY deletedAt DESC")
    fun getDeletedJobs(): Flow<List<CustomerJobEntity>>

    @Query("UPDATE customer_jobs SET isDeleted = 1, deletedAt = :deletedAt, last_updated = :deletedAt, is_synced = 0 WHERE id = :jobId")
    suspend fun moveToRecycleBin(jobId: Long, deletedAt: Long = System.currentTimeMillis())

    @Query("UPDATE customer_jobs SET isDeleted = 0, deletedAt = NULL, last_updated = :restoredAt, is_synced = 0 WHERE id = :jobId")
    suspend fun restoreJobFromRecycleBin(jobId: Long, restoredAt: Long = System.currentTimeMillis())

    @Query("DELETE FROM customer_jobs WHERE isDeleted = 1 AND deletedAt <= :cutoffTimestamp")
    suspend fun purgeJobsOlderThan(cutoffTimestamp: Long)

    @Query("DELETE FROM customer_jobs WHERE isDeleted = 1")
    suspend fun clearRecycleBin()

    @Query("SELECT * FROM customer_jobs WHERE id = :id")
    suspend fun getJobById(id: Long): CustomerJobEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: CustomerJobEntity): Long

    @Update
    suspend fun updateJob(job: CustomerJobEntity)

    @Delete
    suspend fun deleteJob(job: CustomerJobEntity)

    @Query("DELETE FROM customer_jobs WHERE id = :id")
    suspend fun deleteJobById(id: Long)

    @Query("UPDATE customer_jobs SET status = :status, assignedExpertId = :expertId, assignedExpertName = :expertName, assignedExpertPhone = :expertPhone, distanceKmAtDispatch = :distanceKm, assigned_technician_id = :expertId, assigned_technician_name = :expertName, assigned_at_timestamp = :assignedAt, last_updated = :assignedAt WHERE id = :jobId")
    suspend fun updateJobDispatch(
        jobId: Long,
        status: String,
        expertId: Long,
        expertName: String,
        expertPhone: String,
        distanceKm: Double,
        assignedAt: Long = System.currentTimeMillis()
    )

    @Query("UPDATE customer_jobs SET managed_by_user_id = :userId, managed_by_user_name = :userName, managed_by_designation = :userDesignation, last_updated = :now WHERE id = :jobId")
    suspend fun updateJobManager(jobId: Long, userId: String, userName: String, userDesignation: String? = null, now: Long = System.currentTimeMillis())

    @Query("UPDATE customer_jobs SET status = :status WHERE id = :jobId")
    suspend fun updateJobStatus(jobId: Long, status: String)

    @Query("UPDATE customer_jobs SET status = 'PENDING', assignedExpertId = NULL, assignedExpertName = NULL, assignedExpertPhone = NULL, distanceKmAtDispatch = NULL, isExpertNotified = 0, isCustomerNotifiedOnAssign = 0 WHERE id = :jobId")
    suspend fun unassignExpertFromJob(jobId: Long)

    @Query("UPDATE customer_jobs SET status = :status, ratingGiven = :rating, reviewFeedback = :feedback, completedAt = :completedAt WHERE id = :jobId")
    suspend fun completeOrCancelJobWithReview(
        jobId: Long,
        status: String,
        rating: Float,
        feedback: String?,
        completedAt: Long
    )

    @Query("UPDATE customer_jobs SET isExpertNotified = :sent WHERE id = :jobId")
    suspend fun updateExpertNotified(jobId: Long, sent: Boolean)

    @Query("UPDATE customer_jobs SET isCustomerNotifiedOnAssign = :sent WHERE id = :jobId")
    suspend fun updateCustomerNotifiedOnAssign(jobId: Long, sent: Boolean)

    @Query("UPDATE customer_jobs SET isCustomerNotifiedOnCompletion = :sent WHERE id = :jobId")
    suspend fun updateCustomerNotifiedOnCompletion(jobId: Long, sent: Boolean)

    @Query("UPDATE customer_jobs SET assignMessageLaterDismissedAt = :time WHERE id = :jobId")
    suspend fun updateMessageDismissedAt(jobId: Long, time: Long?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobs(jobs: List<CustomerJobEntity>)

    @Query("DELETE FROM customer_jobs")
    suspend fun clearAllJobs()

    @Query("SELECT COUNT(*) FROM customer_jobs")
    suspend fun getJobCount(): Int

    @Query("SELECT * FROM customer_jobs WHERE customerPhone = :phone AND createdAt >= :sinceTimestamp AND isDeleted = 0 LIMIT 1")
    suspend fun findRecentOrderByPhone(phone: String, sinceTimestamp: Long): CustomerJobEntity?

    @Query("SELECT * FROM customer_jobs WHERE is_synced = 0")
    suspend fun getUnsyncedJobs(): List<CustomerJobEntity>

    @Query("UPDATE customer_jobs SET is_synced = 1 WHERE id = :jobId")
    suspend fun markJobSynced(jobId: Long)

    @Query("SELECT * FROM customer_jobs")
    suspend fun getAllJobsDirectList(): List<CustomerJobEntity>
}
