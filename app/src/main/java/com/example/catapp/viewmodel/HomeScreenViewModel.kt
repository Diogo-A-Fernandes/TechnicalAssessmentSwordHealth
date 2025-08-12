package com.example.catapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.networking.api.RetrofitInstance
import com.example.catapp.networking.model.Breed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    // API service instance
    private val api = RetrofitInstance.theCatApi

    // State to hold the list of breeds
    private val _breeds = MutableStateFlow<List<Breed>>(emptyList())
    val breeds = _breeds.asStateFlow()

    // Loading and error states (optional)
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = api.getBreeds()
                _breeds.value = response
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error loading breeds", e)
                _errorMessage.value = "Failed to load breeds"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
