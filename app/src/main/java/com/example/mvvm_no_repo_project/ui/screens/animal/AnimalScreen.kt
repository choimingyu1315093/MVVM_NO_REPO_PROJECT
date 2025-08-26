package com.example.mvvm_no_repo_project.ui.screens.animal

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mvvm_no_repo_project.model.animal.Animal
import com.example.mvvm_no_repo_project.ui.common.ResourceState
import com.example.mvvm_no_repo_project.ui.common.UiEvent
import com.example.mvvm_no_repo_project.viewmodel.animalViewModel.AnimalViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AnimalScreen(navController: NavController, viewModel: AnimalViewModel = viewModel()){
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is UiEvent.Message -> {
                    Toast.makeText(navController.context, event.text, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Log.d("TAG", "화면 전환 코드")
                }
            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        TextButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("뒤로가기")
        }

        when(state.value){
            is ResourceState.Loading -> {
                CircularProgressIndicator()
            }
            is ResourceState.Error -> {
                Log.d("TAG", "AnimalScreen: Error")
            }
            is ResourceState.Success -> {
                val animals = (state.value as ResourceState.Success<List<Animal>>).data
                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(animals){ animal ->
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Yellow)
                                .padding(10.dp)
                                .clickable {
                                    viewModel.animalClick(animal.name)
                                },
                            text = animal.name
                        )
                    }
                }
            }
        }
    }
}