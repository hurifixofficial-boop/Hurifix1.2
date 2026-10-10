package com.example.util;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import com.example.BuildConfig;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: LocationHelper.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0005J\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005J\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u000eJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u0018J8\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u001b0\u001f2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001b0\u001fH\u0007¨\u0006\""}, d2 = {"Lcom/example/util/LocationHelper;", "", "<init>", "()V", "calculateDistanceKm", "", "lat1", "lon1", "lat2", "lon2", "estimateTravelTimeMinutes", "", "distanceKm", "formatDistance", "", "createGoogleMapsUrl", "latitude", "longitude", "parseCoordinatesFromText", "Lkotlin/Pair;", "input", "isLocationPermissionGranted", "", "context", "Landroid/content/Context;", "isLocationEnabled", "openAppSettings", "", "openLocationSettings", "fetchCurrentLocation", "onSuccess", "Lkotlin/Function1;", "Landroid/location/Location;", "onError", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes6.dex */
public final class LocationHelper {
    public static final int $stable = 0;
    public static final LocationHelper INSTANCE = new LocationHelper();

    private LocationHelper() {
    }

    public final double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = (Math.sin(dLat / 2.0d) * Math.sin(dLat / 2.0d)) + (Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2.0d) * Math.sin(dLon / 2.0d));
        double c = 2.0d * Math.atan2(Math.sqrt(a), Math.sqrt(1.0d - a));
        return 6371.0d * c;
    }

    public final int estimateTravelTimeMinutes(double distanceKm) {
        if (distanceKm <= 0.2d) {
            return 2;
        }
        double travelHours = distanceKm / 24.0d;
        int minutes = ((int) (60.0d * travelHours)) + 3;
        return RangesKt.coerceAtLeast(minutes, 3);
    }

    public final String formatDistance(double distanceKm) {
        if (distanceKm < 1.0d) {
            int meters = (int) (1000.0d * distanceKm);
            return meters + " m";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.1f km", Arrays.copyOf(new Object[]{Double.valueOf(distanceKm)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final String createGoogleMapsUrl(double latitude, double longitude) {
        return "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
    }

    public final Pair<Double, Double> parseCoordinatesFromText(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (StringsKt.isBlank(input)) {
            return null;
        }
        Pattern urlQueryPattern = Pattern.compile("[?&](?:q|query|ll)=([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)");
        Matcher urlMatcher = urlQueryPattern.matcher(input);
        if (urlMatcher.find()) {
            String group = urlMatcher.group(1);
            Double lat = group != null ? StringsKt.toDoubleOrNull(group) : null;
            String group2 = urlMatcher.group(2);
            Double lng = group2 != null ? StringsKt.toDoubleOrNull(group2) : null;
            if (lat != null && lng != null) {
                return new Pair<>(lat, lng);
            }
        }
        Pattern atPattern = Pattern.compile("@([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)");
        Matcher atMatcher = atPattern.matcher(input);
        if (atMatcher.find()) {
            String group3 = atMatcher.group(1);
            Double lat2 = group3 != null ? StringsKt.toDoubleOrNull(group3) : null;
            String group4 = atMatcher.group(2);
            Double lng2 = group4 != null ? StringsKt.toDoubleOrNull(group4) : null;
            if (lat2 != null && lng2 != null) {
                return new Pair<>(lat2, lng2);
            }
        }
        Pattern d3d4Pattern = Pattern.compile("!3d([+-]?\\d+\\.\\d+)!4d([+-]?\\d+\\.\\d+)");
        Matcher d3d4Matcher = d3d4Pattern.matcher(input);
        if (d3d4Matcher.find()) {
            String group5 = d3d4Matcher.group(1);
            Double lat3 = group5 != null ? StringsKt.toDoubleOrNull(group5) : null;
            String group6 = d3d4Matcher.group(2);
            Double lng3 = group6 != null ? StringsKt.toDoubleOrNull(group6) : null;
            if (lat3 != null && lng3 != null) {
                return new Pair<>(lat3, lng3);
            }
        }
        Pattern destPattern = Pattern.compile("(?:destination|daddr|saddr)=([+-]?\\d+\\.\\d+),([+-]?\\d+\\.\\d+)");
        Matcher destMatcher = destPattern.matcher(input);
        if (destMatcher.find()) {
            String group7 = destMatcher.group(1);
            Double lat4 = group7 != null ? StringsKt.toDoubleOrNull(group7) : null;
            String group8 = destMatcher.group(2);
            Double lng4 = group8 != null ? StringsKt.toDoubleOrNull(group8) : null;
            if (lat4 != null && lng4 != null) {
                return new Pair<>(lat4, lng4);
            }
        }
        Pattern generalPattern = Pattern.compile("([+-]?\\d{1,2}\\.\\d{2,10})\\s*[,\\s]\\s*([+-]?\\d{1,3}\\.\\d{2,10})");
        Matcher generalMatcher = generalPattern.matcher(input);
        if (!generalMatcher.find()) {
            return null;
        }
        String group9 = generalMatcher.group(1);
        Double lat5 = group9 != null ? StringsKt.toDoubleOrNull(group9) : null;
        String group10 = generalMatcher.group(2);
        Double lng5 = group10 != null ? StringsKt.toDoubleOrNull(group10) : null;
        if (lat5 == null || lng5 == null) {
            return null;
        }
        if (RangesKt.rangeTo(-90.0d, 90.0d).contains(lat5) && RangesKt.rangeTo(-180.0d, 180.0d).contains(lng5)) {
            return new Pair<>(lat5, lng5);
        }
        return null;
    }

    public final boolean isLocationPermissionGranted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean fine = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
        boolean coarse = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
        return fine || coarse;
    }

    public final boolean isLocationEnabled(Context context) {
        boolean gpsEnabled;
        boolean networkEnabled;
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("location");
        LocationManager locationManager = systemService instanceof LocationManager ? (LocationManager) systemService : null;
        if (locationManager == null) {
            return false;
        }
        try {
            gpsEnabled = locationManager.isProviderEnabled("gps");
        } catch (Exception e) {
            gpsEnabled = false;
        }
        try {
            networkEnabled = locationManager.isProviderEnabled("network");
        } catch (Exception e2) {
            networkEnabled = false;
        }
        return gpsEnabled || networkEnabled;
    }

    public final void openAppSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void openLocationSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void fetchCurrentLocation(Context context, final Function1<? super Location, Unit> onSuccess, final Function1<? super String, Unit> onError) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        try {
            final FusedLocationProviderClient fusedClient = LocationServices.getFusedLocationProviderClient(context);
            Intrinsics.checkNotNullExpressionValue(fusedClient, "getFusedLocationProviderClient(...)");
            CancellationTokenSource cts = new CancellationTokenSource();
            Task currentLocation = fusedClient.getCurrentLocation(100, cts.getToken());
            final Function1 function1 = new Function1() { // from class: com.example.util.LocationHelper$$ExternalSyntheticLambda3
                public final Object invoke(Object obj) {
                    return LocationHelper.fetchCurrentLocation$lambda$5(onSuccess, fusedClient, onError, (Location) obj);
                }
            };
            Intrinsics.checkNotNull(currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.util.LocationHelper$$ExternalSyntheticLambda4
                public final void onSuccess(Object obj) {
                    function1.invoke(obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.example.util.LocationHelper$$ExternalSyntheticLambda5
                public final void onFailure(Exception exc) {
                    LocationHelper.fetchCurrentLocation$lambda$7(onError, exc);
                }
            }));
        } catch (Exception e) {
            onError.invoke("GPS Error: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit fetchCurrentLocation$lambda$5(final Function1 $onSuccess, FusedLocationProviderClient $fusedClient, final Function1 $onError, Location loc) {
        if (loc != null) {
            $onSuccess.invoke(loc);
        } else {
            Task lastLocation = $fusedClient.getLastLocation();
            final Function1 function1 = new Function1() { // from class: com.example.util.LocationHelper$$ExternalSyntheticLambda0
                public final Object invoke(Object obj) {
                    return LocationHelper.fetchCurrentLocation$lambda$5$lambda$2($onSuccess, $onError, (Location) obj);
                }
            };
            lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.util.LocationHelper$$ExternalSyntheticLambda1
                public final void onSuccess(Object obj) {
                    function1.invoke(obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.example.util.LocationHelper$$ExternalSyntheticLambda2
                public final void onFailure(Exception exc) {
                    LocationHelper.fetchCurrentLocation$lambda$5$lambda$4($onError, exc);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final Unit fetchCurrentLocation$lambda$5$lambda$2(Function1 $onSuccess, Function1 $onError, Location lastLoc) {
        if (lastLoc != null) {
            $onSuccess.invoke(lastLoc);
        } else {
            $onError.invoke("GPS location not available, using default city center.");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void fetchCurrentLocation$lambda$5$lambda$4(Function1 $onError, Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $onError.invoke("Could not get location: " + it.getLocalizedMessage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void fetchCurrentLocation$lambda$7(Function1 $onError, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        $onError.invoke("Failed to get GPS location: " + e.getLocalizedMessage());
    }
}
