package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

enum class JobStatus(val label: String, val hindiLabel: String) {
    PENDING("Pending", "लंबित"),
    PROCESSING("Processing", "प्रक्रिया में"),
    COMPLETED("Completed", "पूर्ण"),
    CANCELLED("Cancelled", "रद्द")
}

@Serializable
@Entity(tableName = "customer_jobs")
data class CustomerJobEntity(
    @PrimaryKey
    val id: String,
    val serviceType: String,
    val status: String = JobStatus.PENDING.name,
    val customerName: String,
    val customerPhone: String,
    val issueDescription: String,
    val address: String,
    val assignedExpertName: String? = null,
    val assignedExpertPhone: String? = null,
    val created_by_user_name: String? = null,
    val created_by_designation: String? = null,
    val managed_by_user_name: String? = null,
    val managed_by_designation: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)
