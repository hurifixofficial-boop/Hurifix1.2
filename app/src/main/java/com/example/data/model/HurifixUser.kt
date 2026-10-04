package com.example.data.model

/**
 * Model representing a Hurifix user (Admin or Staff) stored in Firestore's "users" collection.
 */
data class HurifixUser(
    val phone: String = "",
    val name: String = "",
    val password: String = "",
    val role: String = ROLE_STAFF, // "ADMIN" or "STAFF"
    val is_blocked: Boolean = false,
    val can_add_experts: Boolean = true,
    val can_manage_orders: Boolean = true,
    val can_add_customers: Boolean = true,
    val view_only: Boolean = false,
    val created_at: Long = System.currentTimeMillis(),
    val last_login: Long = System.currentTimeMillis(),
    val photo_uri: String? = null
) {
    companion object {
        const val ROLE_ADMIN = "ADMIN"
        const val ROLE_STAFF = "STAFF"

        const val PRIMARY_ADMIN_PHONE = "9991287646"
        const val SECONDARY_ADMIN_PHONE = "8307817684"
        const val DEFAULT_ADMIN_PASSWORD = "Donboss890"
        const val EMERGENCY_RECOVERY_KEY = "HURIFIX-RECOVER-2026"

        fun isMasterAdminPhone(phone: String): Boolean {
            val clean = phone.replace(Regex("[^0-9]"), "")
            return clean == PRIMARY_ADMIN_PHONE || clean == SECONDARY_ADMIN_PHONE
        }
    }

    val isAdmin: Boolean
        get() = role.equals(ROLE_ADMIN, ignoreCase = true)

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "phone" to phone,
            "name" to name,
            "password" to password,
            "role" to role,
            "is_blocked" to is_blocked,
            "can_add_experts" to can_add_experts,
            "can_manage_orders" to can_manage_orders,
            "can_add_customers" to can_add_customers,
            "view_only" to view_only,
            "created_at" to created_at,
            "last_login" to last_login,
            "photo_uri" to photo_uri
        )
    }
}
