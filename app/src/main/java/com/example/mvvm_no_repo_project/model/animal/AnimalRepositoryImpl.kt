package com.example.mvvm_no_repo_project.model.animal

import com.example.mvvm_no_repo_project.model.common.ErrorHandler
import kotlinx.coroutines.flow.Flow

class AnimalRepositoryImpl(
    private val api: AnimalApiService,
    private val dao: AnimalDAO,
    private val mapper: AnimalMapper
): AnimalRepository {
    override fun animals(): Flow<List<Animal>> = dao.getAllFlow()

    override suspend fun refresh() {
        try {
            val dtos = api.getAnimals()
            val models = dtos.map(mapper::dtoToModel)
            dao.clear()
            dao.insertAll(models)
        }catch (t: Throwable){
            ErrorHandler.wrap(t)
        }
    }
}