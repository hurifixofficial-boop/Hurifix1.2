package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CustomerJobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerJobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: CustomerJobEntity): Long

    @Update
    suspend fun updateJob(job: CustomerJobEntity)

    @Query("SELECT * FROM customer_jobs WHERE isDeleted = 0 ORDER BY id DESC")
    fun getAllActiveJobs(): Flow<List<CustomerJobEntity>>

    @Query("SELECT * FROM customer_jobs WHERE isDeleted = 1 ORDER BY deletedAt DESC")
    fun getRecycleBinJobs(): Flow<List<CustomerJobEntity>>

    @Query("SELECT * FROM customer_jobs WHERE id = :id")
    suspend fun getJobById(id: Long): CustomerJobEntity?

    @Query("DELETE FROM customer_jobs WHERE id = :id")
    suspend fun deletePermanently(id: Long)

    @Query("DELETE FROM customer_jobs WHERE isDeleted = 1")
    suspend fun clearRecycleBin()

    @Query("UPDATE customer_jobs SET managed_by_user_id = :userId, managed_by_user_name = :userName, managed_by_designation = :designation, last_updated = :timestamp WHERE id = :jobId")
    suspend fun updateJobManager(jobId: Long, userId: String, userName: String, designation: String?, timestamp: Long)

    @Query("SELECT * FROM customer_jobs WHERE customerPhone = :phone AND createdAt >= :sinceTimestamp ORDER BY id DESC LIMIT 1")
    suspend fun findRecentOrderByPhone(phone: String, sinceTimestamp: Long): CustomerJobEntity?

    @Query("UPDATE customer_jobs SET status = :status, assignedExpertId = :expertId, assignedExpertName = :expertName, assignedExpertPhone = :expertPhone, distanceKmAtDispatch = :distanceKm, assigned_at_timestamp = :assignedAt WHERE id = :jobId")
    suspend fun updateJobDispatch(jobId: Long, status: String, expertId: Long, expertName: String, expertPhone: String, distanceKm: Double, assignedAt: Long)

    @Query("UPDATE customer_jobs SET is_synced = 1 WHERE id = :jobId")
    suspend fun markJobSynced(jobId: Long)

    @Query("SELECT * FROM customer_jobs WHERE is_synced = 0")
    suspend fun getUnsyncedJobs(): List<CustomerJobEntity>
}
