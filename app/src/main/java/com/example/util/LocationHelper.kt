package com.example.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.Locale
import java.util.regex.Pattern
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object LocationHelper {

    /**
     * Calculates distance in kilometers between two coordinates using the Haversine formula.
     */
    fun calculateDistanceKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371.0 // Earth radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }

    /**
     * Estimates travel time in minutes based on urban technician bike travel (average 24 km/h + 3 min traffic/prep buffer).
     */
    fun estimateTravelTimeMinutes(distanceKm: Double): Int {
        if (distanceKm <= 0.2) return 2
        val avgSpeedKmh = 24.0
        val travelHours = distanceKm / avgSpeedKmh
        val minutes = (travelHours * 60).toInt() + 3
        return minutes.coerceAtLeast(3)
    }

    /**
     * Formats distance nicely (e.g. "850 m" or "2.4 km").
     */
    fun formatDistance(distanceKm: Double): String {
        return if (distanceKm < 1.0) {
            val meters = (distanceKm * 1000).toInt()
            "$meters m"
        } else {
            String.format(Locale.US, "%.1f km", distanceKm)
        }
    }

    /**
     * Generates a direct Google Maps pin link.
     */
    fun createGoogleMapsUrl(latitude: Double, longitude: Double): String {
        return "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
    }

    /**
     * Smart parser to extract Latitude and Longitude from raw text, coordinates, or Google Maps URL.
     * Supports formats like:
     * - "28.5708, 77.3261"
     * - "https://maps.google.com/?q=28.5708,77.3261"
     * - "https://www.google.com/maps/@28.5708,77.3261,15z"
     * - "lat: 28.5708 lng: 77.3261"
     */
    fun parseCoordinatesFromText(input: String): Pair<Double, Double>? {
        if (input.isBlank()) return null

        // 1. Check for lat/lng query in URL e.g. query=lat,lng or q=lat,lng
        val urlQueryPattern = Pattern.compile("[?&](?:q|query|ll)=([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)")
        val urlMatcher = urlQueryPattern.matcher(input)
        if (urlMatcher.find()) {
            val lat = urlMatcher.group(1)?.toDoubleOrNull()
            val lng = urlMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null) return Pair(lat, lng)
        }

        // 2. Check for @lat,lng in google maps URLs
        val atPattern = Pattern.compile("@([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)")
        val atMatcher = atPattern.matcher(input)
        if (atMatcher.find()) {
            val lat = atMatcher.group(1)?.toDoubleOrNull()
            val lng = atMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null) return Pair(lat, lng)
        }

        // 2b. Check for !3d<lat>!4d<lng> format in web Google Maps URLs
        val d3d4Pattern = Pattern.compile("!3d([+-]?\\d+\\.\\d+)!4d([+-]?\\d+\\.\\d+)")
        val d3d4Matcher = d3d4Pattern.matcher(input)
        if (d3d4Matcher.find()) {
            val lat = d3d4Matcher.group(1)?.toDoubleOrNull()
            val lng = d3d4Matcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null) return Pair(lat, lng)
        }

        // 2c. Check for destination/daddr in navigation links
        val destPattern = Pattern.compile("(?:destination|daddr|saddr)=([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)")
        val destMatcher = destPattern.matcher(input)
        if (destMatcher.find()) {
            val lat = destMatcher.group(1)?.toDoubleOrNull()
            val lng = destMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null) return Pair(lat, lng)
        }

        // 3. Check for standard "28.1234, 77.5678" or "28.1234 77.5678"
        val generalPattern = Pattern.compile("([+-]?\\d{1,2}\\.\\d{2,10})\\s*[,\\s]\\s*([+-]?\\d{1,3}\\.\\d{2,10})")
        val generalMatcher = generalPattern.matcher(input)
        if (generalMatcher.find()) {
            val lat = generalMatcher.group(1)?.toDoubleOrNull()
            val lng = generalMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        return null
    }

    /**
     * Checks if ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION is granted.
     */
    fun isLocationPermissionGranted(context: Context): Boolean {
        val fine = androidx.core.content.ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        val coarse = androidx.core.content.ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        return fine || coarse
    }

    /**
     * Checks if device GPS / location provider is enabled in system settings.
     */
    fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? android.location.LocationManager
            ?: return false
        val gpsEnabled = try {
            locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
        } catch (e: Exception) {
            false
        }
        val networkEnabled = try {
            locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)
        } catch (e: Exception) {
            false
        }
        return gpsEnabled || networkEnabled
    }

    /**
     * Opens Application Details in system settings to let user grant location permission.
     */
    fun openAppSettings(context: Context) {
        val intent = android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = android.net.Uri.fromParts("package", context.packageName, null)
            addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    /**
     * Opens Device Location Settings so user can toggle GPS on.
     */
    fun openLocationSettings(context: Context) {
        val intent = android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
            addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    /**
     * Gets device current GPS location if location permission is granted.
     */
    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation(
        context: Context,
        onSuccess: (Location) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val fusedClient = LocationServices.getFusedLocationProviderClient(context)
            val cts = CancellationTokenSource()
            fusedClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cts.token)
                .addOnSuccessListener { loc ->
                    if (loc != null) {
                        onSuccess(loc)
                    } else {
                        // Fallback to last known location
                        fusedClient.lastLocation.addOnSuccessListener { lastLoc ->
                            if (lastLoc != null) {
                                onSuccess(lastLoc)
                            } else {
                                onError("GPS location not available, using default city center.")
                            }
                        }.addOnFailureListener {
                            onError("Could not get location: ${it.localizedMessage}")
                        }
                    }
                }
                .addOnFailureListener { e ->
                    onError("Failed to get GPS location: ${e.localizedMessage}")
                }
        } catch (e: Exception) {
            onError("GPS Error: ${e.message}")
        }
    }
}
