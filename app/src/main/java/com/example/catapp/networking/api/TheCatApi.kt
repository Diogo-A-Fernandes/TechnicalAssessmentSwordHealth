package com.example.catapp.networking.api

import com.example.catapp.networking.model.Breed
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCatApi {

    @GET("breeds")
    suspend fun getBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Breed>
}