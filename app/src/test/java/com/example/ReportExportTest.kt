package com.example

import com.example.data.model.CustomerJobEntity
import com.example.data.model.JobStatus
import com.example.ui.components.MonthOrderStat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReportExportTest {

    @Test
    fun testMonthlyOrderStatCalculations() {
        val jobs = listOf(
            CustomerJobEntity(
                id = 1,
                customerName = "Rakesh Kumar",
                customerPhone = "9876543210",
                serviceType = "AC Repair",
                issueDescription = "Cooling problem",
                address = "Sector 18",
                latitude = 28.57,
                longitude = 77.32,
                status = JobStatus.COMPLETED.name,
                ratingGiven = 5.0f
            ),
            CustomerJobEntity(
                id = 2,
                customerName = "Pooja Sharma",
                customerPhone = "9811223344",
                serviceType = "Plumbing",
                issueDescription = "Water leak",
                address = "Sector 22",
                latitude = 28.59,
                longitude = 77.34,
                status = JobStatus.CANCELLED.name
            ),
            CustomerJobEntity(
                id = 3,
                customerName = "Amit Verma",
                customerPhone = "9899001122",
                serviceType = "Fan Fitting",
                issueDescription = "Switch spark",
                address = "Sector 50",
                latitude = 28.58,
                longitude = 77.36,
                status = JobStatus.PROCESSING.name
            )
        )

        val total = jobs.size
        val completed = jobs.count { it.status == JobStatus.COMPLETED.name }
        val cancelled = jobs.count { it.status == JobStatus.CANCELLED.name }
        val processing = jobs.count { it.status == JobStatus.PROCESSING.name }

        assertEquals(3, total)
        assertEquals(1, completed)
        assertEquals(1, cancelled)
        assertEquals(1, processing)

        val successRate = (completed * 100) / total
        assertEquals(33, successRate)

        val stat = MonthOrderStat(
            monthYearKey = "2026-10",
            displayMonth = "October 2026",
            totalOrders = total,
            completedOrders = completed,
            cancelledOrders = cancelled,
            processingOrders = processing,
            pendingOrders = 0
        )

        assertEquals("October 2026", stat.displayMonth)
        assertEquals(1, stat.completedOrders)
    }

    @Test
    fun testCsvEscapingLogic() {
        val rawWithComma = "Flat 101, Royal Towers, Sector 18"
        val escaped = if (rawWithComma.contains(",")) "\"$rawWithComma\"" else rawWithComma
        assertTrue(escaped.startsWith("\""))
        assertTrue(escaped.endsWith("\""))
    }
}
