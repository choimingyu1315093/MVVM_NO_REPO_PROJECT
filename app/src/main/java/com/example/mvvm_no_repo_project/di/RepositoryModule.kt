package com.example.mvvm_no_repo_project.di

import com.example.mvvm_no_repo_project.model.animal.AnimalRepository
import com.example.mvvm_no_repo_project.model.animal.AnimalRepositoryImpl
import com.example.mvvm_no_repo_project.model.user.UserRepository
import com.example.mvvm_no_repo_project.model.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds //인터페이스와 구현체를 연결
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindAnimalRepository(
        impl: AnimalRepositoryImpl
    ): AnimalRepository
}