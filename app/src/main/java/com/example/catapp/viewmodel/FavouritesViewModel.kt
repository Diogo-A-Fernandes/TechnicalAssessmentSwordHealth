package com.example.catapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {

    private val _favoriteIds = mutableStateListOf<Int>()
    val favoriteIds: List<Int> get() = _favoriteIds

    fun toggleFavorite(id: Int) {
        if (_favoriteIds.contains(id)) {
            _favoriteIds.remove(id)
        } else {
            _favoriteIds.add(id)
        }
    }

    fun isFavorite(id: Int): Boolean = _favoriteIds.contains(id)
}
