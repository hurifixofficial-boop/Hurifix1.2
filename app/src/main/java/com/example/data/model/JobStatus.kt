package com.example.data.model

enum class JobStatus(val displayName: String) {
    PENDING("Pending"),
    DISPATCHED("Dispatched"),
    PROCESSING("Processing"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    companion object {
        fun fromString(value: String?): JobStatus {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: PENDING
        }
    }
}
