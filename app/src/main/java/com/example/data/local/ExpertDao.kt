package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.ExpertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpertDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpert(expert: ExpertEntity): Long

    @Update
    suspend fun updateExpert(expert: ExpertEntity)

    @Query("SELECT * FROM experts WHERE isDeleted = 0 ORDER BY name ASC")
    fun getAllActiveExperts(): Flow<List<ExpertEntity>>

    @Query("SELECT * FROM experts WHERE isDeleted = 1 ORDER BY deletedAt DESC")
    fun getRecycleBinExperts(): Flow<List<ExpertEntity>>

    @Query("SELECT * FROM experts WHERE id = :id")
    suspend fun getExpertById(id: Long): ExpertEntity?

    @Query("DELETE FROM experts WHERE id = :id")
    suspend fun deletePermanently(id: Long)

    @Query("DELETE FROM experts WHERE isDeleted = 1")
    suspend fun clearRecycleBin()
}
