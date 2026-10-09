package com.example.util

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

object LocationHelper {
    fun isLocationPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true ||
               locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true
    }

    fun openAppSettings(context: Context) {
        val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

    fun openLocationSettings(context: Context) {
        val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

    fun fetchCurrentLocation(
        context: Context,
        onSuccess: (Double, Double) -> Unit,
        onError: (String) -> Unit
    ) {
        if (!isLocationPermissionGranted(context)) {
            onError("Location permission not granted")
            return
        }

        try {
            val fusedClient = LocationServices.getFusedLocationProviderClient(context)
            val cts = CancellationTokenSource()
            fusedClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cts.token)
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        onSuccess(location.latitude, location.longitude)
                    } else {
                        fusedClient.lastLocation
                            .addOnSuccessListener { lastLoc: Location? ->
                                if (lastLoc != null) {
                                    onSuccess(lastLoc.latitude, lastLoc.longitude)
                                } else {
                                    onError("Unable to fetch current GPS coordinates")
                                }
                            }
                            .addOnFailureListener {
                                onError(it.message ?: "Failed to get location")
                            }
                    }
                }
                .addOnFailureListener {
                    onError(it.message ?: "Failed to get location")
                }
        } catch (e: SecurityException) {
            onError("Location permission error: ${e.message}")
        } catch (e: Exception) {
            onError(e.message ?: "Unknown location error")
        }
    }

    fun parseCoordinatesFromText(text: String): Pair<Double, Double>? {
        if (text.isBlank()) return null
        val clean = text.trim()
        val regex = Regex("(-?\\d+\\.\\d+)[,\\s]+(-?\\d+\\.\\d+)")
        val match = regex.find(clean) ?: return null
        return try {
            val lat = match.groupValues[1].toDouble()
            val lon = match.groupValues[2].toDouble()
            if (lat in -90.0..90.0 && lon in -180.0..180.0) {
                Pair(lat, lon)
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2.0) * Math.sin(dLon / 2.0)
        val c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a))
        return 6371.0 * c
    }
}
