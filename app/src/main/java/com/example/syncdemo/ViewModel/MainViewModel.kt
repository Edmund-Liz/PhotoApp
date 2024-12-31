package com.example.syncdemo.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncdemo.Model.Image
import com.example.syncdemo.Model.ImageRepository
import com.example.syncdemo.Model.Pixabay.ImageResult
import com.example.syncdemo.Model.Pixabay.PixabayResponse
import kotlinx.coroutines.launch

class MainViewModel(application: Application,private val repository:ImageRepository):ViewModel() {

    private val _images = MutableLiveData<List<ImageResult>>()
    val images:LiveData<List<ImageResult>> get() = _images

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadImages(params: Map<String,String>){
        viewModelScope.launch {
            try {
                val images = repository.fetchImageUrls(params)
                if (images.isNotEmpty()){
                    _images.value = images
                }else{
                    _error.value = "No Images Found."
                }
            }catch (e:Exception){
                _error.value = "Error fetching images: ${e.localizedMessage}"
            }
        }
    }

}