package com.example.mvvm_no_repo_project.model.animal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val species: String = "",
    val age: Int = 0
)