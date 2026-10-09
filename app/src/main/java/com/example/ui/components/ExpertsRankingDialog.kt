package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.ExpertEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpertsRankingDialog(
    experts: List<ExpertEntity>,
    onDismiss: () -> Unit
) {
    // Sort experts by highest rating first, then by completed jobs
    val rankedExperts = experts.sortedWith(
        compareByDescending<ExpertEntity> { it.rating }
            .thenByDescending { it.completedJobsCount }
    )

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
                            Text(
                                text = "🏆",
                                fontSize = 22.sp
                            )
                            Column {
                                Text(
                                    text = "Experts Leaderboard & Ranking",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Ranked by customer reviews & average rating",
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
            if (rankedExperts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No expert records found.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    itemsIndexed(rankedExperts) { index, expert ->
                        val rank = index + 1
                        val badgeColor = when (rank) {
                            1 -> Color(0xFFEAB308) // Gold
                            2 -> Color(0xFF94A3B8) // Silver
                            3 -> Color(0xFFD97706) // Bronze
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                        val badgeTextColor = when (rank) {
                            1, 2, 3 -> Color.White
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = if (rank == 1) Color(0xFFFEFCE8) else MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = if (rank <= 3) 2.dp else 0.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                // Rank Circle
                                Surface(
                                    shape = CircleShape,
                                    color = badgeColor,
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = "#$rank",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = badgeTextColor
                                        )
                                    }
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = expert.name,
                                        fontWeight = FontWeight.SemiBold,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Text(
                                        text = "${expert.category} • ${expert.address}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "${expert.completedJobsCount} Jobs Completed • ${expert.totalRatingsCount} Reviews",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                // Rating Badge
                                Surface(
                                    color = Color(0xFFFEF3C7),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                                    ) {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFF59E0B),
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Text(
                                            text = String.format("%.1f", expert.rating),
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 13.sp,
                                            color = Color(0xFFB45309)
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
