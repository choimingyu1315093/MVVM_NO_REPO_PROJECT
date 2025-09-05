package com.example.mvvm_no_repo_project.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_no_repo_project.model.animal.AnimalDAO
import com.example.mvvm_no_repo_project.model.local.AppDatabase
import com.example.mvvm_no_repo_project.model.user.UserDAO
import com.example.mvvm_no_repo_project.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile private var INSTANCE: AppDatabase? = null

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                Constants.DB_NAME
            ).build().also { INSTANCE = it }
        }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDAO =
        database.getUserDao()

    @Provides
    fun provideAnimalDao(database: AppDatabase): AnimalDAO =
        database.getAnimalDao()
}