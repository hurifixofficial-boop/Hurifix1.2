package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experts")
data class ExpertEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val phone: String,
    val category: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val isAvailable: Boolean = true,
    val rating: Float = 4.8f,
    val ratingSum: Float = 4.8f,
    val totalRatingsCount: Int = 1,
    val completedJobsCount: Int = 0,
    val cancelledJobsCount: Int = 0,
    val isWelcomeMessageSent: Boolean = false,
    val isDeleted: Boolean = false,
    val deletedAt: Long? = null,
    val added_by_user_id: String? = null,
    val added_by_user_name: String? = null,
    val added_by_designation: String? = null,
    val profilePicUrl: String? = null,
    val created_at_timestamp: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis(),
    val last_updated: Long = System.currentTimeMillis(),
    val is_synced: Boolean = false
)

data class RankedExpert(
    val expert: ExpertEntity,
    val distanceKm: Double,
    val travelTimeMinutes: Int
)
