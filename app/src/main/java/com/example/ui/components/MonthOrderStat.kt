package com.example.ui.components

data class MonthOrderStat(
    val monthYearKey: String = "",
    val displayMonth: String = "",
    val totalOrders: Int = 0,
    val completedOrders: Int = 0,
    val cancelledOrders: Int = 0,
    val processingOrders: Int = 0,
    val pendingOrders: Int = 0
)
