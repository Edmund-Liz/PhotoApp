package com.example.syncdemo.Model.Pixabay

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class PixabayResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<ImageResult>
)

data class ImageResult(
    @SerializedName("id") val id: Int,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewURL: String,
    @SerializedName("webformatURL") val webformatURL: String,
    @SerializedName("largeImageURL") val largeImageURL: String,
    @SerializedName("user") val user: String
)