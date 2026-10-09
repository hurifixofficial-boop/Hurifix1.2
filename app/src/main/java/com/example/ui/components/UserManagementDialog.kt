package com.example.ui.components

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import com.example.data.model.JobStatus
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.firebase.FirestoreSyncManager
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.HurifixUser
import com.example.util.AutoBackupWorker
import com.example.util.SoundHelper
import com.example.util.BackupRestoreHelper
import com.example.util.SessionManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManagementDialog(
    allJobs: List<CustomerJobEntity> = emptyList(),
    allExperts: List<ExpertEntity> = emptyList(),
    allCategories: List<ExpertCategoryEntity> = emptyList(),
    onRestoreBackup: (List<CustomerJobEntity>, List<ExpertEntity>, List<ExpertCategoryEntity>) -> Unit = { _, _, _ -> },
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val syncManager = remember { FirestoreSyncManager.getInstance(context) }
    val sessionManager = remember { SessionManager(context) }

    if (!sessionManager.isAdmin()) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "🔒 Access Denied: Admin privileges required.", Toast.LENGTH_LONG).show()
            onDismiss()
        }
        return
    }

    var selectedAdminTab by remember { mutableIntStateOf(0) }
    var usersList by remember { mutableStateOf<List<HurifixUser>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var showAddUserDialog by remember { mutableStateOf(false) }
    var editingUserPermissions by remember { mutableStateOf<HurifixUser?>(null) }
    var userToDelete by remember { mutableStateOf<HurifixUser?>(null) }

    // Auto-backup configuration states
    var isAutoBackupEnabled by remember { mutableStateOf(sessionManager.isAutoBackupEnabled()) }
    var autoBackupFreq by remember { mutableStateOf(sessionManager.getAutoBackupFrequency()) }
    var lastBackupTimestamp by remember { mutableStateOf(sessionManager.getLastBackupTimestamp()) }
    var lastBackupStatus by remember { mutableStateOf(sessionManager.getLastBackupStatus()) }

    // Restore confirmation dialog state
    var pendingRestoreData by remember { mutableStateOf<BackupRestoreHelper.BackupData?>(null) }

    // File picker for Restore
    val restoreFileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let {
            try {
                context.contentResolver.openInputStream(it)?.use { stream ->
                    val data = BackupRestoreHelper.parseBackupJson(stream)
                    pendingRestoreData = data
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to read backup file: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refreshUsers() {
        isLoading = true
        coroutineScope.launch {
            usersList = syncManager.fetchAllUsers()
            isLoading = false
            lastBackupTimestamp = sessionManager.getLastBackupTimestamp()
            lastBackupStatus = sessionManager.getLastBackupStatus()
        }
    }

    LaunchedEffect(Unit) {
        refreshUsers()
    }

    // Confirmation dialog before applying restore
    pendingRestoreData?.let { data ->
        HurifixConfirmDialog(
            title = "Confirm Database Restore?",
            message = "Restore ${data.jobs.size} orders, ${data.experts.size} experts, and ${data.categories.size} categories into Hurifix database?",
            confirmText = "Yes, Restore Now",
            icon = Icons.Default.CloudDownload,
            onConfirm = {
                onRestoreBackup(data.jobs, data.experts, data.categories)
                Toast.makeText(
                    context,
                    "Database restored: ${data.jobs.size} orders & ${data.experts.size} experts imported!",
                    Toast.LENGTH_LONG
                ).show()
                pendingRestoreData = null
            },
            onDismiss = { pendingRestoreData = null }
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = androidx.compose.ui.graphics.RectangleShape,
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 6.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                Icons.Default.Security,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Column {
                                Text(
                                    text = "👑 Admin Control Center",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Team Designation & Google Drive Backup",
                                    fontSize = 11.5.sp,
                                    color = Color.White.copy(alpha = 0.85f)
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { refreshUsers() }) {
                                Icon(Icons.Default.Refresh, contentDescription = "Refresh", tint = Color.White)
                            }
                            IconButton(onClick = onDismiss) {
                                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                            }
                        }
                    }
                }

                // Sub-tabs: Team Members vs Backup & Google Drive
                TabRow(selectedTabIndex = selectedAdminTab) {
                    Tab(
                        selected = selectedAdminTab == 0,
                        onClick = { selectedAdminTab = 0 },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(16.dp))
                                Text("Team Members (${usersList.size})", fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                    Tab(
                        selected = selectedAdminTab == 1,
                        onClick = { selectedAdminTab = 1 },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(Icons.Default.Backup, contentDescription = null, modifier = Modifier.size(16.dp))
                                Text("Backup & Drive", fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                    Tab(
                        selected = selectedAdminTab == 2,
                        onClick = { selectedAdminTab = 2 },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(Icons.Default.History, contentDescription = null, modifier = Modifier.size(16.dp))
                                Text("Activity Log (${allJobs.size})", fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                }

                if (selectedAdminTab == 0) {
                    // TAB 0: TEAM MEMBERS & DESIGNATION MANAGEMENT
                    val teamListState = rememberLazyListState()
                    val isTeamListScrolledDown by remember {
                        derivedStateOf {
                            teamListState.firstVisibleItemIndex > 0 || teamListState.firstVisibleItemScrollOffset > 20
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Registered Team Members: ${usersList.size}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Divider()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        if (isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else if (usersList.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No team members found in database.",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            LazyColumn(
                                state = teamListState,
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 80.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(usersList, key = { it.phone }) { user ->
                                    UserCardItem(
                                        user = user,
                                        onToggleBlock = { isBlocked ->
                                            coroutineScope.launch {
                                                syncManager.updateUserBlocked(user.phone, isBlocked)
                                                refreshUsers()
                                                val statusText = if (isBlocked) "Access Restricted & Session Terminated" else "Access Restored"
                                                Toast.makeText(context, "${user.name} $statusText", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        onEditPermissions = {
                                            editingUserPermissions = user
                                        },
                                        onDelete = {
                                            userToDelete = user
                                        }
                                    )
                                }
                            }
                        }

                        // Right-down corner Floating Action Buttons
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .navigationBarsPadding()
                                .padding(end = 12.dp, bottom = 72.dp)
                        ) {
                            // When not scrolled down (at top), show "Add Team Member" button
                            androidx.compose.animation.AnimatedVisibility(
                                visible = !isTeamListScrolledDown,
                                enter = fadeIn(tween(250)) + scaleIn(tween(250)),
                                exit = fadeOut(tween(200)) + scaleOut(tween(200))
                            ) {
                                ExtendedFloatingActionButton(
                                    onClick = { showAddUserDialog = true },
                                    icon = { Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(20.dp)) },
                                    text = { Text("Add Team Member", fontWeight = FontWeight.Bold) },
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = Color.White,
                                    shape = RoundedCornerShape(16.dp),
                                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp, pressedElevation = 10.dp)
                                )
                            }

                            // When list is scrolled down, convert button to Scroll To Top button
                            ScrollToTopButton(
                                visible = isTeamListScrolledDown,
                                onClick = {
                                    coroutineScope.launch {
                                        teamListState.animateScrollToItem(0)
                                    }
                                }
                            )
                        }
                    }
                } else if (selectedAdminTab == 1) {
                    // TAB 1: ADMIN BACKUP & GOOGLE DRIVE CONTROL CENTER
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(14.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Section 1: Manual Backup to Google Drive
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.CloudUpload,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(22.dp)
                                        )
                                        Text(
                                            text = "Manual Google Drive Backup",
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }

                                    Text(
                                        text = "Generate a full JSON database snapshot containing all customer jobs, registered experts, and categories, and upload directly to your Google Drive or device vault.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Surface(
                                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("📋 Orders: ${allJobs.size}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                            Text("🛠 Experts: ${allExperts.size}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                            Text("🏷 Categories: ${allCategories.size}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                        }
                                    }

                                    Button(
                                        onClick = {
                                            BackupRestoreHelper.exportAndShareBackup(
                                                context = context,
                                                jobs = allJobs,
                                                experts = allExperts,
                                                categories = allCategories
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Icon(Icons.Default.CloudUpload, contentDescription = null, modifier = Modifier.size(18.dp))
                                        Spacer(Modifier.width(8.dp))
                                        Text("Backup to Google Drive / Share JSON", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        // Section 2: Manual Restore from Drive / File
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.CloudDownload,
                                            contentDescription = null,
                                            tint = Color(0xFF16A34A),
                                            modifier = Modifier.size(22.dp)
                                        )
                                        Text(
                                            text = "Restore from Google Drive / File",
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }

                                    Text(
                                        text = "Import a Hurifix JSON backup file from Google Drive or local storage. Existing and backup records will be merged with latest timestamp synchronization.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    OutlinedButton(
                                        onClick = {
                                            restoreFileLauncher.launch(arrayOf("application/json", "*/*"))
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Icon(Icons.Default.CloudDownload, contentDescription = null, modifier = Modifier.size(18.dp))
                                        Spacer(Modifier.width(8.dp))
                                        Text("Select Drive Backup File & Restore", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        // Section 3: Automated Scheduled Google Drive Auto-Backup (WorkManager)
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.Schedule,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(22.dp)
                                            )
                                            Text(
                                                text = "Scheduled Auto-Backup Engine",
                                                fontWeight = FontWeight.Bold,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                        }

                                        Switch(
                                            checked = isAutoBackupEnabled,
                                            onCheckedChange = { enabled ->
                                                isAutoBackupEnabled = enabled
                                                sessionManager.setAutoBackupEnabled(enabled)
                                                AutoBackupWorker.scheduleAutoBackup(
                                                    context = context,
                                                    isEnabled = enabled,
                                                    frequency = autoBackupFreq
                                                )
                                                Toast.makeText(
                                                    context,
                                                    if (enabled) "Scheduled auto-backup enabled ($autoBackupFreq)" else "Auto-backup disabled",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        )
                                    }

                                    Text(
                                        text = "Uses Android WorkManager to automatically generate a lightweight JSON backup and store it in your backup vault in the background at regular intervals.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    if (isAutoBackupEnabled) {
                                        Text(
                                            text = "Backup Interval Frequency:",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            val isDaily = autoBackupFreq.equals("DAILY", ignoreCase = true)
                                            Button(
                                                onClick = {
                                                    autoBackupFreq = "DAILY"
                                                    sessionManager.setAutoBackupFrequency("DAILY")
                                                    AutoBackupWorker.scheduleAutoBackup(context, true, "DAILY")
                                                    Toast.makeText(context, "Interval set to Daily (every 24 hrs)", Toast.LENGTH_SHORT).show()
                                                },
                                                colors = if (isDaily) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                                                else ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Text("Daily (24 hrs)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            }

                                            val isWeekly = autoBackupFreq.equals("WEEKLY", ignoreCase = true)
                                            Button(
                                                onClick = {
                                                    autoBackupFreq = "WEEKLY"
                                                    sessionManager.setAutoBackupFrequency("WEEKLY")
                                                    AutoBackupWorker.scheduleAutoBackup(context, true, "WEEKLY")
                                                    Toast.makeText(context, "Interval set to Weekly (every 7 days)", Toast.LENGTH_SHORT).show()
                                                },
                                                colors = if (isWeekly) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                                                else ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Text("Weekly (7 days)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            }
                                        }
                                    }

                                    // Real-time Status Card
                                    Surface(
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(10.dp),
                                            verticalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            val formattedTime = if (lastBackupTimestamp > 0) {
                                                SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(lastBackupTimestamp))
                                            } else "Not yet run"

                                            Text(
                                                text = "🕒 Last Backup: $formattedTime",
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(
                                                text = "📌 Status: $lastBackupStatus",
                                                fontSize = 11.5.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }

                                    // Immediate Trigger Action
                                    FilledTonalButton(
                                        onClick = {
                                            AutoBackupWorker.runImmediateBackup(context)
                                            sessionManager.setLastBackupTimestamp(System.currentTimeMillis())
                                            val msg = "Background backup task triggered (${allJobs.size} orders, ${allExperts.size} experts)"
                                            sessionManager.setLastBackupStatus(msg)
                                            lastBackupTimestamp = System.currentTimeMillis()
                                            lastBackupStatus = msg
                                            Toast.makeText(context, "Background backup triggered!", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Icon(Icons.Default.Backup, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(Modifier.width(6.dp))
                                        Text("⚡ Run Background Backup Now", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // TAB 2: ORDER ACTIVITY HISTORY AUDIT LOG
                    OrderActivityHistoryContent(allJobs = allJobs)
                }
            }
        }
    }

    // Add New Team Member Dialog
    if (showAddUserDialog) {
        AddTeamMemberDialog(
            onDismiss = { showAddUserDialog = false },
            onSave = { newUser ->
                coroutineScope.launch {
                    val result = syncManager.saveStaffUser(newUser)
                    showAddUserDialog = false
                    if (result.isSuccess) {
                        Toast.makeText(context, "Team Member '${newUser.name}' (${newUser.displayDesignation}) created successfully!", Toast.LENGTH_SHORT).show()
                        refreshUsers()
                    } else {
                        Toast.makeText(context, "Failed to create team member: ${result.exceptionOrNull()?.localizedMessage}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    // Edit Role & Permissions Dialog
    editingUserPermissions?.let { user ->
        EditRoleAndPermissionsDialog(
            user = user,
            onDismiss = { editingUserPermissions = null },
            onSave = { updatedUser ->
                coroutineScope.launch {
                    val result = syncManager.saveStaffUser(updatedUser)
                    editingUserPermissions = null
                    if (result.isSuccess) {
                        Toast.makeText(context, "Role & permissions updated for ${updatedUser.name}", Toast.LENGTH_SHORT).show()
                        refreshUsers()
                    } else {
                        Toast.makeText(context, "Error updating permissions", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

    // Delete User Confirmation
    userToDelete?.let { user ->
        AlertDialog(
            onDismissRequest = { userToDelete = null },
            title = { Text("Delete Team Member Account?", fontWeight = FontWeight.Bold) },
            text = {
                Text("Are you sure you want to permanently delete '${user.name}' (${user.displayDesignation} - ${user.phone})? Their credentials and access will be removed immediately.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        val target = user
                        userToDelete = null
                        coroutineScope.launch {
                            syncManager.deleteUser(target.phone)
                            Toast.makeText(context, "Account for ${target.name} deleted", Toast.LENGTH_SHORT).show()
                            refreshUsers()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete Account", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { userToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun UserCardItem(
    user: HurifixUser,
    onToggleBlock: (Boolean) -> Unit,
    onEditPermissions: () -> Unit,
    onDelete: () -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    var showConfirmDialog by remember { mutableStateOf(false) }
    var confirmTitle by remember { mutableStateOf("") }
    var confirmMessage by remember { mutableStateOf("") }
    var confirmButtonText by remember { mutableStateOf("Confirm") }
    var confirmIsDestructive by remember { mutableStateOf(false) }
    var confirmAction by remember { mutableStateOf<() -> Unit>({}) }

    if (showConfirmDialog) {
        HurifixConfirmDialog(
            title = confirmTitle,
            message = confirmMessage,
            confirmText = confirmButtonText,
            isDestructive = confirmIsDestructive,
            onConfirm = {
                showConfirmDialog = false
                confirmAction()
            },
            onDismiss = { showConfirmDialog = false }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            if (user.is_blocked) MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
            else if (user.isAdmin) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            else MaterialTheme.colorScheme.outlineVariant
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (user.is_blocked) MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.15f)
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Row 1: Name, Role Badge, Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (user.isAdmin) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(38.dp).clip(CircleShape)
                    ) {
                        if (!user.profile_pic_url.isNullOrBlank()) {
                            if (user.profile_pic_url.startsWith("preset:")) {
                                val emoji = user.profile_pic_url.removePrefix("preset:")
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(text = emoji, fontSize = 20.sp)
                                }
                            } else {
                                AsyncImage(
                                    model = user.profile_pic_url,
                                    contentDescription = "${user.name} Profile Picture",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        } else {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = user.name.take(1).uppercase(),
                                    color = if (user.isAdmin) Color.White else MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    Column {
                        Text(
                            text = user.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "📱 ${user.phone}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Respectful Custom Designation Tag Badge
                    Surface(
                        color = if (user.isAdmin) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, if (user.isAdmin) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = if (user.isAdmin) "👑" else "✨",
                                fontSize = 11.sp
                            )
                            Text(
                                text = user.displayDesignation,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (user.isAdmin) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }

                    Surface(
                        color = if (user.is_blocked) Color(0xFFFEE2E2) else Color(0xFFDCFCE7),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = if (user.is_blocked) "🚫 RESTRICTED" else "ACTIVE",
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (user.is_blocked) Color(0xFFDC2626) else Color(0xFF16A34A),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            // Row 2: Password Display
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(15.dp), tint = MaterialTheme.colorScheme.primary)
                        Text(
                            text = "Login Key: ",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = if (showPassword) user.password.ifBlank { "Not set" } else "••••••••",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = { showPassword = !showPassword },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Row 3: Active Permissions preview chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (user.isAdmin) {
                    PermissionChip(label = "Full Admin Access", granted = true)
                } else if (user.view_only) {
                    PermissionChip(label = "View Only Mode", granted = true, isWarning = true)
                } else {
                    if (user.can_manage_orders) PermissionChip(label = "Orders", granted = true)
                    if (user.can_add_experts) PermissionChip(label = "Experts", granted = true)
                    if (user.can_delete_orders) PermissionChip(label = "Delete", granted = true)
                    if (user.can_export_reports) PermissionChip(label = "Reports", granted = true)
                }
            }

            // Row 4: Action Buttons (Edit Role & Permissions, Restrict/Restore, Delete)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!user.isAdmin) {
                    OutlinedButton(
                        onClick = onEditPermissions,
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(13.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Role & Access", fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(Modifier.width(8.dp))

                    OutlinedButton(
                        onClick = {
                            val isBlocking = !user.is_blocked
                            confirmTitle = if (isBlocking) "Restrict Account Access?" else "Restore Account Access?"
                            confirmMessage = if (isBlocking) {
                                "Are you sure you want to restrict login access for ${user.name} (${user.displayDesignation} - ${user.phone})? Their active session will be terminated immediately."
                            } else {
                                "Allow ${user.name} (${user.displayDesignation} - ${user.phone}) to login and access Hurifix again?"
                            }
                            confirmButtonText = if (isBlocking) "Yes, Restrict Access" else "Restore Access"
                            confirmIsDestructive = isBlocking
                            confirmAction = { onToggleBlock(isBlocking) }
                            showConfirmDialog = true
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (user.is_blocked) Color(0xFF16A34A) else MaterialTheme.colorScheme.error
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(if (user.is_blocked) "Restore Access" else "Restrict Access", fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            confirmTitle = "Delete Account?"
                            confirmMessage = "Are you sure you want to permanently delete account for ${user.name} (${user.displayDesignation} - ${user.phone})?"
                            confirmButtonText = "Yes, Delete"
                            confirmIsDestructive = true
                            confirmAction = onDelete
                            showConfirmDialog = true
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Gray, modifier = Modifier.size(16.dp))
                    }
                } else {
                    Text(
                        text = "Master Admin (Protected)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun PermissionChip(label: String, granted: Boolean, isWarning: Boolean = false) {
    Surface(
        color = if (isWarning) Color(0xFFFEF3C7) else if (granted) Color(0xFFDCFCE7) else Color(0xFFF3F4F6),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isWarning) Color(0xFFB45309) else if (granted) Color(0xFF15803D) else Color.Gray,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTeamMemberDialog(
    onDismiss: () -> Unit,
    onSave: (HurifixUser) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("Hurifix@123") }
    var designationTag by remember { mutableStateOf("Operations Head") }
    var canManageOrders by remember { mutableStateOf(true) }
    var canAddExperts by remember { mutableStateOf(true) }
    var canAddCustomers by remember { mutableStateOf(true) }
    var canDeleteOrders by remember { mutableStateOf(false) }
    var canExportReports by remember { mutableStateOf(false) }
    var viewOnly by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val presetDesignations = remember { HurifixUser.DEFAULT_DESIGNATION_PRESETS }

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
                                text = "Add New Team Member",
                                style = MaterialTheme.typography.titleLarge.copy(fontSize = 26.sp),
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Register staff credentials & assign permissions",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", modifier = Modifier.size(26.dp))
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
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Cancel button on left side
                            OutlinedButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(52.dp),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("Cancel", fontSize = 16.5.sp, fontWeight = FontWeight.Bold)
                            }
                            // Create Account button on right side
                            Button(
                                onClick = {
                                    SoundHelper.playSFX("click")
                                    if (name.isBlank()) {
                                        SoundHelper.playSFX("error")
                                        error = "Please enter full name"
                                        return@Button
                                    }
                                    if (phone.length < 10) {
                                        SoundHelper.playSFX("error")
                                        error = "Mobile number must be 10 digits"
                                        return@Button
                                    }
                                    if (password.length < 4) {
                                        SoundHelper.playSFX("error")
                                        error = "Password must be at least 4 characters"
                                        return@Button
                                    }

                                    val chosenTag = designationTag.trim().ifBlank { "Team Member" }
                                    val newUser = HurifixUser(
                                        phone = phone,
                                        name = name.trim(),
                                        password = password.trim(),
                                        role = HurifixUser.ROLE_STAFF,
                                        designation_tag = chosenTag,
                                        is_blocked = false,
                                        can_manage_orders = canManageOrders,
                                        can_add_experts = canAddExperts,
                                        can_add_customers = canAddCustomers,
                                        can_delete_orders = canDeleteOrders,
                                        can_export_reports = canExportReports,
                                        view_only = viewOnly,
                                        created_at = System.currentTimeMillis(),
                                        last_updated = System.currentTimeMillis()
                                    )
                                    SoundHelper.playSFX("success")
                                    onSave(newUser)
                                },
                                modifier = Modifier
                                    .weight(1.2f)
                                    .height(52.dp),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("Create Account", fontSize = 16.5.sp, fontWeight = FontWeight.Bold)
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
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name *", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) },
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 17.5.sp, fontWeight = FontWeight.Medium),
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(24.dp)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { input -> phone = input.filter { it.isDigit() }.take(10) },
                    label = { Text("Mobile Number (10 Digits) *", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) },
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 17.5.sp, fontWeight = FontWeight.Medium),
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(24.dp)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Initial Login Password *", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) },
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 17.5.sp, fontWeight = FontWeight.Medium),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(24.dp)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Divider(modifier = Modifier.padding(vertical = 4.dp))

                // Custom Designation Tag Input & Quick Select Presets
                Text(
                    text = "Designation / Role Tag *",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = designationTag,
                    onValueChange = { designationTag = it },
                    label = { Text("Custom Designation / Title", fontSize = 16.sp, fontWeight = FontWeight.SemiBold) },
                    placeholder = { Text("e.g. Co-Founder, Partner, Operations Head", fontSize = 15.sp) },
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 17.5.sp, fontWeight = FontWeight.Medium),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Quick Tag Suggestions:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    presetDesignations.forEach { tag ->
                        val isSelected = designationTag.equals(tag, ignoreCase = true)
                        Surface(
                            onClick = { designationTag = tag },
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(1.5.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Text(
                                text = tag,
                                fontSize = 14.5.sp,
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                            )
                        }
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 4.dp))

                Text(
                    text = "Feature & Module Access Permissions:",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                PermissionSwitchRow(
                    label = "Manage & Dispatch Orders",
                    checked = canManageOrders,
                    onCheckedChange = { canManageOrders = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Add & Edit Experts Directory",
                    checked = canAddExperts,
                    onCheckedChange = { canAddExperts = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Add & Edit Customers",
                    checked = canAddCustomers,
                    onCheckedChange = { canAddCustomers = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Delete Orders & Access Recycle Bin",
                    checked = canDeleteOrders,
                    onCheckedChange = { canDeleteOrders = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Export Performance & Analytics Reports",
                    checked = canExportReports,
                    onCheckedChange = { canExportReports = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "View Only Mode (Locks all modifications)",
                    checked = viewOnly,
                    onCheckedChange = { viewOnly = it }
                )

                error?.let { err ->
                    Text(err, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditRoleAndPermissionsDialog(
    user: HurifixUser,
    onDismiss: () -> Unit,
    onSave: (HurifixUser) -> Unit
) {
    var designationTag by remember { mutableStateOf(user.displayDesignation) }
    var canManageOrders by remember { mutableStateOf(user.can_manage_orders) }
    var canAddExperts by remember { mutableStateOf(user.can_add_experts) }
    var canAddCustomers by remember { mutableStateOf(user.can_add_customers) }
    var canDeleteOrders by remember { mutableStateOf(user.can_delete_orders) }
    var canExportReports by remember { mutableStateOf(user.can_export_reports) }
    var viewOnly by remember { mutableStateOf(user.view_only) }
    var isBlocked by remember { mutableStateOf(user.is_blocked) }

    val presetDesignations = remember { HurifixUser.DEFAULT_DESIGNATION_PRESETS }

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
                                text = "Edit Role & Permissions",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${user.name} (${user.phone})",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
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
                            OutlinedButton(
                                onClick = onDismiss,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Cancel")
                            }
                            Button(
                                onClick = {
                                    val updated = user.copy(
                                        designation_tag = designationTag.trim().ifBlank { "Team Member" },
                                        can_manage_orders = canManageOrders,
                                        can_add_experts = canAddExperts,
                                        can_add_customers = canAddCustomers,
                                        can_delete_orders = canDeleteOrders,
                                        can_export_reports = canExportReports,
                                        view_only = viewOnly,
                                        is_blocked = isBlocked,
                                        last_updated = System.currentTimeMillis()
                                    )
                                    onSave(updated)
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Save Changes", fontWeight = FontWeight.Bold)
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Configure designation & feature access for ${user.phone}:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Designation Tag Input & Presets
                Text(
                    text = "Designation / Role Tag *",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )

                OutlinedTextField(
                    value = designationTag,
                    onValueChange = { designationTag = it },
                    label = { Text("Designation Tag") },
                    placeholder = { Text("e.g. Co-Founder, Partner, Operations Head") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Quick Tag Suggestions:",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    presetDesignations.forEach { tag ->
                        val isSelected = designationTag.equals(tag, ignoreCase = true)
                        Surface(
                            onClick = { designationTag = tag },
                            shape = RoundedCornerShape(6.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            border = BorderStroke(1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Text(
                                text = tag,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 2.dp))

                Text(
                    text = "Feature Permissions:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )

                PermissionSwitchRow(
                    label = "Manage & Dispatch Orders",
                    checked = canManageOrders,
                    onCheckedChange = { canManageOrders = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Add & Edit Experts",
                    checked = canAddExperts,
                    onCheckedChange = { canAddExperts = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Add & Edit Customers",
                    checked = canAddCustomers,
                    onCheckedChange = { canAddCustomers = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Delete Orders & Access Recycle Bin",
                    checked = canDeleteOrders,
                    onCheckedChange = { canDeleteOrders = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "Export Performance & Analytics Reports",
                    checked = canExportReports,
                    onCheckedChange = { canExportReports = it },
                    enabled = !viewOnly
                )

                PermissionSwitchRow(
                    label = "View Only Mode (Locks all edits)",
                    checked = viewOnly,
                    onCheckedChange = { viewOnly = it }
                )

                Divider(modifier = Modifier.padding(vertical = 4.dp))

                PermissionSwitchRow(
                    label = "Restrict Account Access",
                    checked = isBlocked,
                    onCheckedChange = { isBlocked = it },
                    isDestructive = true
                )
            }
        }
    }
}

@Composable
private fun PermissionSwitchRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    isDestructive: Boolean = false
) {
    Surface(
        onClick = { if (enabled) onCheckedChange(!checked) },
        shape = RoundedCornerShape(12.dp),
        color = if (checked) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        border = BorderStroke(
            1.5.dp,
            if (checked) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            else MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 14.dp)) {
                Text(
                    text = label,
                    fontSize = 17.5.sp,
                    fontWeight = if (checked) FontWeight.ExtraBold else FontWeight.Bold,
                    color = if (isDestructive && checked) MaterialTheme.colorScheme.error
                    else if (!enabled) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (checked) "🟢 ON (Permission Enabled)" else "⚪ OFF (Permission Disabled)",
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (checked) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
        }
    }
}

@Composable
private fun ColumnScope.OrderActivityHistoryContent(
    allJobs: List<CustomerJobEntity>
) {
    val dateFormat = remember { SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf("ALL") }
    var selectedStaff by remember { mutableStateOf("ALL") }

    val staffMembers = remember(allJobs) {
        val list = mutableSetOf<String>()
        allJobs.forEach { job ->
            job.created_by_user_name?.let { if (it.isNotBlank()) list.add(it) }
            job.managed_by_user_name?.let { if (it.isNotBlank()) list.add(it) }
        }
        list.toList().sorted()
    }

    val filteredJobs = remember(allJobs, searchQuery, selectedStatus, selectedStaff) {
        allJobs.filter { job ->
            val matchesSearch = if (searchQuery.isBlank()) true else {
                val q = searchQuery.trim().lowercase()
                job.customerName.lowercase().contains(q) ||
                job.customerPhone.contains(q) ||
                job.serviceType.lowercase().contains(q) ||
                job.issueDescription.lowercase().contains(q) ||
                (job.created_by_user_name?.lowercase()?.contains(q) == true) ||
                (job.managed_by_user_name?.lowercase()?.contains(q) == true) ||
                (job.assigned_technician_name?.lowercase()?.contains(q) == true) ||
                (job.assignedExpertName?.lowercase()?.contains(q) == true)
            }

            val matchesStatus = if (selectedStatus == "ALL") true else {
                job.status.equals(selectedStatus, ignoreCase = true)
            }

            val matchesStaff = if (selectedStaff == "ALL") true else {
                job.created_by_user_name.equals(selectedStaff, ignoreCase = true) ||
                job.managed_by_user_name.equals(selectedStaff, ignoreCase = true)
            }

            matchesSearch && matchesStatus && matchesStaff
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Search Input
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search by customer, staff, technician, service...", fontSize = 12.sp) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear", modifier = Modifier.size(16.dp))
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        // Status Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val statusList = listOf(
                "ALL" to "All (${allJobs.size})",
                JobStatus.PENDING.name to "Pending (${allJobs.count { it.status == JobStatus.PENDING.name }})",
                JobStatus.PROCESSING.name to "Processing (${allJobs.count { it.status == JobStatus.PROCESSING.name }})",
                JobStatus.COMPLETED.name to "Completed (${allJobs.count { it.status == JobStatus.COMPLETED.name }})",
                JobStatus.CANCELLED.name to "Cancelled (${allJobs.count { it.status == JobStatus.CANCELLED.name }})"
            )

            statusList.forEach { (statusKey, label) ->
                val isSelected = selectedStatus == statusKey
                Surface(
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.clickable { selectedStatus = statusKey }
                ) {
                    Text(
                        text = label,
                        fontSize = 11.5.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Staff filter chips (if staff are available)
        if (staffMembers.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Staff Filter:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Surface(
                    color = if (selectedStaff == "ALL") MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.clickable { selectedStaff = "ALL" }
                ) {
                    Text(
                        text = "All Staff",
                        fontSize = 11.sp,
                        fontWeight = if (selectedStaff == "ALL") FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedStaff == "ALL") MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                staffMembers.forEach { staffName ->
                    val isSelected = selectedStaff == staffName
                    Surface(
                        color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.clickable { selectedStaff = staffName }
                    ) {
                        Text(
                            text = staffName,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Quick Stats Summary Bar
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Showing ${filteredJobs.size} of ${allJobs.size} orders",
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Active: ${filteredJobs.count { it.status == JobStatus.PROCESSING.name }} • Done: ${filteredJobs.count { it.status == JobStatus.COMPLETED.name }}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (filteredJobs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        Icons.Default.History,
                        contentDescription = null,
                        modifier = Modifier.size(44.dp),
                        tint = MaterialTheme.colorScheme.outlineVariant
                    )
                    Text(
                        text = "No order activity records found",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Try adjusting your search query or status filter chips",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredJobs, key = { it.id }) { job ->
                    OrderAuditItemCard(job = job, dateFormat = dateFormat)
                }
            }
        }
    }
}

@Composable
private fun OrderAuditItemCard(
    job: CustomerJobEntity,
    dateFormat: SimpleDateFormat
) {
    val statusColor = when (job.status) {
        JobStatus.PENDING.name -> Color(0xFFF59E0B)
        JobStatus.PROCESSING.name -> Color(0xFF3B82F6)
        JobStatus.COMPLETED.name -> Color(0xFF16A34A)
        JobStatus.CANCELLED.name -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header Row: Order ID, Service Type & Status Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "Order #${job.id}",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                        )
                    }

                    Text(
                        text = job.serviceType,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Surface(
                    color = statusColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = job.status,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Customer Info Row
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "👤 ${job.customerName} (${job.customerPhone})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (job.issueDescription.isNotBlank()) {
                    Text(
                        text = "📝 Problem: ${job.issueDescription}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                if (job.address.isNotBlank()) {
                    Text(
                        text = "📍 ${job.address}",
                        fontSize = 11.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Audit Trail Details Box
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "📋 AUDIT LOG METADATA",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Created by
                    val creatorName = job.created_by_user_name ?: "Admin"
                    val creatorDesig = job.created_by_designation?.let { " ($it)" } ?: ""
                    Text(
                        text = "• Created By: $creatorName$creatorDesig on ${dateFormat.format(Date(job.createdAt))}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Managed by
                    val managerName = job.managed_by_user_name ?: "Unassigned"
                    val managerDesig = job.managed_by_designation?.let { " ($it)" } ?: ""
                    Text(
                        text = "• Currently Managed By: $managerName$managerDesig",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (job.managed_by_user_name != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Technician Assigned
                    val techName = job.assigned_technician_name ?: job.assignedExpertName
                    if (techName != null) {
                        val techTime = job.assigned_at_timestamp?.let { dateFormat.format(Date(it)) } ?: "Recorded"
                        Text(
                            text = "• Technician Assigned: 👨‍🔧 $techName (Phone: ${job.assignedExpertPhone ?: "N/A"}) at $techTime",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Text(
                            text = "• Technician: ⚠️ No technician assigned yet (Pending dispatch)",
                            fontSize = 11.sp,
                            color = Color(0xFFB45309)
                        )
                    }

                    // Completed At & Feedback if available
                    if (job.completedAt != null) {
                        Text(
                            text = "• Completed At: ✅ ${dateFormat.format(Date(job.completedAt))}",
                            fontSize = 11.sp,
                            color = Color(0xFF15803D),
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    if (job.ratingGiven != null) {
                        Text(
                            text = "• Customer Feedback: ⭐ ${job.ratingGiven.toInt()}/5 ${job.reviewFeedback?.let { "(\"$it\")" } ?: ""}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Last updated
                    Text(
                        text = "• Last Activity Update: ${dateFormat.format(Date(job.last_updated))}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}
