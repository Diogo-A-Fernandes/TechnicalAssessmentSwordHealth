package com.example.catapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.database.DatabaseProvider
import com.example.catapp.data.repository.CatBreedRepository
import com.example.catapp.networking.api.RetrofitInstance
import com.example.catapp.networking.model.Breed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CatBreedRepository

    private val _breeds = MutableStateFlow<List<Breed>>(emptyList())
    val breeds = _breeds.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private var currentPage = 0
    private val pageSize = 10
    private var endReached = false

    init {
        val db = DatabaseProvider.getDatabase(application)
        repository = CatBreedRepository(
            api = RetrofitInstance.theCatApi,
            dao = db.catBreedDao()
        )
        fetchBreeds()
    }

    fun fetchBreedsIfNeeded() {
        if (_isLoading.value || endReached) return
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newBreeds = repository.getBreeds(page = currentPage, limit = pageSize)
                if (newBreeds.isEmpty()) {
                    endReached = true
                } else {
                    _breeds.value = _breeds.value + newBreeds
                    currentPage++
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error loading breeds"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
