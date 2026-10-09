package com.example.data.model

enum class JobStatus {
    PENDING, PROCESSING, COMPLETED, CANCELLED
}

data class RankedExpert(
    val expert: ExpertEntity,
    val distanceKm: Double,
    val score: Double = 0.0
)

data class CustomerFormState(
    val name: String = "",
    val phone: String = "",
    val serviceType: String = "",
    val issueDescription: String = "",
    val address: String = "",
    val rawLocationInput: String = "",
    val hasValidLocation: Boolean = false
)
