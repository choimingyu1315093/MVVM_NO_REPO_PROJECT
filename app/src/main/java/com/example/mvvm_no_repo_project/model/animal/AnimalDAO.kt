package com.example.mvvm_no_repo_project.model.animal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDAO {
    @Query("SELECT * FROM animals")
    fun getAllFlow(): Flow<List<Animal>>

    @Upsert
    suspend fun insertAll(animals: List<Animal>)

    @Query("DELETE FROM animals")
    suspend fun clear()
}