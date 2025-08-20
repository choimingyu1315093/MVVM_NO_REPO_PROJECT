package com.example.mvvm_no_repo_project.model.user

import retrofit2.http.GET

interface UserApiService {
    @GET("7316a8a2-e5fa-462a-9fc0-2478c51429b9")
    suspend fun getUsers(): List<UserDto>
}