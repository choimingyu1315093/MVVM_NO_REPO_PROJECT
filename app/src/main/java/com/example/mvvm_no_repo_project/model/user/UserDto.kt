package com.example.mvvm_no_repo_project.model.user

import kotlinx.serialization.SerialName

data class UserDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("email")
    val email: String? = null
)