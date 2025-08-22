package com.example.mvvm_no_repo_project.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.mvvm_no_repo_project.ui.navigation.ListScreens

@Composable
fun HomeScreen(navController: NavController){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(
            onClick = {
                navController.navigate(ListScreens.USER_LIST.name)
            }
        ) {
            Text(
                text = "UserList"
            )
        }

        Button(
            onClick = {
                navController.navigate(ListScreens.ANIMAL_LIST.name)
            }
        ) {
            Text(
                text = "AnimalList"
            )
        }
    }
}