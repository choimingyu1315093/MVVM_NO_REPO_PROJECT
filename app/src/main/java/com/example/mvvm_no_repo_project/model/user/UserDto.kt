package com.example.mvvm_no_repo_project.model.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null
)