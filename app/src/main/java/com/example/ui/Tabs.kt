package com.example.ui

enum class MainTab {
    CUSTOMER_ORDERS,
    EXPERTS
}

enum class CustomerSubTab {
    DISPATCH_ORDER,
    ORDERS
}

enum class OrderStatusTab(val statusName: String, val label: String) {
    PENDING("Pending", "Pending"),
    PROCESSING("Assigned", "Processing"),
    COMPLETED("Completed", "Completed"),
    CANCELLED("Cancelled", "Cancelled")
}
