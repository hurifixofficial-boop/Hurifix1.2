package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.CustomerEntity
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.TechnicianEntity

@Database(
    entities = [
        TechnicianEntity::class,
        CustomerEntity::class,
        ExpertEntity::class,
        CustomerJobEntity::class,
        ExpertCategoryEntity::class
    ],
    version = 10,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun technicianDao(): TechnicianDao
    abstract fun customerDao(): CustomerDao
    abstract fun expertDao(): ExpertDao
    abstract fun customerJobDao(): CustomerJobDao
    abstract fun expertCategoryDao(): ExpertCategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val CENTRAL_DB_NAME = "hurifix_central_business.db"

        /**
         * Returns the single, centralized business database shared by all users (Admin & Staff).
         * Completely eliminates per-user isolated databases so that everyone sees and edits the same data.
         */
        fun getDatabase(context: Context, @Suppress("UNUSED_PARAMETER") userPhone: String = ""): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    CENTRAL_DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
