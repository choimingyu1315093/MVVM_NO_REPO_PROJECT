package com.example.mvvm_no_repo_project.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String = "",
    val email: String = ""
)
