package com.clipsmyanmar.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clipsmyanmar.data.repository.AuthRepository
import com.clipsmyanmar.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// This will represent the state of the UI
data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signInState = MutableStateFlow(AuthUiState())
    val signInState = _signInState.asStateFlow()

    private val _signUpState = MutableStateFlow(AuthUiState())
    val signUpState = _signUpState.asStateFlow()
    
    fun isUserLoggedIn(): Boolean {
        return authRepository.getCurrentUser() != null
    }

    fun signIn(email: String, pass: String) {
        viewModelScope.launch {
            _signInState.value = AuthUiState(isLoading = true)
            when (val result = authRepository.login(email, pass)) {
                is Resource.Success -> {
                    _signInState.value = AuthUiState(isSuccess = true)
                }
                is Resource.Error -> {
                    _signInState.value = AuthUiState(error = result.message)
                }
                else -> {} // Should not happen for login
            }
        }
    }

    // Add signUp function similarly
}

