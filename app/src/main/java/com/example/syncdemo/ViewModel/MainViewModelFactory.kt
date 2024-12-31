package com.example.syncdemo.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.syncdemo.Model.ImageRepository

class MainViewModelFactory(
    private val application:Application,
    private val imageRepository: ImageRepository
):ViewModelProvider.AndroidViewModelFactory(application){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application,imageRepository)as T
    }
}
