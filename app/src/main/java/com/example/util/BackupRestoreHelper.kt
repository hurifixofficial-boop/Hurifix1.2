package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object BackupRestoreHelper {

    data class BackupData(
        val jobs: List<CustomerJobEntity>,
        val experts: List<ExpertEntity>,
        val categories: List<ExpertCategoryEntity>
    )

    fun createBackupJson(
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ): String {
        val root = JSONObject()
        root.put("app", "Hurifix")
        root.put("version", 4)
        root.put("timestamp", System.currentTimeMillis())

        // Categories Array
        val categoriesArray = JSONArray()
        categories.forEach { cat ->
            val obj = JSONObject()
            obj.put("id", cat.id)
            obj.put("name", cat.name)
            obj.put("isDefault", cat.isDefault)
            obj.put("createdAt", cat.createdAt)
            categoriesArray.put(obj)
        }
        root.put("categories", categoriesArray)

        // Experts Array
        val expertsArray = JSONArray()
        experts.forEach { exp ->
            val obj = JSONObject()
            obj.put("id", exp.id)
            obj.put("name", exp.name)
            obj.put("phone", exp.phone)
            obj.put("category", exp.category)
            obj.put("address", exp.address)
            obj.put("latitude", exp.latitude)
            obj.put("longitude", exp.longitude)
            obj.put("isAvailable", exp.isAvailable)
            obj.put("rating", exp.rating.toDouble())
            obj.put("ratingSum", exp.ratingSum.toDouble())
            obj.put("totalRatingsCount", exp.totalRatingsCount)
            obj.put("completedJobsCount", exp.completedJobsCount)
            obj.put("cancelledJobsCount", exp.cancelledJobsCount)
            obj.put("createdAt", exp.createdAt)
            expertsArray.put(obj)
        }
        root.put("experts", expertsArray)

        // Jobs Array
        val jobsArray = JSONArray()
        jobs.forEach { job ->
            val obj = JSONObject()
            obj.put("id", job.id)
            obj.put("customerName", job.customerName)
            obj.put("customerPhone", job.customerPhone)
            obj.put("serviceType", job.serviceType)
            obj.put("issueDescription", job.issueDescription)
            obj.put("address", job.address)
            obj.put("latitude", job.latitude)
            obj.put("longitude", job.longitude)
            obj.put("status", job.status)
            obj.put("assignedExpertId", job.assignedExpertId ?: JSONObject.NULL)
            obj.put("assignedExpertName", job.assignedExpertName ?: JSONObject.NULL)
            obj.put("assignedExpertPhone", job.assignedExpertPhone ?: JSONObject.NULL)
            obj.put("distanceKmAtDispatch", job.distanceKmAtDispatch ?: JSONObject.NULL)
            obj.put("ratingGiven", job.ratingGiven?.toDouble() ?: JSONObject.NULL)
            obj.put("reviewFeedback", job.reviewFeedback ?: JSONObject.NULL)
            obj.put("createdAt", job.createdAt)
            obj.put("completedAt", job.completedAt ?: JSONObject.NULL)
            obj.put("isExpertNotified", job.isExpertNotified)
            obj.put("isCustomerNotifiedOnAssign", job.isCustomerNotifiedOnAssign)
            obj.put("isCustomerNotifiedOnCompletion", job.isCustomerNotifiedOnCompletion)
            obj.put("assignMessageLaterDismissedAt", job.assignMessageLaterDismissedAt ?: JSONObject.NULL)
            jobsArray.put(obj)
        }
        root.put("jobs", jobsArray)

        return root.toString(2)
    }

    fun exportAndShareBackup(
        context: Context,
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ) {
        val jsonString = createBackupJson(jobs, experts, categories)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault()).format(Date())
        val fileName = "Hurifix_DriveBackup_$timeStamp.json"

        val backupDir = File(context.cacheDir, "backups").apply { mkdirs() }
        val backupFile = File(backupDir, fileName)
        backupFile.writeText(jsonString)

        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            backupFile
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/json"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, "Hurifix Full Database Backup - $timeStamp")
            putExtra(Intent.EXTRA_TEXT, "Hurifix complete system backup file. You can upload this directly to Google Drive to save your data.")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(shareIntent, "Backup to Google Drive / Files").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(chooser)
    }

    fun parseBackupJson(inputStream: InputStream): BackupData {
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val root = JSONObject(jsonString)

        val categoriesList = mutableListOf<ExpertCategoryEntity>()
        if (root.has("categories")) {
            val arr = root.getJSONArray("categories")
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                categoriesList.add(
                    ExpertCategoryEntity(
                        id = obj.optLong("id", 0L),
                        name = obj.getString("name"),
                        isDefault = obj.optBoolean("isDefault", false),
                        createdAt = obj.optLong("createdAt", System.currentTimeMillis())
                    )
                )
            }
        }

        val expertsList = mutableListOf<ExpertEntity>()
        if (root.has("experts")) {
            val arr = root.getJSONArray("experts")
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                expertsList.add(
                    ExpertEntity(
                        id = obj.optLong("id", 0L),
                        name = obj.getString("name"),
                        phone = obj.getString("phone"),
                        category = obj.getString("category"),
                        address = obj.optString("address", ""),
                        latitude = obj.optDouble("latitude", 28.57),
                        longitude = obj.optDouble("longitude", 77.32),
                        isAvailable = obj.optBoolean("isAvailable", true),
                        rating = obj.optDouble("rating", 4.8).toFloat(),
                        ratingSum = obj.optDouble("ratingSum", 4.8).toFloat(),
                        totalRatingsCount = obj.optInt("totalRatingsCount", 1),
                        completedJobsCount = obj.optInt("completedJobsCount", 0),
                        cancelledJobsCount = obj.optInt("cancelledJobsCount", 0),
                        createdAt = obj.optLong("createdAt", System.currentTimeMillis())
                    )
                )
            }
        }

        val jobsList = mutableListOf<CustomerJobEntity>()
        if (root.has("jobs")) {
            val arr = root.getJSONArray("jobs")
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                jobsList.add(
                    CustomerJobEntity(
                        id = obj.optLong("id", 0L),
                        customerName = obj.getString("customerName"),
                        customerPhone = obj.getString("customerPhone"),
                        serviceType = obj.getString("serviceType"),
                        issueDescription = obj.optString("issueDescription", ""),
                        address = obj.optString("address", ""),
                        latitude = obj.optDouble("latitude", 28.57),
                        longitude = obj.optDouble("longitude", 77.32),
                        status = obj.optString("status", "PENDING"),
                        assignedExpertId = if (obj.isNull("assignedExpertId")) null else obj.optLong("assignedExpertId"),
                        assignedExpertName = if (obj.isNull("assignedExpertName")) null else obj.optString("assignedExpertName"),
                        assignedExpertPhone = if (obj.isNull("assignedExpertPhone")) null else obj.optString("assignedExpertPhone"),
                        distanceKmAtDispatch = if (obj.isNull("distanceKmAtDispatch")) null else obj.optDouble("distanceKmAtDispatch"),
                        ratingGiven = if (obj.isNull("ratingGiven")) null else obj.optDouble("ratingGiven").toFloat(),
                        reviewFeedback = if (obj.isNull("reviewFeedback")) null else obj.optString("reviewFeedback"),
                        createdAt = obj.optLong("createdAt", System.currentTimeMillis()),
                        completedAt = if (obj.isNull("completedAt")) null else obj.optLong("completedAt"),
                        isExpertNotified = obj.optBoolean("isExpertNotified", false),
                        isCustomerNotifiedOnAssign = obj.optBoolean("isCustomerNotifiedOnAssign", false),
                        isCustomerNotifiedOnCompletion = obj.optBoolean("isCustomerNotifiedOnCompletion", false),
                        assignMessageLaterDismissedAt = if (obj.isNull("assignMessageLaterDismissedAt")) null else obj.optLong("assignMessageLaterDismissedAt")
                    )
                )
            }
        }

        return BackupData(
            jobs = jobsList,
            experts = expertsList,
            categories = categoriesList
        )
    }
}
