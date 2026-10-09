package com.example.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.data.model.ExpertEntity
import com.example.ui.theme.AmberWarning

private val WhatsAppDarkGreen = Color(0xFF15803D)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpertCard(
    expert: ExpertEntity,
    modifier: Modifier = Modifier,
    isAdmin: Boolean = false,
    onCall: () -> Unit,
    onWhatsApp: () -> Unit,
    onViewMap: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onToggleAvailability: (Boolean) -> Unit,
    onViewWorkHistory: () -> Unit,
    onSendWelcome: () -> Unit,
    onLongPress: (() -> Unit)? = null
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    var showConfirmDialog by remember { mutableStateOf(false) }
    var confirmTitle by remember { mutableStateOf("") }
    var confirmMessage by remember { mutableStateOf("") }
    var confirmButtonText by remember { mutableStateOf("Confirm") }
    var confirmIsDestructive by remember { mutableStateOf(false) }
    var confirmIcon by remember { mutableStateOf(Icons.Default.Warning) }
    var confirmAction by remember { mutableStateOf<() -> Unit>({}) }
    var showFullScreenImage by remember { mutableStateOf(false) }

    val createdTimestamp = if (expert.created_at_timestamp > 0) expert.created_at_timestamp else expert.createdAt
    val isTimeLocked = !isAdmin && ((System.currentTimeMillis() - createdTimestamp) > 24 * 60 * 60 * 1000L)

    fun requestConfirm(
        title: String,
        message: String,
        buttonText: String = "Confirm",
        isDestructive: Boolean = false,
        icon: ImageVector = Icons.Default.Warning,
        action: () -> Unit
    ) {
        confirmTitle = title
        confirmMessage = message
        confirmButtonText = buttonText
        confirmIsDestructive = isDestructive
        confirmIcon = icon
        confirmAction = action
        showConfirmDialog = true
    }

    if (showConfirmDialog) {
        HurifixConfirmDialog(
            title = confirmTitle,
            message = confirmMessage,
            confirmText = confirmButtonText,
            isDestructive = confirmIsDestructive,
            icon = confirmIcon,
            onConfirm = {
                showConfirmDialog = false
                confirmAction()
            },
            onDismiss = { showConfirmDialog = false }
        )
    }

    // Full Display Dialog for Expert Profile Picture with Minimize Action
    if (showFullScreenImage && !expert.profilePicUrl.isNullOrBlank()) {
        Dialog(
            onDismissRequest = { showFullScreenImage = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.94f))
                    .clickable { showFullScreenImage = false },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Top Bar: Expert details & Close Icon Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 4.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = expert.name,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "${expert.category} • +91 ${expert.phone}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.75f)
                            )
                        }

                        // Close Icon Button (Top Right)
                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.2f),
                            modifier = Modifier.size(42.dp)
                        ) {
                            IconButton(
                                onClick = { showFullScreenImage = false },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Full View",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }

                    // Center: Full Display Image View
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .navigationBarsPadding()
                            .padding(vertical = 12.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { /* Prevent dismissing when clicking the image container itself */ },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(expert.profilePicUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "${expert.name} Full Profile Picture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }
            }
        }
    }

    val (catBg, catFg) = when (expert.category.lowercase()) {
        "electrician" -> Color(0xFFEFF6FF) to Color(0xFF1D4ED8)
        "plumber" -> Color(0xFFECFDF5) to Color(0xFF047857)
        "carpenter" -> Color(0xFFFFFBEB) to Color(0xFFB45309)
        "painter" -> Color(0xFFFDF4FF) to Color(0xFF9333EA)
        else -> Color(0xFFF3E8FF) to Color(0xFF7E22CE)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("expert_item_${expert.id}")
            .then(
                if (onLongPress != null) {
                    Modifier.combinedClickable(
                        onClick = {},
                        onLongClick = onLongPress
                    )
                } else Modifier
            ),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header Row: Avatar, Name, Category pill & Availability switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Avatar: Circle with background gradient fallback & high-res profile picture overlay
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF3B82F6), Color(0xFF1D4ED8))
                            )
                        )
                        .clickable(enabled = !expert.profilePicUrl.isNullOrBlank()) {
                            showFullScreenImage = true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = expert.name.take(1).uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )

                    if (!expert.profilePicUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(expert.profilePicUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "${expert.name} Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = expert.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Surface(
                            color = catBg,
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, catFg.copy(alpha = 0.3f))
                        ) {
                            Text(
                                text = "🛠️ ${expert.category}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = catFg,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Text(
                            text = "📱 ${expert.phone}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Switch(
                    checked = expert.isAvailable,
                    onCheckedChange = { newAvail ->
                        requestConfirm(
                            title = "Change Status?",
                            message = "Change ${expert.name}'s status to ${if (newAvail) "Available" else "Busy / Unavailable"}?",
                            buttonText = "Update Status",
                            action = { onToggleAvailability(newAvail) }
                        )
                    },
                    modifier = Modifier.testTag("expert_avail_switch_${expert.id}")
                )
            }

            // Location & Rating row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = expert.address,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }

                Surface(
                    color = Color(0xFFFEF3C7),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(13.dp), tint = AmberWarning)
                        Text(text = "${expert.rating} (${expert.completedJobsCount} jobs)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                    }
                }
            }

            // Welcome Message Status Indicator Row
            Surface(
                color = if (expert.isWelcomeMessageSent) Color(0xFFECFDF5) else Color(0xFFFFFBEB),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, if (expert.isWelcomeMessageSent) Color(0xFFA7F3D0) else Color(0xFFFDE68A)),
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
                        Text(
                            text = if (expert.isWelcomeMessageSent) "✅ Welcome WhatsApp Sent" else "⚠️ Welcome WhatsApp not sent yet",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (expert.isWelcomeMessageSent) Color(0xFF047857) else Color(0xFFB45309)
                        )
                    }

                    if (!expert.isWelcomeMessageSent) {
                        FilledTonalButton(
                            onClick = {
                                requestConfirm(
                                    title = "Send Welcome Message?",
                                    message = "Send welcome WhatsApp message to ${expert.name} (${expert.phone})?",
                                    buttonText = "Send Now",
                                    icon = Icons.AutoMirrored.Filled.Send,
                                    action = onSendWelcome
                                )
                            },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = Color(0xFFDCFCE7),
                                contentColor = Color(0xFF15803D)
                            ),
                            modifier = Modifier.height(28.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(12.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Send Now", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        TextButton(
                            onClick = {
                                requestConfirm(
                                    title = "Send Welcome Again?",
                                    message = "Send welcome WhatsApp message again to ${expert.name} (${expert.phone})?",
                                    buttonText = "Send Again",
                                    icon = Icons.AutoMirrored.Filled.Send,
                                    action = onSendWelcome
                                )
                            },
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 6.dp, vertical = 2.dp),
                            modifier = Modifier.height(26.dp)
                        ) {
                            Text("Send Again", fontSize = 11.sp, color = WhatsAppDarkGreen)
                        }
                    }
                }
            }

            // Work History Button
            FilledTonalButton(
                onClick = onViewWorkHistory,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
            ) {
                Icon(Icons.Default.History, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "View Work History & Customers",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            // Creator Attribution Badge
            val creatorName = expert.added_by_user_name ?: "Admin"
            val creatorDesig = expert.added_by_designation?.let { " ($it)" } ?: ""

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
                    Text(
                        text = "👤 Added by: $creatorName$creatorDesig",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                    if (isTimeLocked) {
                        Surface(
                            color = Color(0xFFFEE2E2),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "🔒 24h Lock",
                                fontSize = 10.sp,
                                color = Color(0xFFB91C1C),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            // Action Buttons: Call, WhatsApp, Map, Edit, Delete (Protected with HurifixConfirmDialog)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = {
                        requestConfirm(
                            title = "Call Expert?",
                            message = "Call expert ${expert.name} at ${expert.phone}?",
                            buttonText = "Call Now",
                            icon = Icons.Default.Call,
                            action = onCall
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Call", fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = {
                        requestConfirm(
                            title = "Open WhatsApp?",
                            message = "Chat with expert ${expert.name} (${expert.phone}) on WhatsApp?",
                            buttonText = "Open WhatsApp",
                            icon = Icons.AutoMirrored.Filled.Chat,
                            action = onWhatsApp
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = null, modifier = Modifier.size(16.dp), tint = WhatsAppDarkGreen)
                    Spacer(Modifier.width(4.dp))
                    Text("WhatsApp", fontSize = 12.sp, color = WhatsAppDarkGreen)
                }

                IconButton(onClick = onViewMap, modifier = Modifier.size(38.dp)) {
                    Icon(Icons.Default.LocationOn, contentDescription = "View Map", tint = MaterialTheme.colorScheme.primary)
                }

                IconButton(
                    onClick = {
                        if (isTimeLocked) {
                            android.widget.Toast.makeText(
                                context,
                                "Editing locked after 24 hours. Contact Admin to make changes.",
                                android.widget.Toast.LENGTH_LONG
                            ).show()
                        }
                        onEdit()
                    },
                    modifier = Modifier.size(38.dp)
                ) {
                    Icon(
                        if (isTimeLocked) Icons.Default.Lock else Icons.Default.Edit,
                        contentDescription = if (isTimeLocked) "Locked (View Only)" else "Edit",
                        tint = if (isTimeLocked) Color(0xFFB91C1C) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (!isTimeLocked || isAdmin) {
                    IconButton(
                        onClick = {
                            requestConfirm(
                                title = "Move to Recycle Bin?",
                                message = "Are you sure you want to move expert ${expert.name} (${expert.category}) to the recycle bin?",
                                buttonText = "Yes, Move to Bin",
                                isDestructive = true,
                                icon = Icons.Default.Delete,
                                action = onDelete
                            )
                        },
                        modifier = Modifier.size(38.dp)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}
