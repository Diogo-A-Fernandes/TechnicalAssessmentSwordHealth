package com.example.catapp.data.repository

import com.example.catapp.data.dao.CatBreedDao
import com.example.catapp.data.entity.toEntity
import com.example.catapp.data.entity.toBreed
import com.example.catapp.networking.api.TheCatApi
import com.example.catapp.networking.model.Breed

class CatBreedRepository(
    private val api: TheCatApi,
    private val dao: CatBreedDao
) {

    suspend fun getBreeds(): List<Breed> {
        val cachedBreeds = dao.getAllCatsList()
        return if (cachedBreeds.isNotEmpty()) {
            cachedBreeds.map { it.toBreed() }
        } else {
            val breedsFromApi = api.getBreeds()
            dao.insertAll(breedsFromApi.map { it.toEntity() })
            breedsFromApi
        }
    }
}
