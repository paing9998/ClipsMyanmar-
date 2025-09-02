package com.clipsmyanmar.data.model

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val creatorName: String,
    val creatorPhotoUrl: String,
    val timestamp: Long
)
