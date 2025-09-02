package com.clipsmyanmar.data.mapper

import com.clipsmyanmar.data.model.Movie
import com.clipsmyanmar.data.remote.dto.MovieDto

object MovieMapper {
    fun fromDto(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            videoUrl = dto.videoUrl,
            thumbnailUrl = dto.thumbnailUrl,
            creatorName = dto.creatorName,
            creatorPhotoUrl = dto.creatorPhotoUrl,
            timestamp = dto.timestamp?.toDate()?.time ?: System.currentTimeMillis()
        )
    }
}
