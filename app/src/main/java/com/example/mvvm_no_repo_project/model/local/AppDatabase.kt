package com.example.mvvm_no_repo_project.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_no_repo_project.model.animal.Animal
import com.example.mvvm_no_repo_project.model.animal.AnimalDAO
import com.example.mvvm_no_repo_project.model.user.User
import com.example.mvvm_no_repo_project.model.user.UserDAO
import com.example.mvvm_no_repo_project.utils.Constants

@Database(entities = [User::class, Animal::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDAO
    abstract fun getAnimalDao(): AnimalDAO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null //@Volatile 여러 스레드에서 INSTANCE를 안전하게 읽을 수 있도록 보장함.

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this){ //synchronized는 여러 스레드가 동시에 DB를 만들려고 할 때 딱 한 번만 생성되도록 잠금 처리.
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DB_NAME
                ).build().also { INSTANCE = it }
            }
    }
}