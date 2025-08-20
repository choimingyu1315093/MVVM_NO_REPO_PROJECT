package com.example.mvvm_no_repo_project.model.animal

class AnimalMapper {
    fun dtoToModel(animalDto: AnimalDto): Animal {
        val id = (animalDto.id ?: animalDto.name ?: animalDto.species ?: animalDto.age ?: System.currentTimeMillis().toString()).toString().toInt()
        return Animal(
            id = id,
            name = animalDto.name ?: "",
            species = animalDto.species ?: "",
            age = animalDto.age ?: 0
        )
    }
}