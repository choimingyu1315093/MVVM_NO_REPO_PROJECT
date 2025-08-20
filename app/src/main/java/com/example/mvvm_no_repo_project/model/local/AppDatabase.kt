package com.example.mvvm_no_repo_project.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_no_repo_project.model.animal.Animal
import com.example.mvvm_no_repo_project.model.animal.AnimalDAO
import com.example.mvvm_no_repo_project.model.user.User
import com.example.mvvm_no_repo_project.model.user.UserDAO

@Database(entities = [User::class, Animal::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDAO
    abstract fun getAnimalDao(): AnimalDAO
}