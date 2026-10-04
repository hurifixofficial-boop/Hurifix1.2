package com.example.data.remote

import android.content.Context
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.HurifixUser
import com.example.data.model.TechnicianEntity
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.PersistentCacheSettings
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreService(private val context: Context) {

    private val firestore: FirebaseFirestore by lazy {
        initFirebaseIfNeeded(context)
        val db = FirebaseFirestore.getInstance()
        try {
            val settings = FirebaseFirestoreSettings.Builder()
                .setLocalCacheSettings(
                    PersistentCacheSettings.newBuilder().build()
                )
                .build()
            db.firestoreSettings = settings
        } catch (_: Exception) {
            // Settings can only be set before calling any other Firestore method
        }
        db
    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val JOBS_COLLECTION = "customer_jobs"
        private const val EXPERTS_COLLECTION = "experts"
        private const val CATEGORIES_COLLECTION = "expert_categories"
        private const val TECHNICIANS_COLLECTION = "technicians"

        @Volatile
        private var isInitialized = false

        fun initFirebaseIfNeeded(context: Context) {
            if (isInitialized) return
            synchronized(this) {
                if (isInitialized) return
                try {
                    if (FirebaseApp.getApps(context).isEmpty()) {
                        val options = FirebaseOptions.Builder()
                            .setApplicationId("com.aistudio.sevamitra.dispatch")
                            .setApiKey("AIzaSyDummyKeyForSparkTierHurifixClient")
                            .setProjectId("hurifix-dispatch")
                            .setStorageBucket("hurifix-dispatch.appspot.com")
                            .build()
                        FirebaseApp.initializeApp(context.applicationContext, options)
                    }
                    isInitialized = true
                } catch (e: Exception) {
                    // Firebase already initialized via plugin or manifest
                    isInitialized = true
                }
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Authentication & RBAC
    // ---------------------------------------------------------------------------------------------

    suspend fun login(phone: String, passwordInput: String): Result<HurifixUser> {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) {
            return Result.failure(Exception("Please enter a valid 10-digit mobile number"))
        }

        try {
            val docRef = firestore.collection(USERS_COLLECTION).document(cleanPhone)
            val snapshot = try {
                docRef.get().await()
            } catch (e: Exception) {
                null
            }

            // Check if Master Admin initial setup is needed
            if (snapshot == null || !snapshot.exists()) {
                if (HurifixUser.isMasterAdminPhone(cleanPhone)) {
                    val defaultAdmin = HurifixUser(
                        phone = cleanPhone,
                        name = if (cleanPhone == HurifixUser.PRIMARY_ADMIN_PHONE) "Master Admin" else "Secondary Admin",
                        password = HurifixUser.DEFAULT_ADMIN_PASSWORD,
                        role = HurifixUser.ROLE_ADMIN,
                        is_blocked = false,
                        can_add_experts = true,
                        can_manage_orders = true,
                        can_add_customers = true,
                        view_only = false
                    )
                    docRef.set(defaultAdmin.toMap()).await()
                    if (passwordInput != defaultAdmin.password) {
                        return Result.failure(Exception("Invalid password. Please check your credentials."))
                    }
                    return Result.success(defaultAdmin)
                }

                // Specification #2: Unknown/unregistered numbers are blocked at login
                return Result.failure(Exception("Access Denied. Contact Admin to register your account."))
            }

            val user = parseUser(snapshot)
            if (user.is_blocked) {
                return Result.failure(Exception("Access Revoked. Your account has been blocked by Admin."))
            }

            if (user.password != passwordInput) {
                return Result.failure(Exception("Invalid password. Please check your credentials."))
            }

            // Update last login
            docRef.update("last_login", System.currentTimeMillis())

            return Result.success(user)
        } catch (e: Exception) {
            // Offline fallback for master admin
            if (HurifixUser.isMasterAdminPhone(cleanPhone) && passwordInput == HurifixUser.DEFAULT_ADMIN_PASSWORD) {
                return Result.success(
                    HurifixUser(
                        phone = cleanPhone,
                        name = "Master Admin",
                        password = HurifixUser.DEFAULT_ADMIN_PASSWORD,
                        role = HurifixUser.ROLE_ADMIN,
                        is_blocked = false
                    )
                )
            }
            return Result.failure(e)
        }
    }

    suspend fun recoverAdminAccess(
        currentPhone: String,
        secretKey: String,
        lostPhoneToRevoke: String?,
        newPassword: String
    ): Result<HurifixUser> {
        val cleanCurrentPhone = currentPhone.replace(Regex("[^0-9]"), "")
        if (cleanCurrentPhone.length < 10) {
            return Result.failure(Exception("Please enter a valid 10-digit mobile number"))
        }

        if (secretKey.trim() != HurifixUser.EMERGENCY_RECOVERY_KEY) {
            return Result.failure(Exception("Invalid Emergency Secret Recovery Key!"))
        }

        if (newPassword.length < 4) {
            return Result.failure(Exception("Password must be at least 4 characters"))
        }

        try {
            // Block lost phone account if provided
            val cleanLost = lostPhoneToRevoke?.replace(Regex("[^0-9]"), "")
            if (!cleanLost.isNullOrBlank() && cleanLost.length >= 10 && cleanLost != cleanCurrentPhone) {
                firestore.collection(USERS_COLLECTION).document(cleanLost)
                    .set(mapOf("is_blocked" to true), SetOptions.merge())
                    .await()
            }

            // Promote current phone to ADMIN
            val adminUser = HurifixUser(
                phone = cleanCurrentPhone,
                name = "Hurifix Admin",
                password = newPassword,
                role = HurifixUser.ROLE_ADMIN,
                is_blocked = false,
                can_add_experts = true,
                can_manage_orders = true,
                can_add_customers = true,
                view_only = false,
                last_login = System.currentTimeMillis()
            )

            firestore.collection(USERS_COLLECTION).document(cleanCurrentPhone)
                .set(adminUser.toMap(), SetOptions.merge())
                .await()

            return Result.success(adminUser)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    fun listenToUserSession(
        phone: String,
        onUpdate: (HurifixUser) -> Unit,
        onBlocked: () -> Unit
    ): ListenerRegistration? {
        val clean = phone.replace(Regex("[^0-9]"), "")
        if (clean.isBlank()) return null

        return firestore.collection(USERS_COLLECTION).document(clean)
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null || !snapshot.exists()) return@addSnapshotListener
                val user = parseUser(snapshot)
                if (user.is_blocked) {
                    onBlocked()
                } else {
                    onUpdate(user)
                }
            }
    }

    fun getAllUsersFlow(): Flow<List<HurifixUser>> = callbackFlow {
        val registration = firestore.collection(USERS_COLLECTION)
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val users = querySnapshot?.documents?.map { parseUser(it) } ?: emptyList()
                trySend(users)
            }
        awaitClose { registration.remove() }
    }

    suspend fun saveStaffUser(user: HurifixUser): Result<Unit> {
        return try {
            firestore.collection(USERS_COLLECTION).document(user.phone)
                .set(user.toMap(), SetOptions.merge())
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserPermissions(
        phone: String,
        canAddExperts: Boolean,
        canManageOrders: Boolean,
        canAddCustomers: Boolean,
        viewOnly: Boolean
    ): Result<Unit> {
        return try {
            firestore.collection(USERS_COLLECTION).document(phone)
                .update(
                    mapOf(
                        "can_add_experts" to canAddExperts,
                        "can_manage_orders" to canManageOrders,
                        "can_add_customers" to canAddCustomers,
                        "view_only" to viewOnly
                    )
                ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun setUserBlocked(phone: String, isBlocked: Boolean): Result<Unit> {
        return try {
            firestore.collection(USERS_COLLECTION).document(phone)
                .update("is_blocked", isBlocked)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserPassword(phone: String, newPassword: String): Result<Unit> {
        return try {
            firestore.collection(USERS_COLLECTION).document(phone)
                .update("password", newPassword)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserProfile(phone: String, name: String, photoUri: String?): Result<Unit> {
        return try {
            val updates = mutableMapOf<String, Any?>("name" to name.trim())
            if (photoUri != null) {
                updates["photo_uri"] = photoUri
            }
            firestore.collection(USERS_COLLECTION).document(phone)
                .update(updates)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseUser(doc: DocumentSnapshot): HurifixUser {
        return HurifixUser(
            phone = doc.getString("phone") ?: doc.id,
            name = doc.getString("name") ?: "",
            password = doc.getString("password") ?: "",
            role = doc.getString("role") ?: HurifixUser.ROLE_STAFF,
            is_blocked = doc.getBoolean("is_blocked") ?: false,
            can_add_experts = doc.getBoolean("can_add_experts") ?: true,
            can_manage_orders = doc.getBoolean("can_manage_orders") ?: true,
            can_add_customers = doc.getBoolean("can_add_customers") ?: true,
            view_only = doc.getBoolean("view_only") ?: false,
            created_at = doc.getLong("created_at") ?: System.currentTimeMillis(),
            last_login = doc.getLong("last_login") ?: System.currentTimeMillis(),
            photo_uri = doc.getString("photo_uri")
        )
    }

    // ---------------------------------------------------------------------------------------------
    // Duplicate Order Protection & Real-time Locking
    // ---------------------------------------------------------------------------------------------

    /**
     * Checks if an order with the same customer phone number exists within the last 4 hours.
     */
    suspend fun isDuplicateOrderInLast4Hours(phone: String): Boolean {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) return false

        val fourHoursAgo = System.currentTimeMillis() - (4 * 60 * 60 * 1000)
        return try {
            val query = firestore.collection(JOBS_COLLECTION)
                .whereEqualTo("customerPhone", cleanPhone)
                .whereGreaterThanOrEqualTo("createdAt", fourHoursAgo)
                .get()
                .await()

            query.documents.any { doc ->
                val isDeleted = doc.getBoolean("isDeleted") ?: false
                !isDeleted
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Real-time Order Assignment Lock: Uses a Firestore Transaction when assigning a technician
     * so that if two users attempt to assign a technician simultaneously, only the first succeeds.
     */
    suspend fun assignExpertWithLock(
        jobId: Long,
        expertId: Long,
        expertName: String,
        expertPhone: String,
        distanceKm: Double
    ): Result<Unit> {
        val docRef = firestore.collection(JOBS_COLLECTION).document(jobId.toString())
        return try {
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(docRef)
                if (snapshot.exists()) {
                    val currentStatus = snapshot.getString("status")
                    val existingExpertId = snapshot.getLong("assignedExpertId")
                    if (existingExpertId != null && existingExpertId != expertId && currentStatus != "PENDING") {
                        val assignedName = snapshot.getString("assignedExpertName") ?: "another technician"
                        throw IllegalStateException("Order #$jobId is already assigned to $assignedName!")
                    }
                }
                // Atomically update
                transaction.set(
                    docRef,
                    mapOf(
                        "id" to jobId,
                        "status" to "PROCESSING",
                        "assignedExpertId" to expertId,
                        "assignedExpertName" to expertName,
                        "assignedExpertPhone" to expertPhone,
                        "distanceKmAtDispatch" to distanceKm,
                        "last_updated" to System.currentTimeMillis()
                    ),
                    SetOptions.merge()
                )
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Cloud Synchronization: Lightweight documents for Spark Free Plan
    // ---------------------------------------------------------------------------------------------

    suspend fun pushJobToFirestore(job: CustomerJobEntity): Boolean {
        return try {
            val map = mapOf(
                "id" to job.id,
                "customerName" to job.customerName,
                "customerPhone" to job.customerPhone,
                "serviceType" to job.serviceType,
                "issueDescription" to job.issueDescription,
                "address" to job.address,
                "latitude" to job.latitude,
                "longitude" to job.longitude,
                "status" to job.status,
                "assignedExpertId" to job.assignedExpertId,
                "assignedExpertName" to job.assignedExpertName,
                "assignedExpertPhone" to job.assignedExpertPhone,
                "distanceKmAtDispatch" to job.distanceKmAtDispatch,
                "ratingGiven" to job.ratingGiven?.toDouble(),
                "reviewFeedback" to job.reviewFeedback,
                "createdAt" to job.createdAt,
                "completedAt" to job.completedAt,
                "isExpertNotified" to job.isExpertNotified,
                "isCustomerNotifiedOnAssign" to job.isCustomerNotifiedOnAssign,
                "isCustomerNotifiedOnCompletion" to job.isCustomerNotifiedOnCompletion,
                "assignMessageLaterDismissedAt" to job.assignMessageLaterDismissedAt,
                "isDeleted" to job.isDeleted,
                "deletedAt" to job.deletedAt,
                "last_updated" to job.last_updated
            )
            firestore.collection(JOBS_COLLECTION).document(job.id.toString())
                .set(map, SetOptions.merge())
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun fetchAllRemoteJobs(): List<CustomerJobEntity> {
        return try {
            val snapshot = firestore.collection(JOBS_COLLECTION).get().await()
            snapshot.documents.mapNotNull { doc ->
                val id = doc.getLong("id") ?: doc.id.toLongOrNull() ?: return@mapNotNull null
                CustomerJobEntity(
                    id = id,
                    customerName = doc.getString("customerName") ?: "Customer",
                    customerPhone = doc.getString("customerPhone") ?: "",
                    serviceType = doc.getString("serviceType") ?: "General Service",
                    issueDescription = doc.getString("issueDescription") ?: "",
                    address = doc.getString("address") ?: "",
                    latitude = doc.getDouble("latitude") ?: 28.57,
                    longitude = doc.getDouble("longitude") ?: 77.32,
                    status = doc.getString("status") ?: "PENDING",
                    assignedExpertId = doc.getLong("assignedExpertId"),
                    assignedExpertName = doc.getString("assignedExpertName"),
                    assignedExpertPhone = doc.getString("assignedExpertPhone"),
                    distanceKmAtDispatch = doc.getDouble("distanceKmAtDispatch"),
                    ratingGiven = doc.getDouble("ratingGiven")?.toFloat(),
                    reviewFeedback = doc.getString("reviewFeedback"),
                    createdAt = doc.getLong("createdAt") ?: System.currentTimeMillis(),
                    completedAt = doc.getLong("completedAt"),
                    isExpertNotified = doc.getBoolean("isExpertNotified") ?: false,
                    isCustomerNotifiedOnAssign = doc.getBoolean("isCustomerNotifiedOnAssign") ?: false,
                    isCustomerNotifiedOnCompletion = doc.getBoolean("isCustomerNotifiedOnCompletion") ?: false,
                    assignMessageLaterDismissedAt = doc.getLong("assignMessageLaterDismissedAt"),
                    isDeleted = doc.getBoolean("isDeleted") ?: false,
                    deletedAt = doc.getLong("deletedAt"),
                    last_updated = doc.getLong("last_updated") ?: System.currentTimeMillis(),
                    is_synced = true
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun pushExpertToFirestore(expert: ExpertEntity): Boolean {
        return try {
            val map = mapOf(
                "id" to expert.id,
                "name" to expert.name,
                "phone" to expert.phone,
                "category" to expert.category,
                "address" to expert.address,
                "latitude" to expert.latitude,
                "longitude" to expert.longitude,
                "isAvailable" to expert.isAvailable,
                "rating" to expert.rating.toDouble(),
                "ratingSum" to expert.ratingSum.toDouble(),
                "totalRatingsCount" to expert.totalRatingsCount,
                "completedJobsCount" to expert.completedJobsCount,
                "cancelledJobsCount" to expert.cancelledJobsCount,
                "isWelcomeMessageSent" to expert.isWelcomeMessageSent,
                "isDeleted" to expert.isDeleted,
                "deletedAt" to expert.deletedAt,
                "createdAt" to expert.createdAt,
                "last_updated" to expert.last_updated
            )
            firestore.collection(EXPERTS_COLLECTION).document(expert.id.toString())
                .set(map, SetOptions.merge())
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun fetchAllRemoteExperts(): List<ExpertEntity> {
        return try {
            val snapshot = firestore.collection(EXPERTS_COLLECTION).get().await()
            snapshot.documents.mapNotNull { doc ->
                val id = doc.getLong("id") ?: doc.id.toLongOrNull() ?: return@mapNotNull null
                ExpertEntity(
                    id = id,
                    name = doc.getString("name") ?: "",
                    phone = doc.getString("phone") ?: "",
                    category = doc.getString("category") ?: "Electrician",
                    address = doc.getString("address") ?: "",
                    latitude = doc.getDouble("latitude") ?: 28.57,
                    longitude = doc.getDouble("longitude") ?: 77.32,
                    isAvailable = doc.getBoolean("isAvailable") ?: true,
                    rating = (doc.getDouble("rating") ?: 4.8).toFloat(),
                    ratingSum = (doc.getDouble("ratingSum") ?: 4.8).toFloat(),
                    totalRatingsCount = doc.getLong("totalRatingsCount")?.toInt() ?: 1,
                    completedJobsCount = doc.getLong("completedJobsCount")?.toInt() ?: 0,
                    cancelledJobsCount = doc.getLong("cancelledJobsCount")?.toInt() ?: 0,
                    isWelcomeMessageSent = doc.getBoolean("isWelcomeMessageSent") ?: false,
                    isDeleted = doc.getBoolean("isDeleted") ?: false,
                    deletedAt = doc.getLong("deletedAt"),
                    createdAt = doc.getLong("createdAt") ?: System.currentTimeMillis(),
                    last_updated = doc.getLong("last_updated") ?: System.currentTimeMillis(),
                    is_synced = true
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun pushCategoryToFirestore(cat: ExpertCategoryEntity): Boolean {
        return try {
            val map = mapOf(
                "id" to cat.id,
                "name" to cat.name,
                "isDefault" to cat.isDefault,
                "createdAt" to cat.createdAt,
                "last_updated" to cat.last_updated
            )
            firestore.collection(CATEGORIES_COLLECTION).document(cat.name)
                .set(map, SetOptions.merge())
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun fetchAllRemoteCategories(): List<ExpertCategoryEntity> {
        return try {
            val snapshot = firestore.collection(CATEGORIES_COLLECTION).get().await()
            snapshot.documents.mapNotNull { doc ->
                val id = doc.getLong("id") ?: (doc.id.hashCode().toLong())
                val name = doc.getString("name") ?: doc.id
                if (name.isBlank()) return@mapNotNull null
                ExpertCategoryEntity(
                    id = id,
                    name = name,
                    isDefault = doc.getBoolean("isDefault") ?: false,
                    createdAt = doc.getLong("createdAt") ?: System.currentTimeMillis(),
                    last_updated = doc.getLong("last_updated") ?: System.currentTimeMillis(),
                    is_synced = true
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
