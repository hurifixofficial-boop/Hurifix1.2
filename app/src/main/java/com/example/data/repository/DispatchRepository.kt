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
}
