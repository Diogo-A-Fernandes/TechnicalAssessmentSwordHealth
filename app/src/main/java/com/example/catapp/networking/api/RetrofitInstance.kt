package com.example.catapp.networking.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object responsible for creating and providing
 * the Retrofit instance to interact with the Rick and Morty API.
 */
object RetrofitInstance {

    // Base URL for the Rick and Morty API
    private const val baseUrl = " https://api.thecatapi.com/v1/"

    /**
     * Creates and configures the Retrofit instance.
     * - Sets the base URL for all requests
     * - Adds a Gson converter to handle JSON serialization/deserialization
     */
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides a singleton instance of the API interface
     * so it can be used throughout the app for network requests.
     */
    val theCatApi: TheCatApi = getInstance().create(TheCatApi::class.java)
}
