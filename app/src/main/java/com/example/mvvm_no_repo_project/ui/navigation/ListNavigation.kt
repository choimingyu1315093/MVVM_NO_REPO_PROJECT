package com.example.mvvm_no_repo_project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_no_repo_project.ui.screens.home.HomeScreen

@Composable
fun ListNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListScreens.HOME.name){
        composable (route = ListScreens.HOME.name){
            HomeScreen(navController = navController)
        }
    }
}