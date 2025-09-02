package com.clipsmyanmar.data.remote

import com.clipsmyanmar.data.remote.dto.GenerateMovieRequest
import com.clipsmyanmar.data.remote.dto.GenerateMovieResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MovieGenService {
    @POST("generateMovie")
    suspend fun generateMovie(@Body request: GenerateMovieRequest): GenerateMovieResponse
}
