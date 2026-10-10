package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ui.MainTab
import com.example.ui.CustomerSubTab
import com.example.ui.OrderStatusTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    val currentMainTab = MutableStateFlow(MainTab.CUSTOMER_ORDERS)
    val currentCustomerSubTab = MutableStateFlow(CustomerSubTab.ORDERS)
    val currentOrderStatusTab = MutableStateFlow(OrderStatusTab.PENDING)
    
    // Cloudinary Integration state
    val isCloudinaryEnabled = MutableStateFlow(false)
    
    fun selectMainTab(tab: MainTab) {
        currentMainTab.value = tab
    }
    
    fun selectCustomerSubTab(tab: CustomerSubTab) {
        currentCustomerSubTab.value = tab
    }
    
    fun selectOrderStatusTab(tab: OrderStatusTab) {
        currentOrderStatusTab.value = tab
    }
}
