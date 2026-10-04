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
    val createdAt: Long = System.currentTimeMillis()
)

data class RankedExpert(
    val expert: ExpertEntity,
    val distanceKm: Double,
    val travelTimeMinutes: Int
)
