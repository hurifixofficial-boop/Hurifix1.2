@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.ExpertEntity
import com.example.util.CloudinaryHelper
import com.example.util.LocationHelper
import com.example.util.PhoneAuthManager
import com.example.util.SoundHelper
import kotlinx.coroutines.launch
import android.app.Activity
import android.widget.Toast
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.OutlinedTextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpertDialog(
    initialExpert: ExpertEntity? = null,
    availableCategories: List<String> = listOf("Electrician", "Plumber"),
    isAdmin: Boolean = false,
    currentUserId: String = "",
    currentUserName: String = "",
    currentUserDesignation: String = "",
    existingPhones: List<String> = emptyList(),
    onAddNewCategory: (String) -> Unit = {},
    onDismiss: () -> Unit,
    onSave: (ExpertEntity) -> Unit
) {
    val context = LocalContext.current
    val isExisting = initialExpert != null
    val createdTimestamp = initialExpert?.let {
        if (it.created_at_timestamp > 0) it.created_at_timestamp else it.createdAt
    } ?: System.currentTimeMillis()

    val isTimeLocked = isExisting && !isAdmin && ((System.currentTimeMillis() - createdTimestamp) > 24 * 60 * 60 * 1000L)

    val effectiveCategories = remember(availableCategories) {
        val base = linkedSetOf("Electrician", "Plumber")
        base.addAll(availableCategories.filter { it.isNotBlank() })
        base.toList()
    }

    var name by remember { mutableStateOf(initialExpert?.name ?: "") }
    var phone by remember { mutableStateOf(initialExpert?.phone ?: "") }
    var category by remember { mutableStateOf(initialExpert?.category ?: effectiveCategories.firstOrNull() ?: "Electrician") }
    var address by remember { mutableStateOf(initialExpert?.address ?: "") }
    // No prefilled coordinates for new expert
    var rawLocation by remember {
        mutableStateOf(
            if (initialExpert != null) "${initialExpert.latitude}, ${initialExpert.longitude}" else ""
        )
    }
    var latitude by remember { mutableDoubleStateOf(initialExpert?.latitude ?: 0.0) }
    var longitude by remember { mutableDoubleStateOf(initialExpert?.longitude ?: 0.0) }
    var isAvailable by remember { mutableStateOf(initialExpert?.isAvailable ?: true) }
    var isCategoryExpanded by remember { mutableStateOf(false) }
    var locationError by remember { mutableStateOf<String?>(null) }
    var isFetchingLocation by remember { mutableStateOf(false) }

    // Dialogs for permission denied and GPS disabled
    var showPermissionDeniedDialog by remember { mutableStateOf(false) }
    var showGpsDisabledDialog by remember { mutableStateOf(false) }
    var showSaveConfirmDialog by remember { mutableStateOf(false) }

    var isOtpSentInline by remember { mutableStateOf(false) }
    var inlineOtpInput by remember { mutableStateOf("") }
    var isVerifiedInline by remember { 
        mutableStateOf(initialExpert != null && initialExpert.phone.filter { it.isDigit() }.length == 10) 
    }
    var showInlineOtpConfirmDialog by remember { mutableStateOf(false) }
    var isSendingInlineOtp by remember { mutableStateOf(false) }
    var activeVerificationIdInline by remember { mutableStateOf("") }
    var inlineOtpError by remember { mutableStateOf<String?>(null) }
    var isInlineVerifying by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var profilePicUrl by remember { mutableStateOf(initialExpert?.profilePicUrl) }
    var isUploadingImage by remember { mutableStateOf(false) }
    var imageUploadError by remember { mutableStateOf<String?>(null) }
    var showImageSourcePicker by remember { mutableStateOf(false) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    fun createCameraUri(): Uri? {
        return try {
            val tempFile = File.createTempFile("expert_photo_${System.currentTimeMillis()}", ".jpg", context.cacheDir)
            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", tempFile)
        } catch (_: Exception) {
            null
        }
    }

    fun uploadPickedImage(uri: Uri) {
        isUploadingImage = true
        imageUploadError = null
        coroutineScope.launch {
            val result = CloudinaryHelper.compressAndUpload(context, uri)
            isUploadingImage = false
            result.onSuccess { uploadedUrl ->
                profilePicUrl = uploadedUrl
                val msg = if (initialExpert == null) "Profile picture added" else "Profile picture updated"
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }.onFailure { err ->
                imageUploadError = err.message ?: "Upload failed"
            }
        }
    }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempCameraUri != null) {
            uploadPickedImage(tempCameraUri!!)
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = createCameraUri()
            if (uri != null) {
                tempCameraUri = uri
                takePictureLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Unable to access camera cache file", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Camera permission is required to capture photo", Toast.LENGTH_SHORT).show()
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            uploadPickedImage(uri)
        }
    }

    val isPhoneAlreadyRegistered = remember(phone, existingPhones) {
        val cleanInput = phone.replace(Regex("[^0-9]"), "")
        cleanInput.length == 10 && existingPhones.any { existingPhone ->
            val cleanExisting = existingPhone.replace(Regex("[^0-9]"), "")
            cleanExisting == cleanInput && cleanInput != (initialExpert?.phone?.replace(Regex("[^0-9]"), "") ?: "")
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            if (!LocationHelper.isLocationEnabled(context)) {
                showGpsDisabledDialog = true
            } else {
                isFetchingLocation = true
                LocationHelper.fetchCurrentLocation(
                    context = context,
                    onSuccess = { lat, lon ->
                        isFetchingLocation = false
                        latitude = lat
                        longitude = lon
                        rawLocation = "$lat, $lon"
                        locationError = null
                    },
                    onError = { err ->
                        isFetchingLocation = false
                        locationError = err
                    }
                )
            }
        } else {
            showPermissionDeniedDialog = true
        }
    }

    fun handleLocationRequest() {
        if (!LocationHelper.isLocationPermissionGranted(context)) {
            locationPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else if (!LocationHelper.isLocationEnabled(context)) {
            showGpsDisabledDialog = true
        } else {
            isFetchingLocation = true
            LocationHelper.fetchCurrentLocation(
                context = context,
                onSuccess = { lat, lon ->
                    isFetchingLocation = false
                    latitude = lat
                    longitude = lon
                    rawLocation = "$lat, $lon"
                    locationError = null
                },
                onError = { err ->
                    isFetchingLocation = false
                    locationError = err
                }
            )
        }
    }

    if (showImageSourcePicker) {
        ImageSourcePickerDialog(
            title = "Upload Profile Picture",
            onSelectCamera = {
                val hasCameraPermission = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
                if (hasCameraPermission) {
                    val uri = createCameraUri()
                    if (uri != null) {
                        tempCameraUri = uri
                        takePictureLauncher.launch(uri)
                    } else {
                        Toast.makeText(context, "Unable to access camera", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            onSelectGallery = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            onDismiss = { showImageSourcePicker = false }
        )
    }

    // Permission Denied Dialog
    if (showPermissionDeniedDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDeniedDialog = false },
            icon = { Icon(Icons.Default.LocationOff, contentDescription = null, tint = MaterialTheme.colorScheme.error) },
            title = { Text("Location Permission Required", fontWeight = FontWeight.Bold) },
            text = { Text("Location permission is needed to fetch the expert's current GPS coordinates. Please grant the permission to proceed.") },
            confirmButton = {
                Button(onClick = {
                    showPermissionDeniedDialog = false
                    LocationHelper.openAppSettings(context)
                }) {
                    Text("Open Settings")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDeniedDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // GPS Disabled Dialog
    if (showGpsDisabledDialog) {
        AlertDialog(
            onDismissRequest = { showGpsDisabledDialog = false },
            icon = { Icon(Icons.Default.LocationOff, contentDescription = null, tint = MaterialTheme.colorScheme.error) },
            title = { Text("Device Location Disabled", fontWeight = FontWeight.Bold) },
            text = { Text("Your device GPS / Location is turned off. Please turn on Location in Settings to automatically fetch coordinates.") },
            confirmButton = {
                Button(onClick = {
                    showGpsDisabledDialog = false
                    LocationHelper.openLocationSettings(context)
                }) {
                    Text("Turn On GPS")
                }
            },
            dismissButton = {
                TextButton(onClick = { showGpsDisabledDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Dialog state for adding a custom category right from the dialog
    var showAddCategoryInlineDialog by remember { mutableStateOf(false) }
    var newCategoryInput by remember { mutableStateOf("") }

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

    if (showInlineOtpConfirmDialog) {
        HurifixConfirmDialog(
            title = "Send OTP Code?",
            message = "Do you want to send a 6-digit OTP code to verify +91 $phone?",
            confirmText = "Yes, Send",
            onConfirm = {
                showInlineOtpConfirmDialog = false
                isSendingInlineOtp = true
                PhoneAuthManager.sendOtp(
                    context = context,
                    activity = context as? Activity,
                    phone = phone,
                    onCodeSent = { verId, _ ->
                        isSendingInlineOtp = false
                        isOtpSentInline = true
                        activeVerificationIdInline = verId
                        Toast.makeText(context, "OTP Sent", Toast.LENGTH_SHORT).show()
                    },
                    onError = { _ ->
                        isSendingInlineOtp = false
                    }
                )
            },
            onDismiss = { showInlineOtpConfirmDialog = false }
        )
    }

    // Hurifix Confirmation Dialog before saving
    if (showSaveConfirmDialog) {
        HurifixConfirmDialog(
            title = if (initialExpert == null) "Add New Expert?" else "Update Expert?",
            message = "Confirm ${if (initialExpert == null) "registering" else "updating"} $name ($category) in Hurifix Experts Directory?",
            confirmText = "Yes, Save",
            onConfirm = {
                val finalLat = if (latitude != 0.0) latitude else 28.5708
                val finalLng = if (longitude != 0.0) longitude else 77.3261
                val expert = (initialExpert ?: ExpertEntity(
                    name = name.trim(),
                    phone = phone.trim(),
                    category = category,
                    address = address.trim().ifBlank { "Local Area" },
                    latitude = finalLat,
                    longitude = finalLng,
                    profilePicUrl = profilePicUrl ?: "",
                    isAvailable = isAvailable,
                    added_by_user_id = currentUserId.ifBlank { null },
                    added_by_user_name = currentUserName.ifBlank { "Admin" },
                    added_by_designation = currentUserDesignation.ifBlank { null },
                    created_at_timestamp = System.currentTimeMillis()
                )).copy(
                    name = name.trim(),
                    phone = phone.trim(),
                    category = category,
                    address = address.trim().ifBlank { "Local Area" },
                    latitude = finalLat,
                    longitude = finalLng,
                    profilePicUrl = profilePicUrl ?: "",
                    isAvailable = isAvailable,
                    last_updated = System.currentTimeMillis()
                )
                onSave(expert)
                showSaveConfirmDialog = false
            },
            onDismiss = { showSaveConfirmDialog = false }
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)
    ) {
        BackHandler { onDismiss() }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = if (initialExpert == null) "Add New Expert" else if (isTimeLocked) "Expert Details" else "Edit Expert Details",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            if (isTimeLocked) {
                                Text(
                                    text = "🔒 Editing locked after 24 hours (View Only)",
                                    color = Color(0xFFB91C1C),
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    )
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 32.dp)
                ) {
                    Surface(
                        tonalElevation = 6.dp,
                        shadowElevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            if (!isTimeLocked) {
                                OutlinedButton(
                                    onClick = onDismiss,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Cancel")
                                }
                                val isSaveEnabled = name.isNotBlank() && 
                                        phone.length == 10 && 
                                        isVerifiedInline && 
                                        rawLocation.isNotBlank() && 
                                        latitude != 0.0 && 
                                        longitude != 0.0 && 
                                        locationError == null &&
                                        !isUploadingImage
                                Button(
                                    onClick = {
                                        if (isSaveEnabled) {
                                            showSaveConfirmDialog = true
                                        }
                                    },
                                    enabled = isSaveEnabled,
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("save_expert_button")
                                ) {
                                    Text("Save Expert", fontWeight = FontWeight.Bold)
                                }
                            } else {
                                Button(
                                    onClick = onDismiss,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("close_expert_button")
                                ) {
                                    Text("Close (View Only)")
                                }
                            }
                        }
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Profile Picture Upload Section
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                                .clickable(enabled = !isTimeLocked && !isUploadingImage) {
                                    showImageSourcePicker = true
                                }
                        ) {
                            if (isUploadingImage) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(32.dp),
                                        strokeWidth = 3.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else if (!profilePicUrl.isNullOrBlank()) {
                                AsyncImage(
                                    model = profilePicUrl,
                                    contentDescription = "Expert Profile Picture",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (name.isNotBlank()) {
                                        Text(
                                            text = name.take(1).uppercase(),
                                            style = MaterialTheme.typography.headlineMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(40.dp)
                                        )
                                    }
                                }
                            }
                        }

                        // Camera badge icon on bottom right
                        if (!isTimeLocked) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surface),
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .clickable(enabled = !isUploadingImage) {
                                        showImageSourcePicker = true
                                    }
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        Icons.Default.PhotoCamera,
                                        contentDescription = "Upload Picture",
                                        tint = Color.White,
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                            }
                        }
                    }

                    if (!isTimeLocked) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = {
                                    showImageSourcePicker = true
                                },
                                enabled = !isUploadingImage,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.testTag("upload_profile_picture_button")
                            ) {
                                Icon(Icons.Default.PhotoCamera, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = if (isUploadingImage) "Uploading..." else if (profilePicUrl.isNullOrBlank()) "Upload Profile Picture" else "Change Picture",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            if (!profilePicUrl.isNullOrBlank()) {
                                TextButton(
                                    onClick = { profilePicUrl = null },
                                    enabled = !isUploadingImage,
                                    modifier = Modifier.testTag("remove_profile_picture_button")
                                ) {
                                    Text("Remove", fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { if (!isTimeLocked) name = it },
                    label = { Text("Expert Full Name") },
                    readOnly = isTimeLocked,
                    enabled = !isTimeLocked,
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("expert_name_input"),
                    singleLine = true
                )

                val isPhoneFullyVerified = isVerifiedInline && phone.length == 10

                OutlinedTextField(
                    value = phone,
                    onValueChange = { input ->
                        if (!isTimeLocked) { 
                            val digitsOnly = input.filter { char -> char.isDigit() }.take(10)
                            phone = digitsOnly 
                            val isOriginalExistingPhone = initialExpert != null && 
                                    initialExpert.phone.isNotBlank() && 
                                    digitsOnly == initialExpert.phone.filter { c -> c.isDigit() } &&
                                    digitsOnly.length == 10

                            if (isOriginalExistingPhone) {
                                isVerifiedInline = true
                            } else {
                                isVerifiedInline = false
                                isOtpSentInline = false
                                inlineOtpInput = ""
                            }
                        } 
                    },
                    label = { Text("Mobile Number (WhatsApp) *") },
                    prefix = { Text("+91 ") },
                    readOnly = isTimeLocked || isPhoneFullyVerified,
                    enabled = !isTimeLocked,
                    isError = (phone.isNotEmpty() && phone.length < 10) || (phone.length == 10 && isPhoneAlreadyRegistered),
                    leadingIcon = { 
                        Icon(
                            imageVector = Icons.Default.Phone, 
                            contentDescription = null,
                            tint = if (isPhoneFullyVerified) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
                        ) 
                    },
                    trailingIcon = {
                        if (!isTimeLocked && !isPhoneFullyVerified) {
                            val isPhoneComplete = phone.length == 10 && !isPhoneAlreadyRegistered
                            TextButton(
                                onClick = { showInlineOtpConfirmDialog = true },
                                enabled = isPhoneComplete,
                                modifier = Modifier.testTag("inline_send_otp_button")
                            ) {
                                Text(
                                    text = "Send OTP",
                                    fontWeight = FontWeight.Bold,
                                    color = if (isPhoneComplete) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                                )
                            }
                        } else if (isPhoneFullyVerified) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Verified",
                                tint = Color(0xFF2E7D32)
                            )
                        }
                    },
                    colors = if (isPhoneFullyVerified) {
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2E7D32),
                            unfocusedBorderColor = Color(0xFF2E7D32),
                            disabledBorderColor = Color(0xFF2E7D32),
                            focusedLabelColor = Color(0xFF2E7D32),
                            unfocusedLabelColor = Color(0xFF2E7D32)
                        )
                    } else {
                        OutlinedTextFieldDefaults.colors()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("expert_phone_input"),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    supportingText = {
                        if (phone.isNotEmpty() && phone.length < 10) {
                            Text("${phone.length}/10 digits", color = MaterialTheme.colorScheme.error)
                        } else if (phone.length == 10) {
                            if (isPhoneAlreadyRegistered) {
                                Text("❌ Mobile number already registered", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                            } else if (isPhoneFullyVerified) {
                                Text("✓ Mobile Verified Successfully", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                            } else {
                                Text("10/10 digits (Verification Required - Tap Send OTP)", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                )

                // Inline OTP Verification Box (shown below phone input box as requested)
                if (isOtpSentInline && !isPhoneFullyVerified && !isTimeLocked) {
                    OutlinedTextField(
                        value = inlineOtpInput,
                        onValueChange = { inlineOtpInput = it.filter { char -> char.isDigit() }.take(6) },
                        label = { Text("Enter 6-Digit OTP") },
                        leadingIcon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inline_otp_input"),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        trailingIcon = {
                            if (isInlineVerifying) {
                                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                            } else {
                                TextButton(
                                    onClick = {
                                        isInlineVerifying = true
                                        inlineOtpError = null
                                        PhoneAuthManager.verifyOtp(
                                            context = context,
                                            phone = phone,
                                            verificationId = activeVerificationIdInline,
                                            inputCode = inlineOtpInput,
                                            onSuccess = {
                                                isInlineVerifying = false
                                                isVerifiedInline = true
                                                Toast.makeText(context, "Phone Verified Successfully", Toast.LENGTH_SHORT).show()
                                                SoundHelper.playSFX("success")
                                            },
                                            onError = { _ ->
                                                isInlineVerifying = false
                                                inlineOtpError = null
                                            }
                                        )
                                    },
                                    enabled = inlineOtpInput.length == 6,
                                    modifier = Modifier.testTag("inline_verify_otp_button")
                                ) {
                                    Text("Verify", fontWeight = FontWeight.Bold)
                                }
                            }
                        },
                        supportingText = {
                            Text("Enter the 6-digit verification code sent to +91 $phone", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    )
                }

                // Service / Trade Category selector
                ExposedDropdownMenuBox(
                    expanded = isCategoryExpanded && !isTimeLocked,
                    onExpandedChange = { if (!isTimeLocked) isCategoryExpanded = !isCategoryExpanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        enabled = !isTimeLocked,
                        label = { Text("Service Category") },
                        trailingIcon = { if (!isTimeLocked) ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoryExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    if (!isTimeLocked) {
                        ExposedDropdownMenu(
                            expanded = isCategoryExpanded,
                            onDismissRequest = { isCategoryExpanded = false }
                        ) {
                            effectiveCategories.forEach { cat ->
                                DropdownMenuItem(
                                    text = { Text(cat) },
                                    onClick = {
                                        category = cat
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
                }

                OutlinedTextField(
                    value = address,
                    onValueChange = { if (!isTimeLocked) address = it },
                    label = { Text("Area / Landmark / Address") },
                    readOnly = isTimeLocked,
                    enabled = !isTimeLocked,
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("expert_address_input"),
                    singleLine = true
                )

                // Location / GPS input: accepts Lat, Lng or Google Maps link; clicking triggers location flow (Compulsory)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    val hasLocationError = (locationError != null || rawLocation.isBlank() || latitude == 0.0 || longitude == 0.0)
                    OutlinedTextField(
                        value = rawLocation,
                        onValueChange = { input ->
                            if (!isTimeLocked) {
                                rawLocation = input
                                val parsed = LocationHelper.parseCoordinatesFromText(input)
                                if (parsed != null) {
                                    latitude = parsed.first
                                    longitude = parsed.second
                                    locationError = null
                                } else {
                                    locationError = "Enter valid Lat, Lng or Google Maps link"
                                }
                            }
                        },
                        readOnly = isTimeLocked,
                        enabled = !isTimeLocked,
                        label = { Text("Google Location (Coordinates or Maps URL) *") },
                        placeholder = { Text("Click GPS icon to fetch location, or paste coordinates") },
                        trailingIcon = {
                            if (!isTimeLocked) {
                                if (isFetchingLocation) {
                                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                                } else {
                                    IconButton(
                                        onClick = { handleLocationRequest() },
                                        modifier = Modifier.testTag("expert_gps_fetch_button")
                                    ) {
                                        Icon(
                                            Icons.Default.MyLocation,
                                            contentDescription = "Use GPS",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        },
                        isError = hasLocationError && !isTimeLocked,
                        supportingText = {
                            if (!isTimeLocked) {
                                if (locationError != null) {
                                    Text(locationError!!, color = MaterialTheme.colorScheme.error)
                                } else if (rawLocation.isBlank() || latitude == 0.0 || longitude == 0.0) {
                                    Text("Coordinates are compulsory (* Required)", color = MaterialTheme.colorScheme.error)
                                } else {
                                    Text("✓ Coordinates configured successfully", color = Color(0xFF2E7D32))
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("expert_location_input")
                    )

                    if (!isTimeLocked) {
                        // Quick action button to trigger location permission & GPS flow
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = { handleLocationRequest() },
                                enabled = !isFetchingLocation
                            ) {
                                Icon(Icons.Default.MyLocation, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text(if (isFetchingLocation) "Detecting GPS..." else "Use Current Location")
                            }
                        }
                    }
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
                        onCheckedChange = { if (!isTimeLocked) isAvailable = it },
                        enabled = !isTimeLocked,
                        modifier = Modifier.testTag("expert_available_switch")
                    )
                }
            }
        }
    }
}
