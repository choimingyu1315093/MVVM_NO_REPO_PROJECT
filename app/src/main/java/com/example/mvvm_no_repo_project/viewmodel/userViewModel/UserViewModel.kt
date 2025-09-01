package com.example.mvvm_no_repo_project.viewmodel.userViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_no_repo_project.model.common.ErrorHandler
import com.example.mvvm_no_repo_project.model.local.AppDatabase
import com.example.mvvm_no_repo_project.model.remote.ApiClient
import com.example.mvvm_no_repo_project.model.user.User
import com.example.mvvm_no_repo_project.model.user.UserApiService
import com.example.mvvm_no_repo_project.model.user.UserMapper
import com.example.mvvm_no_repo_project.model.user.UserRepository
import com.example.mvvm_no_repo_project.model.user.UserRepositoryImpl
import com.example.mvvm_no_repo_project.ui.common.ResourceState
import com.example.mvvm_no_repo_project.ui.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: UserRepository): ViewModel(){

    //화면 상태(StateFlow)
    private var _state = MutableStateFlow<ResourceState<List<User>>>(ResourceState.Loading)
    val state: StateFlow<ResourceState<List<User>>> = _state

    //일회성 이벤트(SharedFlow)
    private var _event = MutableSharedFlow<UiEvent>(replay = 0, extraBufferCapacity = 1) //replay는 재방출 안함, extraBufferCapacity는 안전 버퍼
    val event: SharedFlow<UiEvent> = _event.asSharedFlow()

    init {
        observeLocal()
        refresh()
    }

    private fun observeLocal() = viewModelScope.launch {
        repo.users()
            .onStart { _state.value = ResourceState.Loading }
            .catch {
                _state.value = ResourceState.Error(it.message ?: "Unknown Error")
                _event.emit(UiEvent.Message("로컬 데이터 읽기 실패"))
            }
            .collectLatest { _state.value = ResourceState.Success(it) }
    }

    private fun refresh() = viewModelScope.launch {
        runCatching {
            repo.refresh()
        }.onSuccess {
            _event.emit(UiEvent.RefreshComplete)
        }.onFailure {
            val msg = ErrorHandler.wrap(it).message ?: "Refresh failed"
            _state.value = ResourceState.Error(msg)
            _event.emit(UiEvent.Message(msg))
        }
    }

    fun userClick(name: String) = viewModelScope.launch {
        _event.emit(UiEvent.Message("Hello, $name"))
    }
}