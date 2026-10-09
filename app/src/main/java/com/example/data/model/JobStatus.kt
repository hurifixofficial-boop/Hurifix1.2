package com.example.data.model

enum class JobStatus(val statusName: String) {
    PENDING("Pending"),
    PROCESSING("Assigned"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    companion object {
        val ASSIGNED = PROCESSING
    }
}
