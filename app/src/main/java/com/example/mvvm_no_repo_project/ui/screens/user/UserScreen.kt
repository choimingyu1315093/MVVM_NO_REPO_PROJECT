package com.example.mvvm_no_repo_project.ui.screens.user

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mvvm_no_repo_project.model.user.User
import com.example.mvvm_no_repo_project.ui.common.ResourceState
import com.example.mvvm_no_repo_project.viewmodel.userViewModel.UserViewModel

@Composable
fun UserScreen(navController: NavController, viewModel: UserViewModel = viewModel()){
    val state = viewModel.state.collectAsState()

    when(state.value){
        is ResourceState.Loading -> {
            Log.d("TAG", "UserScreen: Loading")
        }
        is ResourceState.Error -> {
            Log.d("TAG", "UserScreen: Error")
        }
        is ResourceState.Success<*> -> {
            val users = (state.value as ResourceState.Success<List<User>>).data
            Log.d("TAG", "UserScreen: Success $users")
//            LazyColumn {
//                items(users){ user ->
//                    Text(
//                        text = user.name
//                    )
//                }
//            }
        }
    }
}