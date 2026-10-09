package com.example.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.RankedExpert

fun calculateTaskDuration(createdAt: Long, completedAt: Long?): String {
    val diff = (completedAt ?: System.currentTimeMillis()) - createdAt
    val hours = diff / (1000 * 60 * 60)
    val minutes = (diff / (1000 * 60)) % 60
    return if (hours > 0) "$hours hrs $minutes mins" else "$minutes mins"
}

@Composable
fun NearestExpertsDialog(
    job: CustomerJobEntity,
    rankedExperts: List<RankedExpert>,
    onDismiss: () -> Unit,
    onAssignExpert: (RankedExpert) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nearest Experts for ${job.serviceType}") },
        text = {
            Column {
                if (rankedExperts.isEmpty()) {
                    Text("No experts found nearby.")
                } else {
                    rankedExperts.forEach { ranked ->
                        Button(onClick = { onAssignExpert(ranked) }) {
                            Text("${ranked.expert.name} (${ranked.distanceKm} km)")
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun CompletedOrderDetailDialog(
    job: CustomerJobEntity,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Order Details #${job.id}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Customer: ${job.customerName}")
                Text("Phone: ${job.customerPhone}")
                Text("Service: ${job.serviceType}")
                Text("Status: ${job.status}")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun ExpertsRankingDialog(
    experts: List<ExpertEntity>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Experts Leaderboard") },
        text = {
            Column {
                Text("Total active experts: ${experts.size}")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun MonthlyAnalyticsDialog(
    jobs: List<CustomerJobEntity>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Monthly Performance & Reports") },
        text = {
            Column {
                Text("Total orders: ${jobs.size}")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun ReviewDialog(
    job: CustomerJobEntity,
    isCompletedAction: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Int, String) -> Unit
) {
    var rating by remember { mutableStateOf(5) }
    var feedback by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Review Order") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Rate service quality for ${job.customerName}")
                OutlinedTextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    label = { Text("Feedback (Optional)") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(rating, feedback) }) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun WhatsAppLeadParserDialog(
    onDismiss: () -> Unit,
    onParsed: (String) -> Unit = {},
    onParseText: (String) -> Unit = onParsed
) {
    var text by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Parse WhatsApp Lead") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Paste WhatsApp Message") }
            )
        },
        confirmButton = {
            Button(onClick = { onParseText(text); onDismiss() }) {
                Text("Parse & Fill")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun ExpertWorkHistoryDialog(
    expert: ExpertEntity,
    allJobs: List<CustomerJobEntity> = emptyList(),
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Work History: ${expert.name}") },
        text = {
            Text("Phone: ${expert.phone}\nCategory: ${expert.category}")
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun ImageSourcePickerDialog(
    title: String = "Select Image Source",
    onDismiss: () -> Unit,
    onSelectCamera: () -> Unit = {},
    onSelectGallery: () -> Unit = {},
    onPickCamera: () -> Unit = onSelectCamera,
    onPickGallery: () -> Unit = onSelectGallery
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { onPickCamera(); onDismiss() }) { Text("Camera") }
                Button(onClick = { onPickGallery(); onDismiss() }) { Text("Gallery") }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun OtpVerificationDialog(
    phone: String,
    purposeTitle: String = "Verify OTP",
    purposeSubtitle: String = "Enter OTP",
    onDismiss: () -> Unit,
    onSuccess: () -> Unit = {},
    onVerified: () -> Unit = onSuccess
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(purposeTitle) },
        text = { Text(purposeSubtitle) },
        confirmButton = {
            Button(onClick = { onVerified(); onDismiss() }) { Text("Verify") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun ExpertCard(
    expert: ExpertEntity,
    isAdmin: Boolean = false,
    onCall: () -> Unit,
    onWhatsApp: () -> Unit,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit,
    onToggleAvailability: (Boolean) -> Unit,
    onViewMap: () -> Unit = {},
    onViewWorkHistory: () -> Unit = {},
    onSendWelcome: () -> Unit = {},
    onLongPress: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = expert.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "📞 ${expert.phone} • ${expert.category}")
        }
    }
}
