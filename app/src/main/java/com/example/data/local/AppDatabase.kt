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
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun technicianDao(): TechnicianDao
    abstract fun customerDao(): CustomerDao
    abstract fun expertDao(): ExpertDao
    abstract fun customerJobDao(): CustomerJobDao
    abstract fun expertCategoryDao(): ExpertCategoryDao

    companion object {
        private val INSTANCES = java.util.concurrent.ConcurrentHashMap<String, AppDatabase>()

        fun getDatabase(context: Context, userPhone: String = "default"): AppDatabase {
            val cleanPhone = userPhone.replace(Regex("[^0-9]"), "").ifBlank { "default" }
            val dbName = "hurifix_data_${cleanPhone}.db"
            return INSTANCES.computeIfAbsent(dbName) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
