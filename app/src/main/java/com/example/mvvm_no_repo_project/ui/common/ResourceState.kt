package com.example.mvvm_no_repo_project.ui.common

//sealed = 봉인된 이라는 뜻
//sealed interface는 상태를 3개(Loading, Success, Error)로 고정하고, when문에서 안전하게 처리가 가능하다.
sealed interface ResourceState<out T> { //T라는 타입을 꺼내서 쓰기만 한다는 뜻 out
    object Loading: ResourceState<Nothing> //Nothing은 값이 존재할 수 없는 타입
    data class Success<T>(val data: T): ResourceState<T>
    data class Error(val message: String): ResourceState<Nothing>
}