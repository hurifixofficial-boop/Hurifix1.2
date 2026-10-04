package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ExpertCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpertCategoryDao {
    @Query("SELECT * FROM expert_categories ORDER BY isDefault DESC, name ASC")
    fun getAllCategories(): Flow<List<ExpertCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: ExpertCategoryEntity): Long

    @Delete
    suspend fun deleteCategory(category: ExpertCategoryEntity)

    @Query("DELETE FROM expert_categories WHERE name = :name AND isDefault = 0")
    suspend fun deleteCategoryByName(name: String)

    @Query("SELECT COUNT(*) FROM expert_categories")
    suspend fun getCategoryCount(): Int
}
