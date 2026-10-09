package com.example.data.model

/**
 * Data model for Hurifix System Users (Admins & Team Members) stored in Firestore 'users' collection.
 * Includes Role-Based Access Control (RBAC) permissions and Custom Designation Tags.
 */
data class HurifixUser(
    val phone: String = "",
    val name: String = "",
    val password: String = "",
    val role: String = ROLE_STAFF,
    val designation_tag: String = "",
    val profile_pic_url: String? = null,
    val is_blocked: Boolean = false,
    val can_manage_orders: Boolean = true,
    val can_add_experts: Boolean = true,
    val can_add_customers: Boolean = true,
    val can_delete_orders: Boolean = false,
    val can_export_reports: Boolean = false,
    val view_only: Boolean = false,
    val is_deleted: Boolean = false,
    val created_at: Long = System.currentTimeMillis(),
    val last_updated: Long = System.currentTimeMillis()
) {
    companion object {
        const val ROLE_ADMIN = "ADMIN"
        const val ROLE_STAFF = "STAFF"

        const val PRIMARY_ADMIN_PHONE = "9991287646"
        const val SECONDARY_ADMIN_PHONE = "8307817684"
        const val MASTER_ADMIN_PASSWORD = "Donboss890"
        const val EMERGENCY_RECOVERY_KEY = "HURIFIX-RECOVER-2026"

        // Common Designation Tag presets
        val DEFAULT_DESIGNATION_PRESETS = listOf(
            "Co-Founder",
            "Partner",
            "Operations Head",
            "Team Member",
            "Dispatch Lead",
            "Customer Support",
            "Regional Manager"
        )

        fun createMasterAdmin(phone: String, name: String = "Hurifix Admin"): HurifixUser {
            return HurifixUser(
                phone = phone,
                name = name,
                password = MASTER_ADMIN_PASSWORD,
                role = ROLE_ADMIN,
                designation_tag = "Co-Founder & Admin",
                is_blocked = false,
                is_deleted = false,
                can_manage_orders = true,
                can_add_experts = true,
                can_add_customers = true,
                can_delete_orders = true,
                can_export_reports = true,
                view_only = false,
                created_at = System.currentTimeMillis(),
                last_updated = System.currentTimeMillis()
            )
        }
    }

    val isPrimaryAdmin: Boolean
        get() = phone == PRIMARY_ADMIN_PHONE || phone == SECONDARY_ADMIN_PHONE

    val isAdmin: Boolean
        get() = role.equals(ROLE_ADMIN, ignoreCase = true) || isPrimaryAdmin

    val displayDesignation: String
        get() = if (designation_tag.isNotBlank()) {
            designation_tag.trim()
        } else if (isAdmin) {
            "Master Admin"
        } else {
            "Team Member"
        }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "phone" to phone,
            "name" to name,
            "password" to password,
            "role" to role,
            "designation_tag" to displayDesignation,
            "profile_pic_url" to profile_pic_url,
            "is_blocked" to is_blocked,
            "is_deleted" to is_deleted,
            "can_manage_orders" to can_manage_orders,
            "can_add_experts" to can_add_experts,
            "can_add_customers" to can_add_customers,
            "can_delete_orders" to can_delete_orders,
            "can_export_reports" to can_export_reports,
            "view_only" to view_only,
            "created_at" to created_at,
            "last_updated" to last_updated
        )
    }
}
