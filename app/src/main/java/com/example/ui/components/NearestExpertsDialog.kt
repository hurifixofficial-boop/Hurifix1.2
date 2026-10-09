package com.example.ui.components

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.CustomerJobEntity
import com.example.data.model.RankedExpert
import com.example.util.LocationHelper
import com.example.util.WhatsAppHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NearestExpertsDialog(
    job: CustomerJobEntity,
    rankedExperts: List<RankedExpert>,
    onDismiss: () -> Unit,
    onAssignExpert: (RankedExpert) -> Unit
) {
    val context = LocalContext.current

    var showConfirmDialog by remember { mutableStateOf(false) }
    var confirmTitle by remember { mutableStateOf("") }
    var confirmMessage by remember { mutableStateOf("") }
    var confirmButtonText by remember { mutableStateOf("Confirm") }
    var confirmIcon by remember { mutableStateOf(Icons.Default.Warning) }
    var confirmAction by remember { mutableStateOf<() -> Unit>({}) }

    fun requestConfirm(
        title: String,
        message: String,
        buttonText: String = "Confirm",
        icon: ImageVector = Icons.Default.Warning,
        action: () -> Unit
    ) {
        confirmTitle = title
        confirmMessage = message
        confirmButtonText = buttonText
        confirmIcon = icon
        confirmAction = action
        showConfirmDialog = true
    }

    if (showConfirmDialog) {
        HurifixConfirmDialog(
            title = confirmTitle,
            message = confirmMessage,
            confirmText = confirmButtonText,
            icon = confirmIcon,
            onConfirm = {
                showConfirmDialog = false
                confirmAction()
            },
            onDismiss = { showConfirmDialog = false }
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("📍", fontSize = 18.sp)
                                Text(
                                    text = "Nearest Experts - Order #${job.id}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "${job.customerName} • ${job.serviceType} • ${job.address}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1
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
            }
        ) { paddingValues ->
            if (rankedExperts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No experts currently available.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(rankedExperts) { ranked ->
                        val expert = ranked.expert
                        val isAssigned = job.assignedExpertId == expert.id

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outlineVariant),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier.weight(1f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Surface(
                                            shape = androidx.compose.foundation.shape.CircleShape,
                                            color = MaterialTheme.colorScheme.primaryContainer,
                                            modifier = Modifier.size(38.dp)
                                        ) {
                                            if (!expert.profilePicUrl.isNullOrBlank()) {
                                                coil.compose.AsyncImage(
                                                    model = expert.profilePicUrl,
                                                    contentDescription = null,
                                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                                    modifier = Modifier.size(38.dp)
                                                )
                                            } else {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(
                                                        text = expert.name.take(1).uppercase(),
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            }
                                        }

                                        Column {
                                            Text(
                                                text = expert.name,
                                                fontWeight = FontWeight.Bold,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                            Text(
                                                text = expert.category,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }

                                    // Rating
                                    Surface(
                                        color = Color(0xFFFEF3C7),
                                        shape = RoundedCornerShape(6.dp),
                                        border = BorderStroke(1.dp, Color(0xFFFDE68A))
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                                        ) {
                                            Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(13.dp), tint = Color(0xFFD97706))
                                            Text(text = "${expert.rating}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                                        }
                                    }
                                }

                                Text(
                                    text = "📱 ${expert.phone}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                // Distance & Estimated Travel Time Badges
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        color = Color(0xFFEFF6FF),
                                        shape = RoundedCornerShape(6.dp),
                                        border = BorderStroke(1.dp, Color(0xFFBFDBFE))
                                    ) {
                                        Text(
                                            text = "📍 ${LocationHelper.formatDistance(ranked.distanceKm)} away",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF1D4ED8),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }

                                    Surface(
                                        color = Color(0xFFFFFBEB),
                                        shape = RoundedCornerShape(6.dp),
                                        border = BorderStroke(1.dp, Color(0xFFFDE68A))
                                    ) {
                                        Text(
                                            text = "⏱ ~${ranked.travelTimeMinutes} mins travel",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFB45309),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }

                                if (isAssigned) {
                                    Surface(
                                        color = Color(0xFFDCFCE7),
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "✅ Currently Assigned to this Order",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp,
                                            color = Color(0xFF15803D),
                                            modifier = Modifier.padding(6.dp)
                                        )
                                    }
                                }

                                // Action Buttons (Assign, WhatsApp, Call) protected with HurifixConfirmDialog
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
                                            requestConfirm(
                                                title = "Assign Expert?",
                                                message = "Assign ${expert.name} (${expert.category}) to Order #${job.id} for ${job.customerName}?",
                                                buttonText = "Yes, Assign",
                                                icon = Icons.Default.CheckCircle,
                                                action = { onAssignExpert(ranked) }
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.weight(1.3f).height(40.dp),
                                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                                    ) {
                                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(Modifier.width(3.dp))
                                        Text(
                                            text = "Assign",
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            softWrap = false,
                                            fontSize = 12.sp
                                        )
                                    }

                                    OutlinedButton(
                                        onClick = {
                                            if (isAssigned) {
                                                requestConfirm(
                                                    title = "Send WhatsApp?",
                                                    message = "Send dispatch details and customer location to ${expert.name} on WhatsApp?",
                                                    buttonText = "Send via WhatsApp",
                                                    icon = Icons.AutoMirrored.Filled.Send,
                                                    action = {
                                                        WhatsAppHelper.sendWhatsAppMessageToExpert(
                                                            context = context,
                                                            expert = expert,
                                                            customer = job
                                                        )
                                                    }
                                                )
                                            } else {
                                                Toast.makeText(context, "Please click Assign first before sending WhatsApp details!", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        enabled = isAssigned,
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.weight(1.3f).height(40.dp),
                                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            contentColor = Color(0xFF16A34A),
                                            disabledContentColor = Color.Gray
                                        )
                                    ) {
                                        Icon(
                                            Icons.AutoMirrored.Filled.Send,
                                            contentDescription = null,
                                            tint = if (isAssigned) Color(0xFF25D366) else Color.Gray,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Spacer(Modifier.width(3.dp))
                                        Text(
                                            text = if (isAssigned) "WhatsApp" else "Assign First",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }

                                    OutlinedButton(
                                        onClick = {
                                            requestConfirm(
                                                title = "Call Expert?",
                                                message = "Call expert ${expert.name} at ${expert.phone}?",
                                                buttonText = "Call Now",
                                                icon = Icons.Default.Phone,
                                                action = { WhatsAppHelper.openDialer(context, expert.phone) }
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.weight(0.9f).height(40.dp),
                                        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
                                    ) {
                                        Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(13.dp))
                                        Spacer(Modifier.width(2.dp))
                                        Text(
                                            text = "Call",
                                            fontSize = 11.5.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
