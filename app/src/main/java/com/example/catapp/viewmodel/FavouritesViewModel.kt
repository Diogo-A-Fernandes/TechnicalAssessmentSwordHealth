package com.example.catapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.catapp.networking.model.Breed

class FavouritesViewModel : ViewModel() {

    private val _favouriteIds = mutableStateListOf<String>()
    val favouriteIds: List<String> get() = _favouriteIds

    fun toggleFavourite(id: String) {
        if (_favouriteIds.contains(id)) {
            _favouriteIds.remove(id)
        } else {
            _favouriteIds.add(id)
        }
    }

    fun isFavourite(id: String): Boolean = _favouriteIds.contains(id)

    fun getFavouriteBreeds(breeds: List<Breed>): List<Breed> {
        return breeds.filter { it.id != null && isFavourite(it.id) }
    }

}