package com.example.mvvm_no_repo_project.model.animal

import retrofit2.http.GET

interface AnimalApiService {
    @GET("27ffd0e4-eb7e-45c6-90fe-f5a2858a0082")
    suspend fun getAnimals(): List<AnimalDto>
}