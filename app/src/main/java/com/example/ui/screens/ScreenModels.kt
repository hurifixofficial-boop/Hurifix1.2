package com.example.ui.screens

import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity
import com.example.data.model.ExpertCategoryEntity

sealed class DeleteTarget {
    data class Job(val job: CustomerJobEntity) : DeleteTarget()
    data class Expert(val expert: ExpertEntity) : DeleteTarget()
    data class Category(val category: ExpertCategoryEntity) : DeleteTarget()
}

data class OrderCollisionTarget(
    val job: CustomerJobEntity,
    val managerName: String,
    val managerDesignation: String? = null
)
