package com.example.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Centralized utility for graceful Offline & Network Error Handling across the app.
 * Whenever network connectivity is lost, blocked by custom DNS, or requests fail
 * (such as UNAVAILABLE host errors), catches exceptions gracefully and returns
 * the user-friendly message: "Please check your internet connection".
 */
object NetworkErrorHandler {
    private const val TAG = "NetworkErrorHandler"
    const val NETWORK_ERROR_USER_MESSAGE = "Please check your internet connection"

    /**
     * Checks if the given throwable or its underlying causes are related to network unavailability,
     * DNS host resolution failures, timeouts, socket closures, or Firestore UNAVAILABLE errors.
     */
    fun isNetworkOrDnsError(throwable: Throwable?): Boolean {
        if (throwable == null) return false
        var current: Throwable? = throwable
        var depth = 0
        while (current != null && depth < 10) {
            if (current is UnknownHostException ||
                current is SocketTimeoutException ||
                current is ConnectException ||
                current is NoRouteToHostException ||
                current is SocketException ||
                current is InterruptedIOException ||
                current is FirebaseNetworkException
            ) {
                return true
            }

            if (current is FirebaseFirestoreException) {
                if (current.code == FirebaseFirestoreException.Code.UNAVAILABLE ||
                    current.code == FirebaseFirestoreException.Code.DEADLINE_EXCEEDED
                ) {
                    return true
                }
            }

            val msg = current.message?.lowercase() ?: ""
            if (msg.contains("unable to resolve host") ||
                msg.contains("unavailable") ||
                msg.contains("failed to connect") ||
                msg.contains("no address associated with hostname") ||
                msg.contains("network error") ||
                msg.contains("network is unreachable") ||
                msg.contains("connection refused") ||
                msg.contains("connection reset") ||
                msg.contains("timeout") ||
                msg.contains("unknownhostexception") ||
                msg.contains("dns") ||
                msg.contains("sslhandshake") ||
                msg.contains("unreachable") ||
                msg.contains("end of stream") ||
                msg.contains("software caused connection abort") ||
                msg.contains("authoris") ||
                msg.contains("authoriz") ||
                msg.contains("hostname") ||
                msg.contains("unauthorized-domain") ||
                msg.contains("app check")
            ) {
                return true
            }

            current = current.cause
            depth++
        }
        return false
    }

    /**
     * Checks if a message represents an authorized hostname or technical network failure.
     */
    fun isAuthorizedHostnameOrNetworkError(message: String?): Boolean {
        if (message.isNullOrBlank()) return false
        val lower = message.lowercase()
        return lower.contains("authoris") ||
                lower.contains("authoriz") ||
                lower.contains("hostname") ||
                lower.contains("domain") ||
                lower.contains("unavailable") ||
                lower.contains("unable to resolve host") ||
                lower.contains("unknownhostexception") ||
                lower.contains("dns") ||
                lower.contains("no address associated") ||
                lower.contains("network") ||
                lower.contains("failed to connect") ||
                lower.contains("connection refused") ||
                lower.contains("timeout")
    }

    /**
     * Sanitizes any error message: if it is a technical network/DNS/host/authorized hostname error,
     * returns "Please check your internet connection". Otherwise returns the sanitized message.
     */
    fun sanitizeMessage(message: String?): String {
        if (message.isNullOrBlank()) return NETWORK_ERROR_USER_MESSAGE
        val lower = message.lowercase()
        if (isAuthorizedHostnameOrNetworkError(lower)) {
            return NETWORK_ERROR_USER_MESSAGE
        }
        return message
    }

    /**
     * Extracts a friendly error message from a Throwable.
     * Technical network / DNS / UNAVAILABLE exceptions are cleanly replaced with
     * "Please check your internet connection".
     */
    fun getFriendlyErrorMessage(throwable: Throwable?, defaultMsg: String? = null): String {
        if (throwable == null) {
            return defaultMsg?.let { sanitizeMessage(it) } ?: NETWORK_ERROR_USER_MESSAGE
        }
        if (isNetworkOrDnsError(throwable)) {
            return NETWORK_ERROR_USER_MESSAGE
        }
        val raw = throwable.localizedMessage ?: throwable.message ?: defaultMsg ?: "Operation failed"
        return sanitizeMessage(raw)
    }

    /**
     * Displays a clean, user-friendly Toast at the bottom.
     */
    fun showNetworkErrorToast(context: Context) {
        SoundManager.playError()
        Toast.makeText(context, NETWORK_ERROR_USER_MESSAGE, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Real-time network connectivity monitor using Android ConnectivityManager.
 */
object NetworkMonitor {
    private const val TAG = "NetworkMonitor"
    private val _isConnected = MutableStateFlow(true)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val _networkErrorEvent = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val networkErrorEvent: SharedFlow<String> = _networkErrorEvent.asSharedFlow()

    private var isInitialized = false

    fun initialize(context: Context) {
        if (isInitialized) return
        isInitialized = true

        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                    ?: return

            // Initial check
            val activeNetwork = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            val hasInternet = capabilities != null && (
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    )
            _isConnected.value = hasInternet

            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            connectivityManager.registerNetworkCallback(
                request,
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        _isConnected.value = true
                    }

                    override fun onLost(network: Network) {
                        _isConnected.value = false
                        SoundManager.playError()
                        _networkErrorEvent.tryEmit(NetworkErrorHandler.NETWORK_ERROR_USER_MESSAGE)
                    }

                    override fun onUnavailable() {
                        _isConnected.value = false
                        SoundManager.playError()
                        _networkErrorEvent.tryEmit(NetworkErrorHandler.NETWORK_ERROR_USER_MESSAGE)
                    }
                }
            )
        } catch (e: Exception) {
            Log.w(TAG, "NetworkMonitor init note: ${e.message}")
        }
    }
}
