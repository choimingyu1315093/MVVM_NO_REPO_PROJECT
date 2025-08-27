package com.example.mvvm_no_repo_project.model.animal

import kotlinx.serialization.SerialName

data class AnimalDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("species")
    val species: String? = null,
    @SerialName("age")
    val age: Int? = null
)