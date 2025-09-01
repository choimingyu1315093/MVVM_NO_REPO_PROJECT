package com.example.mvvm_no_repo_project.model.animal

import kotlinx.coroutines.flow.Flow

interface AnimalRepository {
    fun animals(): Flow<List<Animal>>

    suspend fun refresh()
}