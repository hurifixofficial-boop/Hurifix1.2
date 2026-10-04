package com.example.ui.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.data.model.ExpertEntity
import com.example.util.LocationHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpertDialog(
    initialExpert: ExpertEntity? = null,
    availableCategories: List<String> = listOf("Electrician", "Plumber"),
    onAddNewCategory: (String) -> Unit = {},
    onDismiss: () -> Unit,
    onSave: (ExpertEntity) -> Unit
) {
    val context = LocalContext.current
    val effectiveCategories = remember(availableCategories) {
        val base = linkedSetOf("Electrician", "Plumber")
        base.addAll(availableCategories.filter { it.isNotBlank() })
        base.toList()
    }

    var name by remember { mutableStateOf(initialExpert?.name ?: "") }
    var phone by remember { mutableStateOf(initialExpert?.phone ?: "") }
    var category by remember { mutableStateOf(initialExpert?.category ?: effectiveCategories.firstOrNull() ?: "Electrician") }
    var address by remember { mutableStateOf(initialExpert?.address ?: "") }
    var rawLocation by remember {
        mutableStateOf(
            if (initialExpert != null) "${initialExpert.latitude}, ${initialExpert.longitude}" else "28.5708, 77.3261"
        )
    }
    var latitude by remember { mutableDoubleStateOf(initialExpert?.latitude ?: 28.5708) }
    var longitude by remember { mutableDoubleStateOf(initialExpert?.longitude ?: 77.3261) }
    var isAvailable by remember { mutableStateOf(initialExpert?.isAvailable ?: true) }
    var isCategoryExpanded by remember { mutableStateOf(false) }
    var locationError by remember { mutableStateOf<String?>(null) }

    // Dialog state for adding a custom category right from the dialog
    var showAddCategoryInlineDialog by remember { mutableStateOf(false) }
    var newCategoryInput by remember { mutableStateOf("") }

    // Dialog popups for Location Permission and Location Disabled (Specification #6)
    var showLocationPermissionDialog by remember { mutableStateOf(false) }
    var showLocationDisabledDialog by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (fineGranted || coarseGranted) {
            // Permission granted, now check if GPS is enabled
            if (LocationHelper.isLocationEnabled(context)) {
                LocationHelper.fetchCurrentLocation(
                    context = context,
                    onSuccess = { loc ->
                        latitude = loc.latitude
                        longitude = loc.longitude
                        rawLocation = "${loc.latitude}, ${loc.longitude}"
                        locationError = null
                    },
                    onError = { err ->
                        locationError = err
                    }
                )
            } else {
                showLocationDisabledDialog = true
            }
        } else {
            locationError = "Location permission is required to detect coordinates automatically."
        }
    }

    fun handleLocationClick() {
        if (!LocationHelper.isLocationPermissionGranted(context)) {
            showLocationPermissionDialog = true
        } else if (!LocationHelper.isLocationEnabled(context)) {
            showLocationDisabledDialog = true
        } else {
            LocationHelper.fetchCurrentLocation(
                context = context,
                onSuccess = { loc ->
                    latitude = loc.latitude
                    longitude = loc.longitude
                    rawLocation = "${loc.latitude}, ${loc.longitude}"
                    locationError = null
                },
                onError = { err ->
                    locationError = err
                }
            )
        }
    }

    // Popup 1: Location Permission Popup
    if (showLocationPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showLocationPermissionDialog = false },
            icon = {
                Icon(
                    Icons.Default.Security,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            title = {
                Text(
                    text = "Allow Location Permission",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("Hurifix needs your location permission to fetch the expert's current GPS coordinates for accurate dispatch and proximity calculation.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLocationPermissionDialog = false
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                ) {
                    Text("Allow Permission")
                }
            },
            dismissButton = {
                Row {
                    TextButton(
                        onClick = {
                            showLocationPermissionDialog = false
                            LocationHelper.openAppSettings(context)
                        }
                    ) {
                        Text("App Settings")
                    }
                    TextButton(onClick = { showLocationPermissionDialog = false }) {
                        Text("Cancel")
                    }
                }
            }
        )
    }

    // Popup 2: Location Disabled Popup
    if (showLocationDisabledDialog) {
        AlertDialog(
            onDismissRequest = { showLocationDisabledDialog = false },
            icon = {
                Icon(
                    Icons.Default.LocationOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
            title = {
                Text(
                    text = "Device Location Disabled",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("Your phone's GPS / Location service is turned off. Please turn on Location in your phone settings to detect current coordinates.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLocationDisabledDialog = false
                        LocationHelper.openLocationSettings(context)
                    }
                ) {
                    Text("Turn On Location")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLocationDisabledDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Inline Add Category Dialog
    if (showAddCategoryInlineDialog) {
        AlertDialog(
            onDismissRequest = { showAddCategoryInlineDialog = false },
            title = { Text("Add New Category", fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Enter trade/service category name:")
                    OutlinedTextField(
                        value = newCategoryInput,
                        onValueChange = { newCategoryInput = it },
                        placeholder = { Text("e.g. Carpenter, Painter") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val trimmed = newCategoryInput.trim()
                        if (trimmed.isNotBlank()) {
                            onAddNewCategory(trimmed)
                            category = trimmed
                            newCategoryInput = ""
                            showAddCategoryInlineDialog = false
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddCategoryInlineDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (initialExpert == null) "Add New Expert" else "Edit Expert Details",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Expert Full Name") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("expert_name_input"),
                    singleLine = true
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Mobile Number (WhatsApp)") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("expert_phone_input"),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = isCategoryExpanded,
                    onExpandedChange = { isCategoryExpanded = !isCategoryExpanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Trade / Skill Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoryExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                    )
                    ExposedDropdownMenu(
                        expanded = isCategoryExpanded,
                        onDismissRequest = { isCategoryExpanded = false }
                    ) {
                        effectiveCategories.forEach { catName ->
                            DropdownMenuItem(
                                text = { Text(catName, fontWeight = FontWeight.SemiBold) },
                                onClick = {
                                    category = catName
                                    isCategoryExpanded = false
                                }
                            )
                        }

                        HorizontalDivider()

                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Add,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        "Add New Category",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            onClick = {
                                isCategoryExpanded = false
                                showAddCategoryInlineDialog = true
                            }
                        )
                    }
                }

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Area / Landmark / Address") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("expert_address_input"),
                    singleLine = true
                )

                // Location / GPS input: accepts Lat, Lng or Google Maps link
                // Specification #6: Clicking the current location box / GPS icon triggers permission / location check popups
                Column {
                    OutlinedTextField(
                        value = rawLocation,
                        onValueChange = { input ->
                            rawLocation = input
                            val parsed = LocationHelper.parseCoordinatesFromText(input)
                            if (parsed != null) {
                                latitude = parsed.first
                                longitude = parsed.second
                                locationError = null
                            } else {
                                locationError = "Enter valid Lat, Lng or Google Maps link"
                            }
                        },
                        label = { Text("Google Location (Click to detect GPS)") },
                        trailingIcon = {
                            IconButton(onClick = { handleLocationClick() }) {
                                Icon(
                                    Icons.Default.MyLocation,
                                    contentDescription = "Use GPS",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        isError = locationError != null,
                        supportingText = {
                            if (locationError != null) {
                                Text(locationError!!, color = MaterialTheme.colorScheme.error)
                            } else {
                                Text("Format: 28.5708, 77.3261 or tap GPS icon to auto-detect")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("expert_location_input")
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Availability Status", fontWeight = FontWeight.Medium)
                        Text(
                            if (isAvailable) "Available for tasks" else "Currently busy / unavailable",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = isAvailable,
                        onCheckedChange = { isAvailable = it },
                        modifier = Modifier.testTag("expert_available_switch")
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val cleanPhone = phone.replace(Regex("[^0-9]"), "")
                    if (name.isBlank() || cleanPhone.length < 10) {
                        locationError = "Please enter valid expert name and 10-digit phone"
                        return@Button
                    }
                    val newExpert = ExpertEntity(
                        id = initialExpert?.id ?: 0L,
                        name = name.trim(),
                        phone = cleanPhone,
                        category = category.trim(),
                        address = address.trim().ifBlank { "Address not specified" },
                        latitude = latitude,
                        longitude = longitude,
                        isAvailable = isAvailable,
                        rating = initialExpert?.rating ?: 4.8f,
                        ratingSum = initialExpert?.ratingSum ?: 4.8f,
                        totalRatingsCount = initialExpert?.totalRatingsCount ?: 1,
                        completedJobsCount = initialExpert?.completedJobsCount ?: 0,
                        cancelledJobsCount = initialExpert?.cancelledJobsCount ?: 0,
                        createdAt = initialExpert?.createdAt ?: System.currentTimeMillis(),
                        last_updated = System.currentTimeMillis()
                    )
                    onSave(newExpert)
                },
                modifier = Modifier.testTag("save_expert_button")
            ) {
                Text(if (initialExpert == null) "Add Expert" else "Save Changes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("cancel_expert_button")
            ) {
                Text("Cancel")
            }
        }
    )
}
