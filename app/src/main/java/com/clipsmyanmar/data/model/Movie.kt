package com.clipsmyanmar.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: String = "",
    val title: String = "",
    val posterUrl: String? = null,
    val poster_prompt: String? = null,
    val rating: Double = 0.0,
    val review_en: String = "",
    val review_mm: String = "",
    val release_year: Int = 2024,
    val genres: List<String> = emptyList(),
    val video_link: String = "",
    val created_by: String = "",
    @ServerTimestamp val created_at: Date? = null
)
