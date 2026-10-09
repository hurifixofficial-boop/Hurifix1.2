package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.ExpertCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpertCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: ExpertCategoryEntity): Long

    @Update
    suspend fun updateCategory(category: ExpertCategoryEntity)

    @Query("SELECT * FROM expert_categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<ExpertCategoryEntity>>

    @Query("DELETE FROM expert_categories WHERE id = :id")
    suspend fun deleteCategory(id: Long)
}
