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
    suspend fun getBreeds(page: Int, limit: Int = 10): List<Breed> {
        val cachedBreeds = dao.getAllCatsList()

        return if (cachedBreeds.isNotEmpty() && page == 0) {
            cachedBreeds.map { it.toBreed() }
        } else {
            val breedsFromApi = api.getBreeds(page, limit)
            dao.insertAll(breedsFromApi.map { it.toEntity() })
            breedsFromApi
        }
    }
}
