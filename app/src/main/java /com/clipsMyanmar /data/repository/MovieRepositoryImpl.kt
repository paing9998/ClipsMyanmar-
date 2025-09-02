package com.clipsmyanmar.data.repository

import com.clipsmyanmar.data.mapper.MovieMapper
import com.clipsmyanmar.data.model.Movie
import com.clipsmyanmar.data.remote.dto.MovieDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : MovieRepository {

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val MOVIES_COLLECTION = "movies"
    }

    override suspend fun isAdmin(): Boolean {
        val currentUser = auth.currentUser ?: return false
        return try {
            val document = firestore.collection(USERS_COLLECTION)
                .document(currentUser.uid)
                .get()
                .await()

            document.getBoolean("isAdmin") ?: false
        } catch (e: Exception) {
            // Log the exception e
            false
        }
    }

    override fun getMovies(): Flow<List<Movie>> = callbackFlow {
        val collection = firestore.collection(MOVIES_COLLECTION)
            .orderBy("timestamp", Query.Direction.DESCENDING)

        val registration = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error) // Close the flow on error
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val movies = snapshot.toObjects(MovieDto::class.java)
                    .map { MovieMapper.fromDto(it) }
                trySend(movies) // Send the latest data to the flow
            }
        }

        // This block is called when the consumer of the flow is cancelled
        awaitClose { registration.remove() }
    }
}
