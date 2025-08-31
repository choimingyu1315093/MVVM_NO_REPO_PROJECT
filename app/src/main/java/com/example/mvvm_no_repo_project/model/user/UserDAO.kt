package com.example.mvvm_no_repo_project.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAllFlow(): Flow<List<User>>

    @Upsert
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM users")
    suspend fun clear()
}