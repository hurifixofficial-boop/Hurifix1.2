package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.data.model.CustomerJobEntity
import com.example.data.model.JobStatus
import com.example.ui.components.JobStatusCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            val sampleJob = CustomerJobEntity(
                                id = "1",
                                serviceType = "Plumbing Repair",
                                status = JobStatus.PROCESSING.name,
                                customerName = "Rajesh Kumar",
                                customerPhone = "+919876543210",
                                issueDescription = "Leaking water tap in kitchen",
                                address = "124, MG Road, New Delhi",
                                assignedExpertName = "Amit Sharma",
                                assignedExpertPhone = "+919123456789",
                                created_by_user_name = "Sunil Verma",
                                created_by_designation = "Support Exec",
                                managed_by_user_name = "Primary Admin",
                                managed_by_designation = "Co-Founder & Admin"
                            )
                            JobStatusCard(
                                job = sampleJob,
                                onStatusChange = {},
                                onCallCustomer = {},
                                onCallExpert = {},
                                onReDispatch = {},
                                onDelete = {},
                                onViewCustomerMap = {}
                            )
                        }
                    }
                }
            }
        }
    }
}
