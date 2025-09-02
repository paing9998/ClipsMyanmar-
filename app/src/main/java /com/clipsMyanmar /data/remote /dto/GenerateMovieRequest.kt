package com.clipsmyanmar.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateMovieRequest(
    @SerialName("prompt")
    val prompt: String,
    @SerialName("userId")
    val userId: String
)
