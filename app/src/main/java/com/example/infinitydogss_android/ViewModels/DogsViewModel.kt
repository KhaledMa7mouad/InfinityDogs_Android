package com.example.infinitydogss_android.ViewModels

// File: com/example/infinitydogss_android/viewmodel/DogViewModel.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infinitydogss_android.network.api.Dog
import com.example.infinitydogss_android.network.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Define possible UI states for the dog image
sealed class DogUiState {
    object Loading : DogUiState()
    data class Success(val dog: Dog) : DogUiState()
    data class Error(val message: String) : DogUiState()
}

class DogViewModel : ViewModel() {

    // Private mutable state that holds the current UI state.
    private val _uiState = MutableStateFlow<DogUiState>(DogUiState.Loading)
    // Expose an immutable StateFlow for observers.
    val uiState: StateFlow<DogUiState> = _uiState

    init {
        fetchDogImage()
    }

    fun fetchDogImage() {
        // Launch a coroutine in viewModelScope so it runs off the main thread.
        viewModelScope.launch {
            _uiState.value = DogUiState.Loading
            try {
                // Call the suspend function defined in DogAPICallable.
                val dog = RetrofitClient.apiService.getImage()
                _uiState.value = DogUiState.Success(dog)
            } catch (e: Exception) {
                _uiState.value = DogUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
