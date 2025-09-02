package com.clipsmyanmar.data.remote.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.serialization.Serializable

// Note: kotlinx.serialization doesn't directly handle Timestamp.
// This DTO is for Firestore's automatic mapping. The mapper will convert it.
data class MovieDto(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val videoUrl: String = "",
    val thumbnailUrl: String = "",
    val creatorName: String = "",
    val creatorPhotoUrl: String = "",
    @ServerTimestamp
    val timestamp: Timestamp? = null
)
