package com.example.mvvm_no_repo_project.model.animal

class AnimalMapper {
    fun dtoToModel(animalDto: AnimalDto): Animal {
        val safeId = when {
            animalDto.id != null -> animalDto.id
            animalDto.species != null -> animalDto.species.hashCode()
            animalDto.name != null  -> animalDto.name.hashCode()
            else -> (System.currentTimeMillis() and 0x7fffffff).toInt()
        }
        return Animal(
            id = safeId,
            name = animalDto.name ?: "",
            species = animalDto.species ?: "",
            age = animalDto.age ?: 0
        )
    }
}