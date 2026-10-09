package com.example.data.repository

import android.content.Context
import com.example.data.local.CustomerDao
import com.example.data.local.CustomerJobDao
import com.example.data.local.ExpertCategoryDao
import com.example.data.local.ExpertDao
import com.example.data.local.TechnicianDao
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import kotlinx.coroutines.flow.Flow

class DispatchRepository(
    private val context: Context,
    private val initialUserPhone: String = "",
    private val fallbackExpertDao: ExpertDao,
    private val fallbackJobDao: CustomerJobDao,
    private val fallbackCategoryDao: ExpertCategoryDao,
    private val fallbackTechnicianDao: TechnicianDao,
    private val fallbackCustomerDao: CustomerDao
) {
    val activeJobs: Flow<List<CustomerJobEntity>> = fallbackJobDao.getAllActiveJobs()
    val recycleBinJobs: Flow<List<CustomerJobEntity>> = fallbackJobDao.getRecycleBinJobs()
    val activeExperts: Flow<List<ExpertEntity>> = fallbackExpertDao.getAllActiveExperts()
    val recycleBinExperts: Flow<List<ExpertEntity>> = fallbackExpertDao.getRecycleBinExperts()
    val categories: Flow<List<ExpertCategoryEntity>> = fallbackCategoryDao.getAllCategories()

    suspend fun refreshAllData() {}
    suspend fun onUserLoggedIn(phone: String) {}
    fun onUserLoggedOut() {}
    suspend fun ensureDefaultCategoriesForCurrentUser() {}

    suspend fun insertJob(job: CustomerJobEntity): Long {
        return fallbackJobDao.insertJob(job)
    }

    suspend fun updateJob(job: CustomerJobEntity) {
        fallbackJobDao.updateJob(job)
    }

    suspend fun deleteJob(job: CustomerJobEntity) {
        fallbackJobDao.updateJob(job.copy(isDeleted = true, deletedAt = System.currentTimeMillis()))
    }

    suspend fun deleteJobPermanently(id: Long) {
        fallbackJobDao.deletePermanently(id)
    }

    suspend fun restoreJobFromRecycleBin(job: CustomerJobEntity) {
        fallbackJobDao.updateJob(job.copy(isDeleted = false, deletedAt = null))
    }

    suspend fun insertExpert(expert: ExpertEntity): Long {
        return fallbackExpertDao.insertExpert(expert)
    }

    suspend fun updateExpert(expert: ExpertEntity) {
        fallbackExpertDao.updateExpert(expert)
    }

    suspend fun deleteExpert(expert: ExpertEntity) {
        fallbackExpertDao.updateExpert(expert.copy(isDeleted = true, deletedAt = System.currentTimeMillis()))
    }

    suspend fun deleteExpertPermanently(id: Long) {
        fallbackExpertDao.deletePermanently(id)
    }

    suspend fun restoreExpertFromRecycleBin(expert: ExpertEntity) {
        fallbackExpertDao.updateExpert(expert.copy(isDeleted = false, deletedAt = null))
    }

    suspend fun clearRecycleBin() {
        fallbackJobDao.clearRecycleBin()
        fallbackExpertDao.clearRecycleBin()
    }

    suspend fun insertCategory(category: ExpertCategoryEntity): Long {
        return fallbackCategoryDao.insertCategory(category)
    }

    suspend fun deleteCategory(id: Long) {
        fallbackCategoryDao.deleteCategory(id)
    }

    suspend fun restoreBackup(
        jobs: List<CustomerJobEntity>,
        experts: List<ExpertEntity>,
        categories: List<ExpertCategoryEntity>
    ) {
        for (job in jobs) {
            fallbackJobDao.insertJob(job)
        }
        for (expert in experts) {
            fallbackExpertDao.insertExpert(expert)
        }
        for (category in categories) {
            fallbackCategoryDao.insertCategory(category)
        }
    }
}
