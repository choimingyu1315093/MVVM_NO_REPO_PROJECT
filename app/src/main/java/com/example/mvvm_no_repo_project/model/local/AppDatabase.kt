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
        //볼레틀
        @Volatile private var INSTANCE: AppDatabase? = null //@Volatile 앱 전체에서 INSTANCE 하나의 객체만 쓰도록 보장(여러 스레드에서 INSTANCE를 안전하게 읽을 수 있도록 보장함.)

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this){ //synchronized는 잠금 장치(잠금 장치 하고 만든다.)
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DB_NAME
                ).build().also { INSTANCE = it }
            }
    }
}