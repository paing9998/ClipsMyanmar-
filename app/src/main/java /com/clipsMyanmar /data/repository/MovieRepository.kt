package com.clipsmyanmar.data.repository

import com.clipsmyanmar.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    /**
     * Checks if the currently authenticated user has admin privileges.
     * @return true if the user is an admin, false otherwise.
     */
    suspend fun isAdmin(): Boolean

    /**
     * Fetches a stream of movies from the database, ordered by creation time.
     * @return A Flow emitting a list of Movie objects.
     */
    fun getMovies(): Flow<List<Movie>>
}
