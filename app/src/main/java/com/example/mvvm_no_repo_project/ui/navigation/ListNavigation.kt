package com.example.mvvm_no_repo_project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_no_repo_project.ui.screens.animal.AnimalScreen
import com.example.mvvm_no_repo_project.ui.screens.home.HomeScreen
import com.example.mvvm_no_repo_project.ui.screens.user.UserScreen

@Composable
fun ListNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListScreens.HOME.name){
        composable (route = ListScreens.HOME.name){
            HomeScreen(navController = navController)
        }

        composable (route = ListScreens.USER_LIST.name){
            UserScreen(navController = navController)
        }

        composable (route = ListScreens.ANIMAL_LIST.name){
            AnimalScreen(navController = navController)
        }
    }
}