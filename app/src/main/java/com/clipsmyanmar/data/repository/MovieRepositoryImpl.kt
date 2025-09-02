package com.clipsmyanmar.data.repository

import android.net.Uri
import com.clipsmyanmar.data.model.GeminiResponse
import com.clipsmyanmar.data.model.Movie
import com.clipsmyanmar.data.remote.GeminiApiService
import com.clipsmyanmar.data.remote.TitleData
import com.clipsmyanmar.data.remote.TitleRequest
import com.clipsmyanmar.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth,
    private val geminiApi: GeminiApiService,
    private val authRepository: AuthRepository
) : MovieRepository {
    private val moviesCollection = firestore.collection("movies")

    override suspend fun getMovies(): Resource<List<Movie>> {
        // ... Implementation to fetch movies ...
        return Resource.Success(emptyList()) // Placeholder
    }
    
    override suspend fun addMovie(movie: Movie, posterUri: Uri?): Resource<Unit> {
        if (!authRepository.isCurrentUserAdmin()) return Resource.Error("Permission denied")

        return try {
            val documentRef = moviesCollection.document()
            var finalMovie = movie.copy(id = documentRef.id, created_by = auth.currentUser!!.uid)

            if (posterUri != null) {
                val storageRef = storage.reference.child("posters/${documentRef.id}.jpg")
                val uploadTask = storageRef.putFile(posterUri).await()
                val downloadUrl = uploadTask.storage.downloadUrl.await().toString()
                finalMovie = finalMovie.copy(posterUrl = downloadUrl)
            }
            
            documentRef.set(finalMovie).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to add movie")
        }
    }
    
    // ... Implement updateMovie, deleteMovie ...

    override suspend fun generateDetailsWithGemini(title: String): Resource<GeminiResponse> {
         if (!authRepository.isCurrentUserAdmin()) return Resource.Error("Permission denied")
        
        return try {
            val request = TitleRequest(TitleData(title))
            val response = geminiApi.generateMovieDetails(request)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to generate details from Gemini")
        }
    }
}
