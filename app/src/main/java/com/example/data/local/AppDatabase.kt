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
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun technicianDao(): TechnicianDao
    abstract fun customerDao(): CustomerDao
    abstract fun expertDao(): ExpertDao
    abstract fun customerJobDao(): CustomerJobDao
    abstract fun expertCategoryDao(): ExpertCategoryDao

    companion object {
        const val CENTRAL_DATABASE_NAME = "hurifix_central_shared.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, @Suppress("UNUSED_PARAMETER") userPhone: String = ""): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    CENTRAL_DATABASE_NAME
                )
                    .fallbackToDestructiveMigration(dropAllTables = false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
