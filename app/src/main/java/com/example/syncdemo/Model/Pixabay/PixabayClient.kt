package com.example.syncdemo.Model.Pixabay

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PixabayClient {

    @GET("api/")
    suspend fun searchImages(@QueryMap params: Map<String,String>):PixabayResponse

    companion object{
        private const val BASE_URL = "https://pixabay.com/"

        fun create():PixabayClient{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PixabayClient::class.java)
        }
    }
}