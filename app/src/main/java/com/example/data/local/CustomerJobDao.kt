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
}
