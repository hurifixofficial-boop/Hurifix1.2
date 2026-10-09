package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_jobs")
data class CustomerJobEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerName: String = "",
    val customerPhone: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val serviceType: String = "",
    val issueDescription: String = "",
    val status: String = "DISPATCHED",
    val assignedExpertId: Long? = null,
    val assignedExpertName: String? = null,
    val assignedExpertPhone: String? = null,
    val assigned_technician_id: Long? = null,
    val assigned_technician_name: String? = null,
    val distanceKmAtDispatch: Double? = null,
    val ratingGiven: Float? = null,
    val reviewFeedback: String? = null,
    val isCustomerNotifiedOnAssign: Boolean = false,
    val isExpertNotified: Boolean = false,
    val isCustomerNotifiedOnCompletion: Boolean = false,
    val assignMessageLaterDismissedAt: Long? = null,
    val created_by_user_id: String? = null,
    val created_by_user_name: String? = null,
    val created_by_designation: String? = null,
    val managed_by_user_id: String? = null,
    val managed_by_user_name: String? = null,
    val managed_by_designation: String? = null,
    val assigned_at_timestamp: Long? = null,
    val completedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val last_updated: Long = System.currentTimeMillis(),
    val is_synced: Boolean = false,
    val isDeleted: Boolean = false,
    val deletedAt: Long? = null
)
