package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.DispatchRepository

class DispatchViewModel(val repository: DispatchRepository) : ViewModel() {
    fun onUserLoggedIn(phone: String) {}
    fun onUserLoggedOut() {}
}

class DispatchViewModelFactory(private val repository: DispatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DispatchViewModel(repository) as T
    }
}
