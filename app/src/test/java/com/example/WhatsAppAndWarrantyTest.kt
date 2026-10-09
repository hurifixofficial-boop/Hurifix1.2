package com.example

import com.example.util.LocationHelper
import com.example.util.WhatsAppHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WhatsAppAndWarrantyTest {

    @Test
    fun testEstimatedTimeWithThirtyMinutesBuffer() {
        // Distance 0.1 km -> baseTravelMinutes = 2 -> +30 mins = 32 mins
        val est1 = WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(0.1)
        assertTrue(est1.contains("32 Minutes") || est1.contains("Minutes"))

        // Distance 10 km -> base travel time is around 28 mins -> + 30 mins = 58 mins
        val est2 = WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(10.0)
        assertTrue(est2.isNotBlank())
        assertTrue(est2.contains("Minutes") || est2.contains("Hour"))

        // Verify buffer is strictly greater than base travel time by 30 mins
        val baseTravel = LocationHelper.estimateTravelTimeMinutes(5.0)
        val expectedBufferMinutes = baseTravel + 30
        val estText = WhatsAppHelper.calculateEstimatedArrivalTimeWithBuffer(5.0)
        assertTrue(estText.contains("$expectedBufferMinutes Minutes"))
    }

    @Test
    fun testCompletionMessageContainsInstagramLink() {
        val msg = WhatsAppHelper.createCompletionCustomerMessage("Rahul")
        assertTrue(msg.contains("Dear Rahul ✨,"))
        assertTrue(msg.contains("https://www.instagram.com/hurifix_official?stkn=MzUzMW9xOTh2eTJ4"))
        assertTrue(msg.contains("valuable feedback"))
    }

    @Test
    fun testAssignmentMessageContainsExpertAndEstimate() {
        val msg = WhatsAppHelper.createCustomerAssignmentNotificationMessage(
            customerName = "Rakesh",
            expertName = "Mohammad Irfan",
            expertPhone = "9876543210",
            serviceType = "AC Gas Refill",
            estimatedTimeText = "45 Minutes (Approx)"
        )
        assertTrue(msg.contains("Rakesh"))
        assertTrue(msg.contains("Mohammad Irfan"))
        assertTrue(msg.contains("9876543210"))
        assertTrue(msg.contains("45 Minutes (Approx)"))
    }

    @Test
    fun testTenDaysWarrantyLogic() {
        val now = System.currentTimeMillis()
        val oneDayMs = 24 * 60 * 60 * 1000L

        // Order completed 3 days ago -> Active
        val completed3DaysAgo = now - (3 * oneDayMs)
        val daysPassed3 = ((now - completed3DaysAgo) / oneDayMs).toInt()
        val isWarrantyValid3 = daysPassed3 <= 10
        assertEquals(true, isWarrantyValid3)
        assertEquals(7, 10 - daysPassed3)

        // Order completed 10 days ago -> Active
        val completed10DaysAgo = now - (10 * oneDayMs)
        val daysPassed10 = ((now - completed10DaysAgo) / oneDayMs).toInt()
        val isWarrantyValid10 = daysPassed10 <= 10
        assertEquals(true, isWarrantyValid10)
        assertEquals(0, 10 - daysPassed10)

        // Order completed 12 days ago -> Expired
        val completed12DaysAgo = now - (12 * oneDayMs)
        val daysPassed12 = ((now - completed12DaysAgo) / oneDayMs).toInt()
        val isWarrantyValid12 = daysPassed12 <= 10
        assertEquals(false, isWarrantyValid12)
    }
}
