package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.JobStatus
import com.example.ui.theme.AmberWarning
import com.example.ui.theme.WhatsAppDarkGreen
import com.example.util.WhatsAppHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExpertWorkHistoryDialog(
    expert: ExpertEntity,
    allJobs: List<CustomerJobEntity>,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatusFilter by remember { mutableStateOf("ALL") }

    // Jobs handled by this expert
    val expertJobs = remember(allJobs, expert.id) {
        allJobs.filter { it.assignedExpertId == expert.id && !it.isDeleted }
    }

    val totalCompleted = remember(expertJobs) {
        expertJobs.count { it.status == JobStatus.COMPLETED.name }
    }
    val totalActive = remember(expertJobs) {
        expertJobs.count { it.status == JobStatus.PROCESSING.name }
    }
    val totalUniqueCustomers = remember(expertJobs) {
        expertJobs.map { it.customerPhone }.distinct().size
    }

    // Filtered list based on search and status
    val filteredJobs = remember(expertJobs, searchQuery, selectedStatusFilter) {
        expertJobs.filter { job ->
            val matchesStatus = when (selectedStatusFilter) {
                "COMPLETED" -> job.status == JobStatus.COMPLETED.name
                "PROCESSING" -> job.status == JobStatus.PROCESSING.name
                "CANCELLED" -> job.status == JobStatus.CANCELLED.name
                else -> true
            }
            val matchesQuery = searchQuery.isBlank() ||
                    job.customerName.contains(searchQuery, ignoreCase = true) ||
                    job.customerPhone.contains(searchQuery) ||
                    job.serviceType.contains(searchQuery, ignoreCase = true) ||
                    job.address.contains(searchQuery, ignoreCase = true) ||
                    job.issueDescription.contains(searchQuery, ignoreCase = true)

            matchesStatus && matchesQuery
        }
    }

    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.92f),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header with Colorful Gradient
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF1E3A8A), Color(0xFF3B82F6), Color(0xFF0284C7))
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.History,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "${expert.name} • Work History",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "${expert.category} • 📱 ${expert.phone}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.85f)
                                )
                            }
                        }

                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                        }
                    }
                }

                // Summary Stats Cards (Colorful Metrics)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatCard(
                        title = "Unique Customers",
                        value = "$totalUniqueCustomers",
                        subtitle = "Total served",
                        containerColor = Color(0xFFEFF6FF),
                        contentColor = Color(0xFF1D4ED8),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Completed",
                        value = "$totalCompleted",
                        subtitle = "Done jobs",
                        containerColor = Color(0xFFECFDF5),
                        contentColor = Color(0xFF047857),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Active",
                        value = "$totalActive",
                        subtitle = "In progress",
                        containerColor = Color(0xFFFEF3C7),
                        contentColor = Color(0xFFB45309),
                        modifier = Modifier.weight(1f)
                    )
                }

                // Search Bar and Filter Chips
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search by customer, phone, address...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        trailingIcon = if (searchQuery.isNotEmpty()) {
                            {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                                }
                            }
                        } else null,
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(
                            "ALL" to "All (${expertJobs.size})",
                            "COMPLETED" to "Completed ($totalCompleted)",
                            "PROCESSING" to "Active ($totalActive)",
                            "CANCELLED" to "Cancelled"
                        ).forEach { (statusKey, label) ->
                            val isSelected = selectedStatusFilter == statusKey
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedStatusFilter = statusKey },
                                label = { Text(label, fontSize = 11.5.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // List of Customers & Jobs
                if (filteredJobs.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("🔍", fontSize = 36.sp)
                            Text(
                                text = if (expertJobs.isEmpty()) {
                                    "No job assignments recorded yet for ${expert.name}."
                                } else {
                                    "No customer jobs match your search/filter criteria."
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    val coroutineScope = rememberCoroutineScope()
                    val listState = rememberLazyListState()
                    val showScrollToTop by remember {
                        derivedStateOf {
                            listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 20
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(filteredJobs, key = { it.id }) { job ->
                                WorkHistoryJobCard(
                                    job = job,
                                    dateFormat = dateFormat,
                                    onCallCustomer = {
                                        WhatsAppHelper.openDialer(context, job.customerPhone)
                                    },
                                    onWhatsAppCustomer = {
                                        WhatsAppHelper.sendWhatsAppDirectMessage(
                                            context = context,
                                            phoneNumber = job.customerPhone,
                                            message = "Hello ${job.customerName}, this is regarding your ${job.serviceType} service with Hurifix."
                                        )
                                    }
                                )
                            }
                        }

                        ScrollToTopButton(
                            visible = showScrollToTop,
                            onClick = {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(0)
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    subtitle: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = containerColor,
        border = BorderStroke(1.dp, contentColor.copy(alpha = 0.25f))
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = contentColor)
            Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = contentColor)
            Text(text = subtitle, fontSize = 9.5.sp, color = contentColor.copy(alpha = 0.75f))
        }
    }
}

@Composable
private fun WorkHistoryJobCard(
    job: CustomerJobEntity,
    dateFormat: SimpleDateFormat,
    onCallCustomer: () -> Unit,
    onWhatsAppCustomer: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.2.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column {
                        Text(
                            text = job.customerName,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "📱 ${job.customerPhone}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Status Badge
                val (statusBg, statusFg, statusText) = when (job.status) {
                    JobStatus.COMPLETED.name -> Triple(Color(0xFFDCFCE7), Color(0xFF15803D), "✅ Completed")
                    JobStatus.PROCESSING.name -> Triple(Color(0xFFDBEAFE), Color(0xFF1D4ED8), "🔄 In Progress")
                    JobStatus.CANCELLED.name -> Triple(Color(0xFFFEE2E2), Color(0xFFB91C1C), "❌ Cancelled")
                    else -> Triple(Color(0xFFFEF3C7), Color(0xFFB45309), "⏳ Pending")
                }

                Surface(
                    color = statusBg,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(0.8.dp, statusFg.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = statusText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = statusFg,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Service and Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "🛠️ ${job.serviceType}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }

                Text(
                    text = dateFormat.format(Date(job.createdAt)),
                    fontSize = 10.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (job.issueDescription.isNotBlank()) {
                Text(
                    text = "📝 Problem: ${job.issueDescription}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (job.address.isNotBlank()) {
                Text(
                    text = "📍 Location: ${job.address}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }

            // Rating & Review if completed
            if (job.ratingGiven != null && job.ratingGiven > 0) {
                Surface(
                    color = Color(0xFFFFFBEB),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color(0xFFFDE68A)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = AmberWarning, modifier = Modifier.size(14.dp))
                            Text(
                                text = "Customer Rating: ${job.ratingGiven}/5",
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = Color(0xFF92400E)
                            )
                        }
                        job.reviewFeedback?.let { feedback ->
                            if (feedback.isNotBlank()) {
                                Text(
                                    text = "💬 \"$feedback\"",
                                    fontSize = 11.sp,
                                    color = Color(0xFF78350F),
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Action Buttons to contact customer directly
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onCallCustomer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Call", fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = onWhatsAppCustomer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(15.dp), tint = WhatsAppDarkGreen)
                    Spacer(Modifier.width(4.dp))
                    Text("WhatsApp", fontSize = 12.sp, color = WhatsAppDarkGreen)
                }
            }
        }
    }
}
