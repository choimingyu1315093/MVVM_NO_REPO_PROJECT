package com.example.mvvm_no_repo_project.model.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    //Local
    fun users(): Flow<List<User>>

    //Remote
    suspend fun refresh()
}