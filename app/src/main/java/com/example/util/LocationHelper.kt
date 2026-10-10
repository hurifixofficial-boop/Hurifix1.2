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
     * Checks if the given string represents a Google Maps URL (full or shortened).
     */
    fun isGoogleMapsUrl(input: String): Boolean {
        val lower = input.trim().lowercase()
        return lower.contains("maps.google.") ||
                lower.contains("google.com/maps") ||
                (lower.contains("google.") && lower.contains("/maps")) ||
                lower.contains("goo.gl/maps") ||
                lower.contains("maps.app.goo.gl") ||
                lower.startsWith("geo:")
    }

    /**
     * Smart parser to extract Latitude and Longitude from raw text, coordinates, or Google Maps URL.
     * Supports formats like:
     * - "28.5708, 77.3261" or "28.5708 77.3261"
     * - "https://maps.google.com/?q=28.5708,77.3261"
     * - "https://maps.google.com/?q=28.5708%2C77.3261"
     * - "https://maps.google.com/?q=loc:28.5708+77.3261"
     * - "https://www.google.com/maps/search/?api=1&query=28.5708,77.3261"
     * - "https://www.google.com/maps/search/28.5708,77.3261"
     * - "https://www.google.com/maps/@28.5708,77.3261,15z"
     * - "https://www.google.com/maps/place/Shop/@28.5708,77.3261,17z/data=!3d28.5708!4d77.3261"
     * - "geo:28.5708,77.3261" or "geo:0,0?q=28.5708,77.3261"
     * - "lat: 28.5708 lng: 77.3261"
     */
    fun parseCoordinatesFromText(input: String): Pair<Double, Double>? {
        if (input.isBlank()) return null
        val trimmed = input.trim()

        val direct = extractCoordinatesInternal(trimmed)
        if (direct != null) return direct

        val decoded = try {
            java.net.URLDecoder.decode(trimmed, "UTF-8")
        } catch (_: Exception) {
            trimmed
        }

        return if (decoded != trimmed) extractCoordinatesInternal(decoded) else null
    }

    private fun extractCoordinatesInternal(text: String): Pair<Double, Double>? {
        if (text.isBlank()) return null

        // 1. Check for query parameter syntax (q=, query=, ll=, sll=, destination=, daddr=, saddr=)
        // Accepts optional "loc:" and separators like comma, space, or plus
        val urlQueryPattern = Pattern.compile(
            "[?&](?:q|query|ll|sll|destination|daddr|saddr)=(?:loc:)?([+-]?\\d+\\.\\d+)[, +%2C]+([+-]?\\d+\\.\\d+)",
            Pattern.CASE_INSENSITIVE
        )
        val urlMatcher = urlQueryPattern.matcher(text)
        if (urlMatcher.find()) {
            val lat = urlMatcher.group(1)?.toDoubleOrNull()
            val lng = urlMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        // 2. Check for @lat,lng in Google Maps URLs
        val atPattern = Pattern.compile("@([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)")
        val atMatcher = atPattern.matcher(text)
        if (atMatcher.find()) {
            val lat = atMatcher.group(1)?.toDoubleOrNull()
            val lng = atMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        // 3. Check for !3d<lat>!4d<lng> format in web Google Maps URLs
        val d3d4Pattern = Pattern.compile("!3d([+-]?\\d+\\.\\d+)!4d([+-]?\\d+\\.\\d+)")
        val d3d4Matcher = d3d4Pattern.matcher(text)
        if (d3d4Matcher.find()) {
            val lat = d3d4Matcher.group(1)?.toDoubleOrNull()
            val lng = d3d4Matcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        // 4. Check for path patterns: /maps/search/<lat>,<lng> or /maps/dir/.../<lat>,<lng>
        val pathPattern = Pattern.compile("/maps/(?:search|place|dir)/.*?([+-]?\\d+\\.\\d+)[, +]+([+-]?\\d+\\.\\d+)")
        val pathMatcher = pathPattern.matcher(text)
        if (pathMatcher.find()) {
            val lat = pathMatcher.group(1)?.toDoubleOrNull()
            val lng = pathMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        // 5. Check for geo: URI format
        val geoPattern = Pattern.compile("geo:(?:0,0\\?q=)?([+-]?\\d+\\.\\d+)[, +]+([+-]?\\d+\\.\\d+)", Pattern.CASE_INSENSITIVE)
        val geoMatcher = geoPattern.matcher(text)
        if (geoMatcher.find()) {
            val lat = geoMatcher.group(1)?.toDoubleOrNull()
            val lng = geoMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        // 6. Check for labeled coordinates (e.g. lat: 28.57, lng: 77.32)
        val labeledPattern = Pattern.compile(
            "(?:lat|latitude)[:= ]*([+-]?\\d+\\.\\d+)[, \\t]+(?:lng|lon|long|longitude)[:= ]*([+-]?\\d+\\.\\d+)",
            Pattern.CASE_INSENSITIVE
        )
        val labeledMatcher = labeledPattern.matcher(text)
        if (labeledMatcher.find()) {
            val lat = labeledMatcher.group(1)?.toDoubleOrNull()
            val lng = labeledMatcher.group(2)?.toDoubleOrNull()
            if (lat != null && lng != null && lat in -90.0..90.0 && lng in -180.0..180.0) {
                return Pair(lat, lng)
            }
        }

        // 7. Check for standard raw "28.1234, 77.5678" or "28.1234 77.5678"
        val generalPattern = Pattern.compile("([+-]?\\d{1,2}\\.\\d{2,15})\\s*[,\\s]\\s*([+-]?\\d{1,3}\\.\\d{2,15})")
        val generalMatcher = generalPattern.matcher(text)
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
     * Asynchronously resolves short Google Maps links (e.g. goo.gl/maps/... or maps.app.goo.gl/...)
     * by following redirects and extracting the final coordinates.
     */
    fun resolveAndParseGoogleMapsUrl(input: String): Pair<Double, Double>? {
        val immediate = parseCoordinatesFromText(input)
        if (immediate != null) return immediate

        if (!isGoogleMapsUrl(input)) return null

        return try {
            val trimmed = input.trim()
            val urlStr = if (!trimmed.startsWith("http://", ignoreCase = true) && !trimmed.startsWith("https://", ignoreCase = true)) {
                "https://$trimmed"
            } else trimmed

            val url = java.net.URL(urlStr)
            val conn = url.openConnection() as java.net.HttpURLConnection
            conn.instanceFollowRedirects = true
            conn.connectTimeout = 4000
            conn.readTimeout = 4000
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10; K)")
            conn.connect()
            val finalUrl = conn.url.toString()
            val headerLocation = conn.getHeaderField("Location")
            conn.disconnect()

            parseCoordinatesFromText(finalUrl)
                ?: (if (headerLocation != null) parseCoordinatesFromText(headerLocation) else null)
        } catch (_: Exception) {
            null
        }
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
