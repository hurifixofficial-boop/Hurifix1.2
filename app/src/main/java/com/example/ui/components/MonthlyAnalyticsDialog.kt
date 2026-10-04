package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CustomerJobEntity
import com.example.data.model.JobStatus
import com.example.util.ReportExportHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MonthOrderStat(
    val monthYearKey: String,
    val displayMonth: String,
    val totalOrders: Int,
    val completedOrders: Int,
    val cancelledOrders: Int,
    val processingOrders: Int,
    val pendingOrders: Int
)

@Composable
fun MonthlyAnalyticsDialog(
    jobs: List<CustomerJobEntity>,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val monthFormat = remember { SimpleDateFormat("MMMM yyyy", Locale.getDefault()) }
    val keyFormat = remember { SimpleDateFormat("yyyy-MM", Locale.getDefault()) }
    val yearFormat = remember { SimpleDateFormat("yyyy", Locale.getDefault()) }

    var searchQuery by remember { mutableStateOf("") }
    var selectedYearFilter by remember { mutableStateOf("All") }
    var expandedMonthKey by remember { mutableStateOf<String?>(null) }
    var orderStatusFilterInMonth by remember { mutableStateOf("ALL") }

    // Derive available years from existing jobs
    val availableYears by remember(jobs) {
        derivedStateOf {
            val years = jobs.map { yearFormat.format(Date(it.createdAt)) }.distinct().sortedDescending()
            listOf("All") + years
        }
    }

    // Group jobs by Month
    val groupedStats by remember(jobs, selectedYearFilter, searchQuery) {
        derivedStateOf {
            val filteredJobs = if (selectedYearFilter == "All") {
                jobs
            } else {
                jobs.filter { yearFormat.format(Date(it.createdAt)) == selectedYearFilter }
            }

            filteredJobs.groupBy { job ->
                keyFormat.format(Date(job.createdAt))
            }.map { (key, jobList) ->
                val sampleDate = Date(jobList.first().createdAt)
                val display = monthFormat.format(sampleDate)
                val total = jobList.size
                val completed = jobList.count { it.status == JobStatus.COMPLETED.name }
                val cancelled = jobList.count { it.status == JobStatus.CANCELLED.name }
                val processing = jobList.count { it.status == JobStatus.PROCESSING.name }
                val pending = jobList.count { it.status == JobStatus.PENDING.name }
                MonthOrderStat(
                    monthYearKey = key,
                    displayMonth = display,
                    totalOrders = total,
                    completedOrders = completed,
                    cancelledOrders = cancelled,
                    processingOrders = processing,
                    pendingOrders = pending
                )
            }.filter { stat ->
                if (searchQuery.isBlank()) true
                else {
                    stat.displayMonth.contains(searchQuery, ignoreCase = true) ||
                            stat.monthYearKey.contains(searchQuery, ignoreCase = true)
                }
            }.sortedByDescending { it.monthYearKey }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("📊", fontSize = 22.sp)
                Column {
                    Text(
                        text = "Monthly Performance & Past Records",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Search historical data & export reports",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Search Bar for Month / Year
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search by month or year (e.g. October, 2024)...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = if (searchQuery.isNotBlank()) {
                        { IconButton(onClick = { searchQuery = "" }) { Icon(Icons.Default.Clear, contentDescription = null) } }
                    } else null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Year Filter Chips
                if (availableYears.size > 2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        availableYears.forEach { yr ->
                            FilterChip(
                                selected = selectedYearFilter == yr,
                                onClick = { selectedYearFilter = yr },
                                label = { Text(if (yr == "All") "All Years" else yr) }
                            )
                        }
                    }
                }

                if (groupedStats.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (searchQuery.isNotBlank()) "No records found matching '$searchQuery'." else "No past order records available.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(groupedStats, key = { it.monthYearKey }) { stat ->
                            val successRate = if (stat.totalOrders > 0) {
                                (stat.completedOrders * 100) / stat.totalOrders
                            } else 0

                            val isExpanded = expandedMonthKey == stat.monthYearKey
                            val jobsInThisMonth = jobs.filter {
                                keyFormat.format(Date(it.createdAt)) == stat.monthYearKey
                            }

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                expandedMonthKey = if (isExpanded) null else stat.monthYearKey
                                            },
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = stat.displayMonth,
                                                fontWeight = FontWeight.Bold,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                            Text(
                                                text = "${stat.totalOrders} total jobs",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Surface(
                                                color = if (successRate >= 70) Color(0xFFDCFCE7) else Color(0xFFFEF3C7),
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Text(
                                                    text = "$successRate% Success",
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 11.sp,
                                                    color = if (successRate >= 70) Color(0xFF15803D) else Color(0xFFB45309),
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                                )
                                            }
                                            Icon(
                                                if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                                contentDescription = null
                                            )
                                        }
                                    }

                                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        StatItem(label = "Total", value = "${stat.totalOrders}", color = MaterialTheme.colorScheme.primary)
                                        StatItem(label = "Completed", value = "${stat.completedOrders}", color = Color(0xFF16A34A))
                                        StatItem(label = "Processing", value = "${stat.processingOrders}", color = Color(0xFFD97706))
                                        StatItem(label = "Cancelled", value = "${stat.cancelledOrders}", color = MaterialTheme.colorScheme.error)
                                    }

                                    // Expandable List of Orders in This Month
                                    if (isExpanded) {
                                        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                                        // Mini Status Filter
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .horizontalScroll(rememberScrollState()),
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            listOf("ALL", "COMPLETED", "PROCESSING", "CANCELLED", "PENDING").forEach { statusTag ->
                                                FilterChip(
                                                    selected = orderStatusFilterInMonth == statusTag,
                                                    onClick = { orderStatusFilterInMonth = statusTag },
                                                    label = { Text(statusTag.lowercase().replaceFirstChar { it.uppercase() }, fontSize = 11.sp) }
                                                )
                                            }
                                        }

                                        val filteredJobsInMonth = jobsInThisMonth.filter {
                                            if (orderStatusFilterInMonth == "ALL") true
                                            else it.status == orderStatusFilterInMonth
                                        }

                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            if (filteredJobsInMonth.isEmpty()) {
                                                Text(
                                                    "No jobs matching this status.",
                                                    fontSize = 12.sp,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            } else {
                                                filteredJobsInMonth.take(15).forEach { job ->
                                                    Surface(
                                                        shape = RoundedCornerShape(8.dp),
                                                        color = MaterialTheme.colorScheme.surface,
                                                        tonalElevation = 1.dp,
                                                        modifier = Modifier.fillMaxWidth()
                                                    ) {
                                                        Row(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(8.dp),
                                                            horizontalArrangement = Arrangement.SpaceBetween,
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Column(modifier = Modifier.weight(1f)) {
                                                                Text(job.customerName, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                                                Text("${job.serviceType} • 📞 ${job.customerPhone}", fontSize = 10.5.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                                            }
                                                            Surface(
                                                                color = when (job.status) {
                                                                    "COMPLETED" -> Color(0xFFDCFCE7)
                                                                    "PROCESSING" -> Color(0xFFDBEAFE)
                                                                    "CANCELLED" -> Color(0xFFFEE2E2)
                                                                    else -> Color(0xFFFEF3C7)
                                                                },
                                                                shape = RoundedCornerShape(4.dp)
                                                            ) {
                                                                Text(
                                                                    text = job.status,
                                                                    fontSize = 10.sp,
                                                                    fontWeight = FontWeight.Bold,
                                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                                if (filteredJobsInMonth.size > 15) {
                                                    Text(
                                                        "+ ${filteredJobsInMonth.size - 15} more jobs in full export",
                                                        fontSize = 11.sp,
                                                        color = MaterialTheme.colorScheme.primary,
                                                        fontWeight = FontWeight.SemiBold
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                                    // Export buttons for this month
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Button(
                                            onClick = {
                                                ReportExportHelper.exportMonthlyReportToPdf(
                                                    context = context,
                                                    monthStat = stat,
                                                    jobsInMonth = jobsInThisMonth
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
                                            modifier = Modifier.weight(1f),
                                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 6.dp)
                                        ) {
                                            Icon(Icons.Default.Description, contentDescription = null, modifier = Modifier.size(15.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text("Export PDF", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        }

                                        OutlinedButton(
                                            onClick = {
                                                ReportExportHelper.exportMonthlyReportToCsv(
                                                    context = context,
                                                    monthStat = stat,
                                                    jobsInMonth = jobsInThisMonth
                                                )
                                            },
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.weight(1f),
                                            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 6.dp)
                                        ) {
                                            Icon(Icons.Default.TableChart, contentDescription = null, modifier = Modifier.size(15.dp))
                                            Spacer(Modifier.width(4.dp))
                                            Text("Export CSV", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Close")
            }
        }
    )
}

@Composable
private fun StatItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 17.sp,
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
