package com.example.mvvm_no_repo_project.model.user

class UserMapper {
    fun dtoToModel(userDto: UserDto): User {
        val id = (userDto.id ?: userDto.name ?: userDto.email ?: System.currentTimeMillis().toString()).toString().toInt()
        return User(
            id = id,
            name = userDto.name ?: "",
            email = userDto.email ?: ""
        )
    }
}