package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CustomerJobEntity

@Composable
fun ReviewDialog(
    job: CustomerJobEntity,
    isCompletedAction: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (rating: Float, feedback: String?) -> Unit
) {
    var rating by remember { mutableFloatStateOf(if (isCompletedAction) 5f else 3f) }
    var feedback by remember { mutableStateOf("") }

    val actionTitle = if (isCompletedAction) "Mark Order as Completed" else "Cancel Order"
    val actionColor = if (isCompletedAction) Color(0xFF16A34A) else MaterialTheme.colorScheme.error

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(
                    text = actionTitle,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = actionColor
                )
                Text(
                    text = "Customer: ${job.customerName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (job.assignedExpertName != null) {
                    Text(
                        text = "Submit rating and review for Expert '${job.assignedExpertName}':",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                } else {
                    Text(
                        text = "Enter feedback for this order:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Interactive 5-Star Rating
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        val isFilled = i <= rating
                        Icon(
                            imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = "Star $i",
                            tint = if (isFilled) Color(0xFFF59E0B) else Color.LightGray,
                            modifier = Modifier
                                .size(38.dp)
                                .clickable { rating = i.toFloat() }
                                .padding(2.dp)
                        )
                    }
                }

                Text(
                    text = "${rating.toInt()} / 5 Stars",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF59E0B),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                OutlinedTextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    label = { Text(if (isCompletedAction) "Customer Feedback / Notes (Optional)" else "Reason for Cancellation") },
                    placeholder = { Text(if (isCompletedAction) "e.g. Excellent service, punctual" else "e.g. Customer not available") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 4,
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(rating, feedback.ifBlank { null }) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = actionColor
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(if (isCompletedAction) "Confirm Complete" else "Confirm Cancel")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cancel")
            }
        }
    )
}
