package com.example.mvvm_no_repo_project.model.animal

import com.google.gson.annotations.SerializedName

data class AnimalDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("age")
    val age: Int? = null
)