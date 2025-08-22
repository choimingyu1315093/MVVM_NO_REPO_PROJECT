package com.example.mvvm_no_repo_project.viewmodel.userViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mvvm_no_repo_project.model.local.AppDatabase
import com.example.mvvm_no_repo_project.model.remote.ApiClient
import com.example.mvvm_no_repo_project.model.user.UserApiService
import com.example.mvvm_no_repo_project.model.user.UserMapper

class UserViewModel(application: Application): AndroidViewModel(application){
    private val dao = AppDatabase.getInstance(application).getUserDao()
    private val api = ApiClient.create(UserApiService::class.java)
    private val mapper = UserMapper()
}