package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.JobStatus
import com.example.data.model.RankedExpert
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun calculateTaskDuration(startTime: Long, endTime: Long? = null): String {
    val end = endTime ?: System.currentTimeMillis()
    val diffMinutes = ((end - startTime) / 60000).coerceAtLeast(1L)
    val hours = diffMinutes / 60
    val mins = diffMinutes % 60
    return if (hours > 0) "${hours}h ${mins}m" else "${mins}m"
}

fun calculateTaskDuration(job: CustomerJobEntity): String {
    return calculateTaskDuration(job.createdAt, job.completedAt)
}

@Composable
fun CompletedOrderDetailDialog(
    job: CustomerJobEntity,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order Details (#${job.id})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("👤 Customer: ${job.customerName}", fontWeight = FontWeight.SemiBold)
                        Text("📞 Phone: ${job.customerPhone}")
                        Text("🔧 Service: ${job.serviceType}")
                        if (job.address.isNotBlank()) {
                            Text("📍 Address: ${job.address}")
                        }
                        if (job.issueDescription.isNotBlank()) {
                            Text("📝 Issue: ${job.issueDescription}")
                        }
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        val expName = job.assignedExpertName ?: "None"
                        val expPhone = job.assignedExpertPhone ?: "None"
                        Text("🛠 Assigned Expert: $expName ($expPhone)", fontWeight = FontWeight.SemiBold)
                        Text("⏱ Duration: ${calculateTaskDuration(job)}")
                        if (job.ratingGiven != null) {
                            Text("⭐ Rating: ${job.ratingGiven}/5.0")
                        }
                        if (!job.reviewFeedback.isNullOrBlank()) {
                            Text("💬 Feedback: ${job.reviewFeedback}")
                        }
                        Text("📊 Status: ${job.status}", fontWeight = FontWeight.Bold)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
fun ExpertsRankingDialog(
    experts: List<ExpertEntity>,
    jobs: List<CustomerJobEntity> = emptyList(),
    onDismiss: () -> Unit
) {
    val sortedExperts = remember(experts) {
        experts.sortedByDescending { it.rating }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🏆 Experts Leaderboard",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            if (sortedExperts.isEmpty()) {
                Text("No experts found.")
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sortedExperts) { expert ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(expert.name, fontWeight = FontWeight.Bold)
                                    Text(
                                        "${expert.category} • Completed: ${expert.completedJobsCount}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Rating",
                                        tint = Color(0xFFF59E0B),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        String.format("%.1f", expert.rating),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
fun MonthlyAnalyticsDialog(
    jobs: List<CustomerJobEntity> = emptyList(),
    allJobs: List<CustomerJobEntity> = emptyList(),
    onDismiss: () -> Unit
) {
    val currentJobs = if (jobs.isNotEmpty()) jobs else allJobs
    val totalOrders = currentJobs.size
    val completedOrders = currentJobs.count { it.status.equals(JobStatus.COMPLETED.name, ignoreCase = true) }
    val cancelledOrders = currentJobs.count { it.status.equals(JobStatus.CANCELLED.name, ignoreCase = true) }
    val pendingOrders = currentJobs.count { it.status.equals(JobStatus.PENDING.name, ignoreCase = true) }
    val assignedOrders = currentJobs.count { it.status.equals(JobStatus.PROCESSING.name, ignoreCase = true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "📊 Order Analytics",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Total Orders: $totalOrders", fontWeight = FontWeight.Bold)
                        HorizontalDivider()
                        Text("✅ Completed: $completedOrders", color = Color(0xFF16A34A))
                        Text("⏳ Assigned / In Progress: $assignedOrders", color = Color(0xFF3B82F6))
                        Text("🕒 Pending Dispatch: $pendingOrders", color = Color(0xFFF59E0B))
                        Text("❌ Cancelled: $cancelledOrders", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
fun NearestExpertsDialog(
    job: CustomerJobEntity,
    rankedExperts: List<RankedExpert> = emptyList(),
    nearestExperts: List<RankedExpert> = emptyList(),
    onAssignExpert: ((RankedExpert) -> Unit)? = null,
    onAssign: ((RankedExpert) -> Unit)? = null,
    onDismiss: () -> Unit
) {
    val list = if (rankedExperts.isNotEmpty()) rankedExperts else nearestExperts
    val assignCallback = onAssignExpert ?: onAssign

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "📍 Nearest Experts",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            if (list.isEmpty()) {
                Text("No available experts nearby.")
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(list) { ranked ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(ranked.expert.name, fontWeight = FontWeight.Bold)
                                    Text(
                                        "${ranked.expert.category} • ${String.format("%.1f", ranked.distanceKm)} km away",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        "⭐ ${String.format("%.1f", ranked.expert.rating)} (${ranked.expert.completedJobsCount} done)",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Button(
                                    onClick = { assignCallback?.invoke(ranked) },
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Assign")
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ReviewDialog(
    job: CustomerJobEntity,
    isCompletedAction: Boolean = false,
    isComplete: Boolean = false,
    onConfirm: (Float, String) -> Unit,
    onDismiss: () -> Unit
) {
    var rating by remember { mutableStateOf(5f) }
    var feedback by remember { mutableStateOf("") }
    val isDone = isCompletedAction || isComplete

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (isDone) "Complete Order Review" else "Cancel Order",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Rate the service performance:")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    (1..5).forEach { star ->
                        IconButton(onClick = { rating = star.toFloat() }) {
                            Icon(
                                imageVector = if (rating >= star) Icons.Default.Star else Icons.Outlined.Star,
                                contentDescription = "$star Stars",
                                tint = if (rating >= star) Color(0xFFF59E0B) else Color.Gray,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    label = { Text("Feedback / Notes") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(rating, feedback) }) {
                Text(if (isDone) "Submit & Complete" else "Submit & Cancel")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun WhatsAppLeadParserDialog(
    onDismiss: () -> Unit,
    onParseText: ((String) -> Unit)? = null,
    onParsed: ((String) -> Unit)? = null
) {
    var text by remember { mutableStateOf("") }
    val callback = onParseText ?: onParsed

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "📋 Paste WhatsApp Lead",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Paste any message containing customer details, service type, and address or coordinates to auto-fill the order form.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("WhatsApp message text") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    maxLines = 8
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    callback?.invoke(text)
                },
                enabled = text.isNotBlank()
            ) {
                Text("Parse & Fill")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ImageSourcePickerDialog(
    title: String = "Select Photo Source",
    onSelectCamera: () -> Unit,
    onSelectGallery: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectCamera() },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Camera",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Take Photo (Camera)",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectGallery() },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoLibrary,
                            contentDescription = "Gallery",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Choose from Gallery",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun OtpVerificationDialog(
    phone: String = "",
    purposeTitle: String = "Verify OTP",
    purposeSubtitle: String = "Enter the 6-digit verification code",
    onSuccess: (() -> Unit)? = null,
    onVerified: (() -> Unit)? = null,
    onDismiss: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    val verifyCallback = onSuccess ?: onVerified

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = purposeTitle,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = purposeSubtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (phone.isNotBlank()) {
                    Text(
                        text = "Phone: +91 $phone",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                OutlinedTextField(
                    value = otp,
                    onValueChange = { if (it.length <= 6) otp = it.filter { c -> c.isDigit() } },
                    label = { Text("6-Digit OTP") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { verifyCallback?.invoke() },
                enabled = otp.length >= 4
            ) {
                Text("Verify")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ExpertWorkHistoryDialog(
    expert: ExpertEntity,
    allJobs: List<CustomerJobEntity> = emptyList(),
    jobs: List<CustomerJobEntity> = emptyList(),
    onDismiss: () -> Unit
) {
    val jobList = if (allJobs.isNotEmpty()) allJobs else jobs
    val expertJobs = remember(jobList, expert.id) {
        jobList.filter { it.assignedExpertId == expert.id }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Work History - ${expert.name}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            if (expertJobs.isEmpty()) {
                Text("No previous jobs found for this expert.")
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(expertJobs) { job ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "#${job.id} ${job.serviceType}",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = job.status,
                                        fontWeight = FontWeight.Bold,
                                        color = if (job.status.equals(JobStatus.COMPLETED.name, ignoreCase = true)) Color(0xFF16A34A) else MaterialTheme.colorScheme.primary
                                    )
                                }
                                Text("Customer: ${job.customerName} (${job.customerPhone})", style = MaterialTheme.typography.bodySmall)
                                if (job.address.isNotBlank()) {
                                    Text("Address: ${job.address}", style = MaterialTheme.typography.bodySmall)
                                }
                                val formattedDate = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(job.createdAt))
                                Text("Date: $formattedDate", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
