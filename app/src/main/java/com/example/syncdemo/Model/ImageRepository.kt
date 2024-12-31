package com.example.syncdemo.Model

import com.example.syncdemo.Model.Pixabay.ImageResult
import com.example.syncdemo.Model.Pixabay.PixabayClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ImageRepository(private val pixabayClient: PixabayClient) {

    // 模拟网络请求，获取图片列表
    suspend fun fetchImageUrls(params:Map<String,String>): List<ImageResult> {
        return try {
            val response = pixabayClient.searchImages(params)
            response.hits
        }catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }
}
