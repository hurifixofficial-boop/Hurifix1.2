package com.example.util

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.data.model.CustomerJobEntity
import com.example.ui.components.MonthOrderStat
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ReportExportHelper {

    /**
     * Exports monthly orders data to a clean CSV file and opens the Android share sheet.
     */
    fun exportMonthlyReportToCsv(
        context: Context,
        monthStat: MonthOrderStat,
        jobsInMonth: List<CustomerJobEntity>
    ) {
        try {
            val reportsDir = File(context.cacheDir, "reports").apply { mkdirs() }
            val cleanMonth = monthStat.displayMonth.replace(" ", "_")
            val csvFile = File(reportsDir, "Hurifix_Report_${cleanMonth}.csv")

            val dateTimeFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            val generatedAt = dateTimeFormat.format(Date())
            val successRate = if (monthStat.totalOrders > 0) (monthStat.completedOrders * 100) / monthStat.totalOrders else 0

            FileWriter(csvFile).use { writer ->
                // Title and Metadata
                writer.append("HURIFIX PERFORMANCE & OPERATIONS REPORT\n")
                writer.append("Tagline: Many Problems | One Solution\n")
                writer.append("Report Period: ${monthStat.displayMonth}\n")
                writer.append("Generated On: $generatedAt\n")
                writer.append("Total Orders: ${monthStat.totalOrders}\n")
                writer.append("Completed Orders: ${monthStat.completedOrders}\n")
                writer.append("Processing Orders: ${monthStat.processingOrders}\n")
                writer.append("Cancelled Orders: ${monthStat.cancelledOrders}\n")
                writer.append("Success Rate: $successRate%\n\n")

                // CSV Headers
                writer.append("Order ID,Created Date,Customer Name,Customer Phone,Service Required,Problem Description,Address,Assigned Expert,Status,Rating Given,Feedback\n")

                // Order Rows
                jobsInMonth.forEach { job ->
                    val dateStr = dateTimeFormat.format(Date(job.createdAt))
                    val orderId = "#${job.id}"
                    val name = escapeCsv(job.customerName)
                    val phone = escapeCsv(job.customerPhone)
                    val service = escapeCsv(job.serviceType)
                    val problem = escapeCsv(job.issueDescription)
                    val address = escapeCsv(job.address)
                    val expert = escapeCsv(job.assignedExpertName ?: "Not Assigned")
                    val status = escapeCsv(job.status)
                    val rating = job.ratingGiven?.let { String.format("%.1f", it) } ?: "N/A"
                    val feedback = escapeCsv(job.reviewFeedback ?: "")

                    writer.append("$orderId,$dateStr,$name,$phone,$service,$problem,$address,$expert,$status,$rating,$feedback\n")
                }
            }

            shareFile(
                context = context,
                file = csvFile,
                mimeType = "text/csv",
                subject = "Hurifix Monthly Report - ${monthStat.displayMonth}"
            )
        } catch (e: Exception) {
            Toast.makeText(context, "CSV export me error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Generates a beautifully formatted PDF report using Android's native PdfDocument.
     */
    fun exportMonthlyReportToPdf(
        context: Context,
        monthStat: MonthOrderStat,
        jobsInMonth: List<CustomerJobEntity>
    ) {
        try {
            val pdfDocument = PdfDocument()
            val pageWidth = 595 // Standard A4 width in points
            val pageHeight = 842 // Standard A4 height in points
            val dateTimeFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            val generatedAt = dateTimeFormat.format(Date())
            val successRate = if (monthStat.totalOrders > 0) (monthStat.completedOrders * 100) / monthStat.totalOrders else 0

            val itemsPerPage = 12
            val totalPages = maxOf(1, (jobsInMonth.size + itemsPerPage - 1) / itemsPerPage)

            for (pageIndex in 0 until totalPages) {
                val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageIndex + 1).create()
                val page = pdfDocument.startPage(pageInfo)
                val canvas = page.canvas

                val paint = Paint(Paint.ANTI_ALIAS_FLAG)

                // Page 1 Header
                if (pageIndex == 0) {
                    // Top Banner Background
                    paint.color = Color.parseColor("#1E293B") // Slate dark navy
                    canvas.drawRect(0f, 0f, pageWidth.toFloat(), 105f, paint)

                    // Accent orange stripe
                    paint.color = Color.parseColor("#D97706") // Hurifix Amber
                    canvas.drawRect(0f, 105f, pageWidth.toFloat(), 110f, paint)

                    // Company Name
                    paint.color = Color.WHITE
                    paint.textSize = 24f
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    canvas.drawText("HURIFIX", 28f, 42f, paint)

                    // Tagline
                    paint.textSize = 10f
                    paint.color = Color.parseColor("#F59E0B")
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    canvas.drawText("MANY PROBLEMS  |  ONE SOLUTION", 28f, 58f, paint)

                    // Report Subtitle
                    paint.textSize = 13f
                    paint.color = Color.WHITE
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    canvas.drawText("Monthly Operations & Performance Report", 28f, 82f, paint)

                    // Right side Date info
                    paint.textSize = 9f
                    paint.color = Color.parseColor("#CBD5E1")
                    canvas.drawText("Period: ${monthStat.displayMonth}", pageWidth - 190f, 42f, paint)
                    canvas.drawText("Generated: $generatedAt", pageWidth - 190f, 58f, paint)
                    canvas.drawText("Page ${pageIndex + 1} of $totalPages", pageWidth - 190f, 74f, paint)

                    // Summary KPI Cards Box
                    var cardY = 125f
                    val cardHeight = 65f
                    paint.color = Color.parseColor("#F8FAFC")
                    val kpiRect = RectF(24f, cardY, pageWidth - 24f, cardY + cardHeight)
                    canvas.drawRoundRect(kpiRect, 8f, 8f, paint)

                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = 1f
                    paint.color = Color.parseColor("#E2E8F0")
                    canvas.drawRoundRect(kpiRect, 8f, 8f, paint)
                    paint.style = Paint.Style.FILL

                    // KPI Columns
                    val colWidth = (pageWidth - 48f) / 5f
                    val kpiLabels = listOf("TOTAL", "COMPLETED", "PROCESSING", "CANCELLED", "SUCCESS")
                    val kpiValues = listOf(
                        "${monthStat.totalOrders}",
                        "${monthStat.completedOrders}",
                        "${monthStat.processingOrders}",
                        "${monthStat.cancelledOrders}",
                        "$successRate%"
                    )
                    val kpiColors = listOf(
                        Color.parseColor("#1E293B"),
                        Color.parseColor("#16A34A"),
                        Color.parseColor("#D97706"),
                        Color.parseColor("#DC2626"),
                        Color.parseColor("#2563EB")
                    )

                    for (i in 0 until 5) {
                        val colX = 24f + (i * colWidth) + (colWidth / 2f)

                        paint.textSize = 9f
                        paint.color = Color.parseColor("#64748B")
                        paint.textAlign = Paint.Align.CENTER
                        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        canvas.drawText(kpiLabels[i], colX, cardY + 24f, paint)

                        paint.textSize = 18f
                        paint.color = kpiColors[i]
                        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        canvas.drawText(kpiValues[i], colX, cardY + 50f, paint)
                    }

                    paint.textAlign = Paint.Align.LEFT
                } else {
                    // Subsequent page mini header
                    paint.color = Color.parseColor("#1E293B")
                    canvas.drawRect(0f, 0f, pageWidth.toFloat(), 45f, paint)

                    paint.color = Color.WHITE
                    paint.textSize = 14f
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    canvas.drawText("Hurifix Operations Report - ${monthStat.displayMonth}", 24f, 28f, paint)

                    paint.textSize = 9f
                    paint.color = Color.parseColor("#CBD5E1")
                    canvas.drawText("Page ${pageIndex + 1} of $totalPages", pageWidth - 110f, 28f, paint)
                }

                // Table Starts
                var tableTop = if (pageIndex == 0) 205f else 60f

                // Table Header Row
                paint.color = Color.parseColor("#0F172A")
                canvas.drawRect(24f, tableTop, pageWidth - 24f, tableTop + 24f, paint)

                paint.color = Color.WHITE
                paint.textSize = 9f
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                canvas.drawText("ID", 28f, tableTop + 16f, paint)
                canvas.drawText("CUSTOMER", 60f, tableTop + 16f, paint)
                canvas.drawText("MOBILE", 160f, tableTop + 16f, paint)
                canvas.drawText("SERVICE", 235f, tableTop + 16f, paint)
                canvas.drawText("EXPERT ASSIGNED", 345f, tableTop + 16f, paint)
                canvas.drawText("STATUS", 465f, tableTop + 16f, paint)
                canvas.drawText("RATING", 535f, tableTop + 16f, paint)

                // Table Data Rows
                val startIndex = pageIndex * itemsPerPage
                val endIndex = minOf(startIndex + itemsPerPage, jobsInMonth.size)
                var rowY = tableTop + 24f

                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)

                for (idx in startIndex until endIndex) {
                    val job = jobsInMonth[idx]
                    val isEven = (idx % 2 == 0)

                    // Alternate row background
                    paint.color = if (isEven) Color.parseColor("#F8FAFC") else Color.WHITE
                    canvas.drawRect(24f, rowY, pageWidth - 24f, rowY + 38f, paint)

                    // Border line bottom
                    paint.color = Color.parseColor("#E2E8F0")
                    paint.strokeWidth = 0.5f
                    canvas.drawLine(24f, rowY + 38f, pageWidth - 24f, rowY + 38f, paint)

                    // Text values
                    paint.color = Color.parseColor("#1E293B")
                    paint.textSize = 9f
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    canvas.drawText("#${job.id}", 28f, rowY + 18f, paint)

                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    canvas.drawText(job.customerName.take(18), 60f, rowY + 18f, paint)
                    paint.color = Color.parseColor("#64748B")
                    paint.textSize = 8f
                    canvas.drawText(job.address.take(20), 60f, rowY + 30f, paint)

                    paint.color = Color.parseColor("#1E293B")
                    paint.textSize = 9f
                    canvas.drawText(job.customerPhone, 160f, rowY + 18f, paint)
                    canvas.drawText(job.serviceType.take(18), 235f, rowY + 18f, paint)
                    canvas.drawText((job.assignedExpertName ?: "Unassigned").take(18), 345f, rowY + 18f, paint)

                    // Status Pill
                    val statusBgColor = when (job.status) {
                        "COMPLETED" -> Color.parseColor("#DCFCE7")
                        "CANCELLED" -> Color.parseColor("#FEE2E2")
                        "PROCESSING" -> Color.parseColor("#DBEAFE")
                        else -> Color.parseColor("#FEF3C7")
                    }
                    val statusTextColor = when (job.status) {
                        "COMPLETED" -> Color.parseColor("#15803D")
                        "CANCELLED" -> Color.parseColor("#B91C1C")
                        "PROCESSING" -> Color.parseColor("#1D4ED8")
                        else -> Color.parseColor("#B45309")
                    }

                    val statusRect = RectF(465f, rowY + 6f, 525f, rowY + 24f)
                    paint.color = statusBgColor
                    canvas.drawRoundRect(statusRect, 4f, 4f, paint)

                    paint.color = statusTextColor
                    paint.textSize = 7.5f
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    canvas.drawText(job.status.take(10), 470f, rowY + 18f, paint)

                    // Rating
                    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    paint.color = Color.parseColor("#D97706")
                    paint.textSize = 9f
                    val ratingStr = job.ratingGiven?.let { "★ ${it.toInt()}" } ?: "-"
                    canvas.drawText(ratingStr, 540f, rowY + 18f, paint)

                    rowY += 38f
                }

                // Footer
                paint.color = Color.parseColor("#94A3B8")
                paint.textSize = 8f
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                canvas.drawText("Hurifix Operations & Dispatch Management System • Strictly Confidential", 24f, pageHeight - 20f, paint)
                paint.textAlign = Paint.Align.RIGHT
                canvas.drawText("Page ${pageIndex + 1} of $totalPages", pageWidth - 24f, pageHeight - 20f, paint)
                paint.textAlign = Paint.Align.LEFT

                pdfDocument.finishPage(page)
            }

            // Save PDF to cache
            val reportsDir = File(context.cacheDir, "reports").apply { mkdirs() }
            val cleanMonth = monthStat.displayMonth.replace(" ", "_")
            val pdfFile = File(reportsDir, "Hurifix_Report_${cleanMonth}.pdf")

            FileOutputStream(pdfFile).use { out ->
                pdfDocument.writeTo(out)
            }
            pdfDocument.close()

            shareFile(
                context = context,
                file = pdfFile,
                mimeType = "application/pdf",
                subject = "Hurifix Monthly Report - ${monthStat.displayMonth} (PDF)"
            )
        } catch (e: Exception) {
            Toast.makeText(context, "PDF generate karne me error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }

    private fun shareFile(context: Context, file: File, mimeType: String, subject: String) {
        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = mimeType
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, "Hurifix Monthly Performance Report is attached.")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(intent, "Export / Share Report").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(chooser)
    }

    private fun escapeCsv(text: String): String {
        return if (text.contains(",") || text.contains("\"") || text.contains("\n")) {
            "\"" + text.replace("\"", "\"\"") + "\""
        } else {
            text
        }
    }
}
