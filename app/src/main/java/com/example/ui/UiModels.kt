package com.example.ui

data class CustomerFormState(
    val name: String = "",
    val phone: String = "",
    val serviceType: String = "",
    val issueDescription: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val hasValidLocation: Boolean = false,
    val rawLocationInput: String = ""
)

enum class MainTab(val title: String) {
    CUSTOMER_ORDERS("Customer Orders"),
    EXPERTS("Experts Directory")
}

enum class CustomerSubTab(val title: String) {
    DISPATCH_ORDER("Dispatch Order"),
    ORDERS_LIST("Orders List")
}

enum class OrderStatusTab(val title: String) {
    PENDING("Pending"),
    PROCESSING("Processing"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled")
}
