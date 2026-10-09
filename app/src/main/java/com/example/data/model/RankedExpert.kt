package com.example.data.model

data class RankedExpert(
    val expert: ExpertEntity,
    val distanceKm: Double = 0.0,
    val rating: Float = 5.0f,
    val completedJobsCount: Int = 0,
    val isAvailable: Boolean = true
)
