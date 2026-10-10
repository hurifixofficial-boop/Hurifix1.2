package com.example.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Log
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

/**
 * Cloudinary image uploader and WebP auto-compression utility.
 * Credentials:
 * - Cloud Name: qgfxr96m
 * - API Key: 397241468624234
 * - API Secret: tiC43p-Ai0HWnWxnymBNVAtBe8Q
 */
object CloudinaryHelper {
    private const val TAG = "CloudinaryHelper"
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

    /**
     * Compresses an image picked from [uri] into WebP format with a max dimension of 500px.
     */
    fun compressUriToWebp(context: Context, uri: Uri, maxDimension: Int = 500): ByteArray? {
        return try {
            // 1. Decode original bitmap bounds
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            context.contentResolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, options)
            }

            var inSampleSize = 1
            if (options.outHeight > maxDimension || options.outWidth > maxDimension) {
                val halfHeight = options.outHeight / 2
                val halfWidth = options.outWidth / 2
                while ((halfHeight / inSampleSize) >= maxDimension && (halfWidth / inSampleSize) >= maxDimension) {
                    inSampleSize *= 2
                }
            }

            // 2. Decode actual bitmap with sample size
            val decodeOptions = BitmapFactory.Options().apply { this.inSampleSize = inSampleSize }
            val rawBitmap = context.contentResolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, decodeOptions)
            } ?: return null

            // 3. Inspect orientation from EXIF
            var rotationAngle = 0f
            try {
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    val exif = ExifInterface(stream)
                    val orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL
                    )
                    rotationAngle = when (orientation) {
                        ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                        ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                        ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                        else -> 0f
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "EXIF read error: ${e.message}")
            }

            val orientedBitmap = if (rotationAngle != 0f) {
                val matrix = Matrix().apply { postRotate(rotationAngle) }
                Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.width, rawBitmap.height, matrix, true)
            } else {
                rawBitmap
            }

            // 4. Scale to max width/height of 500px
            val width = orientedBitmap.width
            val height = orientedBitmap.height
            val scale = if (width > maxDimension || height > maxDimension) {
                maxDimension.toFloat() / maxOf(width, height)
            } else {
                1.0f
            }

            val targetWidth = (width * scale).toInt().coerceAtLeast(1)
            val targetHeight = (height * scale).toInt().coerceAtLeast(1)
            val scaledBitmap = if (scale < 1.0f) {
                Bitmap.createScaledBitmap(orientedBitmap, targetWidth, targetHeight, true)
            } else {
                orientedBitmap
            }

            // 5. Compress to WebP format
            val baos = ByteArrayOutputStream()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                scaledBitmap.compress(Bitmap.CompressFormat.WEBP_LOSSY, 80, baos)
            } else {
                @Suppress("DEPRECATION")
                scaledBitmap.compress(Bitmap.CompressFormat.WEBP, 80, baos)
            }
            baos.toByteArray()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to compress image to WebP: ${e.message}", e)
            null
        }
    }

    /**
     * Uploads the given WebP byte array to Cloudinary using signed authentication.
     * Uses fixed unique IDs (public_id) and overwrite mode so that old files are cleanly replaced
     * without consuming extra cloud storage.
     * Returns the HTTPS secure URL on success.
     */
    suspend fun uploadImage(
        webpBytes: ByteArray,
        publicId: String? = null,
        overwrite: Boolean = true
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            val timestamp = (System.currentTimeMillis() / 1000).toString()
            val cleanPublicId = publicId?.replace(Regex("[^a-zA-Z0-9_-]"), "_")?.ifBlank { null }
                ?: "profile_user"

            // Cloudinary requires signed parameters to be sorted alphabetically by key:
            // "invalidate" -> "overwrite" -> "public_id" -> "timestamp"
            val paramsToSign = sortedMapOf<String, String>()
            if (overwrite) {
                paramsToSign["invalidate"] = "true"
                paramsToSign["overwrite"] = "true"
            }
            paramsToSign["public_id"] = cleanPublicId
            paramsToSign["timestamp"] = timestamp

            val toSign = paramsToSign.entries.joinToString("&") { "${it.key}=${it.value}" } + API_SECRET
            val signature = sha1Hex(toSign)

            val bodyBuilder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    "$cleanPublicId.webp",
                    webpBytes.toRequestBody("image/webp".toMediaTypeOrNull())
                )
                .addFormDataPart("api_key", API_KEY)
                .addFormDataPart("timestamp", timestamp)
                .addFormDataPart("signature", signature)
                .addFormDataPart("public_id", cleanPublicId)

            if (overwrite) {
                bodyBuilder.addFormDataPart("overwrite", "true")
                bodyBuilder.addFormDataPart("invalidate", "true")
            }

            val request = Request.Builder()
                .url(UPLOAD_URL)
                .post(bodyBuilder.build())
                .build()

            val response = client.newCall(request).execute()
            response.use { resp ->
                val bodyString = resp.body?.string() ?: ""
                if (resp.isSuccessful) {
                    val json = JSONObject(bodyString)
                    val secureUrl = json.optString("secure_url").ifBlank {
                        json.optString("url")
                    }
                    if (secureUrl.isNotBlank()) {
                        Log.i(TAG, "Uploaded to Cloudinary (public_id=$cleanPublicId, overwrite=$overwrite): $secureUrl")
                        Result.success(secureUrl)
                    } else {
                        Result.failure(Exception("Cloudinary response missing secure_url"))
                    }
                } else {
                    Log.e(TAG, "Cloudinary upload error (${resp.code}): $bodyString")
                    Result.failure(Exception("Cloudinary upload failed"))
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during Cloudinary upload: ${e.message}", e)
            val friendlyMsg = NetworkErrorHandler.getFriendlyErrorMessage(e)
            Result.failure(Exception(friendlyMsg))
        }
    }

    /**
     * High-level helper: takes a picked Uri, auto-compresses it to WebP (max 500px),
     * uploads it to Cloudinary using fixed unique public_id and overwrite mode,
     * and returns the resulting secure URL.
     */
    suspend fun compressAndUpload(
        context: Context,
        uri: Uri,
        fixedPublicId: String? = null,
        overwrite: Boolean = true
    ): Result<String> = GlobalLoadingManager.withLoading("Loading...") {
        withContext(Dispatchers.IO) {
            val webpBytes = compressUriToWebp(context, uri, maxDimension = 500)
                ?: return@withContext Result.failure(Exception("Failed to decode and compress image to WebP"))

            val resolvedPublicId = if (!fixedPublicId.isNullOrBlank()) {
                fixedPublicId
            } else {
                val phone = SessionManager(context).getUserPhone().replace(Regex("[^0-9]"), "")
                if (phone.isNotBlank()) "profile_$phone" else "profile_default"
            }

            uploadImage(webpBytes, publicId = resolvedPublicId, overwrite = overwrite)
        }
    }

    private fun sha1Hex(input: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        val bytes = md.digest(input.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
