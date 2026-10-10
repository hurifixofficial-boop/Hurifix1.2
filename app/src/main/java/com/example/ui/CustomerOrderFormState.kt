package com.example.ui

data class CustomerOrderFormState(
    val name: String = "",
    val phone: String = "",
    val serviceType: String = "",
    val issueDescription: String = "",
    val address: String = "",
    val rawLocationInput: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val hasValidLocation: Boolean = false
)
