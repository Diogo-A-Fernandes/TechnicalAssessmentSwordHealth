package com.example.catapp.networking.api

import com.example.catapp.networking.model.Breed
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface defining endpoints to interact with
 * the Rick and Morty API.
 */
interface TheCatApi {

    //---------------------------- Cats -------------------------

    /**
     * Retrieves a paginated list of characters from the API.
     * Endpoint: GET /character
     */
    @GET("breeds")
    suspend fun getBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<Breed>

}