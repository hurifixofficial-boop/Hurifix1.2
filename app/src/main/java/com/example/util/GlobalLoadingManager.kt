package com.example.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicInteger

/**
 * Global Loading Manager for displaying a full-screen, interaction-blocking
 * loading indicator during background tasks, API calls, image uploads, and data fetching.
 */
object GlobalLoadingManager {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _loadingMessage = MutableStateFlow("Loading...")
    val loadingMessage: StateFlow<String> = _loadingMessage.asStateFlow()

    private val activeTasks = AtomicInteger(0)

    fun show(message: String = "Loading...") {
        _loadingMessage.value = message
        if (activeTasks.incrementAndGet() > 0) {
            _isLoading.value = true
        }
    }

    fun hide() {
        if (activeTasks.decrementAndGet() <= 0) {
            activeTasks.set(0)
            _isLoading.value = false
            _loadingMessage.value = "Loading..."
        }
    }

    fun reset() {
        activeTasks.set(0)
        _isLoading.value = false
        _loadingMessage.value = "Loading..."
    }

    suspend fun <T> withLoading(message: String = "Loading...", block: suspend () -> T): T {
        show(message)
        return try {
            block()
        } finally {
            hide()
        }
    }
}
