package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_jobs")
data class CustomerJobEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerName: String,
    val customerPhone: String,
    val serviceType: String,
    val issueDescription: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val status: String = JobStatus.PENDING.name,
    val assignedExpertId: Long? = null,
    val assignedExpertName: String? = null,
    val assignedExpertPhone: String? = null,
    val distanceKmAtDispatch: Double? = null,
    val ratingGiven: Float? = null,
    val reviewFeedback: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null,
    val isExpertNotified: Boolean = false,
    val isCustomerNotifiedOnAssign: Boolean = false,
    val isCustomerNotifiedOnCompletion: Boolean = false,
    val assignMessageLaterDismissedAt: Long? = null,
    val isDeleted: Boolean = false,
    val deletedAt: Long? = null,
    val last_updated: Long = System.currentTimeMillis(),
    val is_synced: Boolean = false
)

enum class JobStatus(val label: String, val hindiLabel: String) {
    PENDING("Pending", "Pending"),
    PROCESSING("Processing", "Processing"),
    COMPLETED("Completed", "Completed"),
    CANCELLED("Cancelled", "Cancelled")
}
