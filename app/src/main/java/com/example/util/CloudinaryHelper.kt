package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

object CloudinaryHelper {
    private const val CLOUD_NAME = "qgfxr96m"
    private const val API_KEY = "397241468624234"
    private const val API_SECRET = "tiC43p-Ai0HWnWxnymBNVAtBe8Q"
    private const val UPLOAD_URL = "https://api.cloudinary.com/v1_1/$CLOUD_NAME/image/upload"

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .build()
    }

    fun compressUriToWebp(context: Context, uri: Uri, maxDimension: Int = 500): ByteArray? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            val scaled = if (bitmap.width > maxDimension || bitmap.height > maxDimension) {
                val ratio = bitmap.width.toFloat() / bitmap.height.toFloat()
                val targetW = if (ratio >= 1) maxDimension else (maxDimension * ratio).toInt()
                val targetH = if (ratio >= 1) (maxDimension / ratio).toInt() else maxDimension
                Bitmap.createScaledBitmap(bitmap, targetW, targetH, true)
            } else bitmap

            val out = ByteArrayOutputStream()
            scaled.compress(Bitmap.CompressFormat.JPEG, 80, out)
            out.toByteArray()
        } catch (_: Exception) {
            null
        }
    }

    suspend fun uploadImage(webpBytes: ByteArray): Result<String> = withContext(Dispatchers.IO) {
        try {
            val timestamp = (System.currentTimeMillis() / 1000).toString()
            val signature = sha1Hex("timestamp=$timestamp$API_SECRET")

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("api_key", API_KEY)
                .addFormDataPart("timestamp", timestamp)
                .addFormDataPart("signature", signature)
                .addFormDataPart(
                    "file",
                    "image.jpg",
                    webpBytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
                )
                .build()

            val request = Request.Builder()
                .url(UPLOAD_URL)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""
            if (response.isSuccessful) {
                val json = JSONObject(body)
                val secureUrl = json.getString("secure_url")
                Result.success(secureUrl)
            } else {
                Result.failure(Exception("Cloudinary error: $body"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun compressAndUpload(context: Context, uri: Uri): Result<String> {
        val bytes = compressUriToWebp(context, uri) ?: return Result.failure(Exception("Failed to read image"))
        return uploadImage(bytes)
    }

    private fun sha1Hex(input: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        val digest = md.digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}
