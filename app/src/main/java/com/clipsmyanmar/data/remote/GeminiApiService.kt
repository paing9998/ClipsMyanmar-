package com.clipsmyanmar.data.remote

import com.clipsmyanmar.data.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GeminiApiService {
    @POST("generateMovie")
    suspend fun generateMovieDetails(@Body body: TitleRequest): GeminiResponse
}

@kotlinx.serialization.Serializable
data class TitleRequest(val data: TitleData)

@kotlinx.serialization.Serializable
data class TitleData(val title: String)
