package com.example.mvvm_no_repo_project.viewmodel.animalViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_no_repo_project.model.animal.Animal
import com.example.mvvm_no_repo_project.model.animal.AnimalApiService
import com.example.mvvm_no_repo_project.model.animal.AnimalMapper
import com.example.mvvm_no_repo_project.model.common.ErrorHandler
import com.example.mvvm_no_repo_project.model.local.AppDatabase
import com.example.mvvm_no_repo_project.model.remote.ApiClient
import com.example.mvvm_no_repo_project.ui.common.ResourceState
import com.example.mvvm_no_repo_project.ui.common.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application): AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).getAnimalDao()
    private val api = ApiClient.create(AnimalApiService::class.java)
    private val mapper = AnimalMapper()

    private var _state = MutableStateFlow<ResourceState<List<Animal>>>(ResourceState.Loading)
    val state: StateFlow<ResourceState<List<Animal>>> = _state

    private var _event = MutableSharedFlow<UiEvent>(replay = 0, extraBufferCapacity = 1)
    val event: SharedFlow<UiEvent> = _event.asSharedFlow()

    init {
        observeLocal()
        refresh()
    }

    private fun observeLocal() = viewModelScope.launch {
        dao.getAllFlow()
            .onStart { _state.value = ResourceState.Loading }
            .catch {
                _state.value = ResourceState.Error(it.message ?: "Unknown Error")
                _event.emit(UiEvent.Message("로컬 데이터 읽기 실패"))
            }
            .collectLatest { _state.value = ResourceState.Success(it) }
    }

    private fun refresh() = viewModelScope.launch {
        runCatching {
            val dtos = api.getAnimals()
            val models = dtos.map(mapper::dtoToModel)
            dao.clear()
            dao.insertAll(models)
        }.onSuccess {
            _event.emit(UiEvent.RefreshComplete)
        }.onFailure {
            val msg = ErrorHandler.wrap(it).message ?: "Refresh failed"
            _state.value = ResourceState.Error(msg)
            _event.emit(UiEvent.Message(msg))
        }
    }

    fun animalClick(name: String) = viewModelScope.launch {
        _event.emit(UiEvent.Message("Hello, $name"))
    }
}