package com.example.mvvm_no_repo_project.model.user

import com.example.mvvm_no_repo_project.model.common.ErrorHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

//구현체
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val dao: UserDAO,
    private val api: UserApiService,
    private val mapper: UserMapper
): UserRepository {
    override fun users(): Flow<List<User>> = dao.getAllFlow()

    override suspend fun refresh() {
        try {
            val dtos = api.getUsers()
            val models = dtos.map(mapper::dtoToModel)
            dao.clear()
            dao.insertAll(models)
        }catch (t: Throwable){
            throw ErrorHandler.wrap(t)
        }
    }
}