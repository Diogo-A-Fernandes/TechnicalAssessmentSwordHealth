package com.example.catapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {

    private val _favoriteIds = mutableStateListOf<String>()
    val favoriteIds: List<String> get() = _favoriteIds

    fun toggleFavorite(id: String) {
        if (_favoriteIds.contains(id)) {
            _favoriteIds.remove(id)
        } else {
            _favoriteIds.add(id)
        }
    }

    fun isFavorite(id: String): Boolean = _favoriteIds.contains(id)
}