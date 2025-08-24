package com.example.mvvm_no_repo_project.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAllFlow(): Flow<List<User>>

    @Insert
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM users")
    suspend fun clear()
}