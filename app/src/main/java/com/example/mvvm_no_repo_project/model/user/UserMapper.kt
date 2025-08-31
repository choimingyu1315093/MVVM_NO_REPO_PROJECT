package com.example.mvvm_no_repo_project.model.user

class UserMapper {
    fun dtoToModel(userDto: UserDto): User {
        val safeId = when {
            userDto.id != null -> userDto.id
            userDto.email != null -> userDto.email.hashCode()
            userDto.name != null  -> userDto.name.hashCode()
            else -> (System.currentTimeMillis() and 0x7fffffff).toInt()
        }
        return User(
            id = safeId,
            name = userDto.name ?: "",
            email = userDto.email ?: ""
        )
    }
}