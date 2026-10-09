package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.WhatsAppDarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppLeadParserDialog(
    onDismiss: () -> Unit,
    onParseText: (String) -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    var inputText by remember { mutableStateOf("") }

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
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = WhatsAppDarkGreen)
                            Column {
                                Text(
                                    text = "WhatsApp Message Parser",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Auto-extract Customer Details & Location",
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
            },
            bottomBar = {
                Surface(
                    tonalElevation = 4.dp,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
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
                                if (inputText.isNotBlank()) {
                                    onParseText(inputText)
                                    onDismiss()
                                }
                            },
                            enabled = inputText.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppDarkGreen),
                            modifier = Modifier
                                .weight(1.2f)
                                .testTag("parse_lead_button")
                        ) {
                            Text("Extract & Auto-Fill", fontWeight = FontWeight.Bold)
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Paste the customer WhatsApp inquiry message below. Our smart lead parser will automatically parse and prefill the Customer Name, Phone Number, Service Problem, and Google Maps Location Coordinates!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedButton(
                    onClick = {
                        val clip = clipboardManager.getText()?.text
                        if (!clip.isNullOrBlank()) {
                            inputText = clip
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.ContentPaste, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Paste from Clipboard", fontWeight = FontWeight.SemiBold)
                }

                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("Customer WhatsApp Message Text") },
                    placeholder = {
                        Text("Example:\nName: Ajay Kumar\nMobile: 9876543210\nAC cooling problem\nNear City Hospital, Sector 62\nhttps://maps.google.com/?q=28.62,77.36")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .testTag("whatsapp_paste_input"),
                    minLines = 8,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}
