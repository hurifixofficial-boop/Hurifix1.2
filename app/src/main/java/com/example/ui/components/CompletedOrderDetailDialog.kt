package com.example.ui.components

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.CustomerJobEntity
import com.example.util.LocationHelper
import com.example.util.WhatsAppHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedOrderDetailDialog(
    job: CustomerJobEntity,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val dateTimeFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    val timeOnlyFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    val createdDateStr = dateTimeFormat.format(Date(job.createdAt))
    val completedDateStr = job.completedAt?.let { dateTimeFormat.format(Date(it)) } ?: "Recorded"

    // Calculate Task Duration
    val durationText = calculateTaskDuration(job.createdAt, job.completedAt)

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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = Color(0xFFDCFCE7),
                                modifier = Modifier.size(36.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = Color(0xFF16A34A),
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }
                            Column {
                                Text(
                                    text = "Order #${job.id} - Completed",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Work Summary & Warranty Details",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
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
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // 10-Day Warranty Verification Card
                val completedTime = job.completedAt ?: job.createdAt
                val daysPassed = ((System.currentTimeMillis() - completedTime) / (1000 * 60 * 60 * 24)).toInt()
                val isWarrantyActive = daysPassed <= 10
                val daysRemaining = maxOf(0, 10 - daysPassed)

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (isWarrantyActive) Color(0xFFDCFCE7) else Color(0xFFFEE2E2)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = if (isWarrantyActive) "🛡️" else "⚠️",
                            fontSize = 24.sp
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isWarrantyActive) "10-DAY WARRANTY ACTIVE" else "10-DAY WARRANTY EXPIRED",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = if (isWarrantyActive) Color(0xFF15803D) else Color(0xFFB91C1C)
                            )
                            Text(
                                text = if (isWarrantyActive) {
                                    "Customer is eligible for free warranty re-inspection ($daysRemaining days remaining)."
                                } else {
                                    "Completed $daysPassed days ago. The 10-day warranty coverage has expired."
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = if (isWarrantyActive) Color(0xFF166534) else Color(0xFF991B1B)
                            )
                        }
                    }
                }

                // Duration & Timeline Highlight Card
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF16A34A).copy(alpha = 0.08f)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF16A34A),
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    Icons.Default.Timer,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "TASK DURATION",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF15803D),
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = durationText,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF166534)
                            )
                            Text(
                                text = "Started: ${timeOnlyFormat.format(Date(job.createdAt))} • Finished: ${job.completedAt?.let { timeOnlyFormat.format(Date(it)) } ?: "Done"}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Customer Feedback & Rating
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Customer Rating & Feedback",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            val rating = job.ratingGiven ?: 5f
                            for (i in 1..5) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = null,
                                    tint = if (i <= rating) Color(0xFFF59E0B) else Color.LightGray,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Text(
                                text = " ${rating.toInt()} / 5 Stars",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFFB45309)
                            )
                        }

                        if (!job.reviewFeedback.isNullOrBlank()) {
                            Surface(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "“${job.reviewFeedback}”",
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }

                // Assigned Expert Dossier
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Assigned Expert",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = job.assignedExpertName ?: "Expert",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                job.assignedExpertPhone?.let {
                                    Text(
                                        text = "📞 $it",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                job.assignedExpertPhone?.let { phone ->
                                    IconButton(onClick = { WhatsAppHelper.openDialer(context, phone) }) {
                                        Icon(Icons.Default.Phone, contentDescription = "Call", tint = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }
                        }
                    }
                }

                // Customer Information
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Customer & Location",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = "👤 ${job.customerName}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "📞 ${job.customerPhone}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "📍 ${job.address}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { WhatsAppHelper.openDialer(context, job.customerPhone) },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("Call Customer", fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = {
                                    WhatsAppHelper.openGoogleMaps(
                                        context = context,
                                        latitude = job.latitude,
                                        longitude = job.longitude,
                                        label = job.customerName
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("Open Map", fontSize = 12.sp)
                            }
                        }
                    }
                }

                // Service Details
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Work Details",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "🛠 Service: ${job.serviceType}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        if (job.issueDescription.isNotBlank()) {
                            Text(
                                text = "📝 Problem: ${job.issueDescription}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Share Receipt on WhatsApp Button
                Button(
                    onClick = {
                        val receiptMessage = """
✅ *HURIFIX WORK COMPLETION RECEIPT* ✅
━━━━━━━━━━━━━━━━━━━━
*Order ID:* #${job.id}
*Customer:* ${job.customerName} (${job.customerPhone})
*Address:* ${job.address}
*Service:* ${job.serviceType}
*Expert Assigned:* ${job.assignedExpertName ?: "Hurifix Expert"}
*Task Duration:* $durationText
*Rating:* ${job.ratingGiven?.let { "$it/5 Stars" } ?: "5/5"}
*Feedback:* ${job.reviewFeedback ?: "Satisfactory"}
*Completed On:* $completedDateStr
━━━━━━━━━━━━━━━━━━━━
Thank you for choosing Hurifix!
_Many Problems | One Solution_
                        """.trimIndent()

                        WhatsAppHelper.copyToClipboard(context, "Work Receipt", receiptMessage)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Copy / Share Receipt", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

/**
 * Calculates human-readable task duration.
 */
fun calculateTaskDuration(startTimeMs: Long, endTimeMs: Long?): String {
    if (endTimeMs == null || endTimeMs <= startTimeMs) {
        return "45 minutes"
    }
    val diffMs = endTimeMs - startTimeMs
    val totalMinutes = diffMs / (1000 * 60)
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    val days = hours / 24

    return when {
        days > 0 -> "$days days, ${hours % 24} hrs"
        hours > 0 && minutes > 0 -> "$hours hr $minutes mins"
        hours > 0 -> "$hours hr${if (hours > 1) "s" else ""}"
        minutes > 0 -> "$minutes mins"
        else -> "Under 15 mins"
    }
}
