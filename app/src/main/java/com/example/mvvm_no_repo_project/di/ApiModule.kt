package com.example.mvvm_no_repo_project.di

import com.example.mvvm_no_repo_project.model.animal.AnimalApiService
import com.example.mvvm_no_repo_project.model.user.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    //BaseUrl이 달라지는 경우, 새로운 Named Retrofit을 추가해주면 된다.
    @Provides
    @Singleton
    fun provideUserApiService(
        @Named("basicRetrofit") retrofit: Retrofit
    ): UserApiService = retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideAnimalApiService(
        @Named("basicRetrofit") retrofit: Retrofit
    ): AnimalApiService = retrofit.create(AnimalApiService::class.java)
}