package com.example.mvvm_no_repo_project.ui.common

sealed interface UiEvent {
    object RefreshComplete: UiEvent
    data class Message(val text: String): UiEvent
    data class Navigate(val route: String): UiEvent
}