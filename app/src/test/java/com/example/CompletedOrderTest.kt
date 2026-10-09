package com.example

import com.example.ui.components.calculateTaskDuration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CompletedOrderTest {

    @Test
    fun testTaskDurationCalculationMinutes() {
        val start = 1000000L
        val end = start + (42 * 60 * 1000L) // 42 minutes later
        val duration = calculateTaskDuration(start, end)
        assertEquals("42 mins", duration)
    }

    @Test
    fun testTaskDurationCalculationHoursAndMinutes() {
        val start = 1000000L
        val end = start + ((1 * 60 * 60 * 1000L) + (35 * 60 * 1000L)) // 1 hr 35 mins
        val duration = calculateTaskDuration(start, end)
        assertEquals("1 hr 35 mins", duration)
    }

    @Test
    fun testTaskDurationFallback() {
        val start = 1000000L
        val duration = calculateTaskDuration(start, null)
        assertTrue(duration.isNotBlank())
    }
}
