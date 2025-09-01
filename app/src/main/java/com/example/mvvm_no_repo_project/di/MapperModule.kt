package com.example.mvvm_no_repo_project.di

import com.example.mvvm_no_repo_project.model.animal.AnimalMapper
import com.example.mvvm_no_repo_project.model.user.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

    @Provides
    fun provideAnimalMapper(): AnimalMapper = AnimalMapper()
}