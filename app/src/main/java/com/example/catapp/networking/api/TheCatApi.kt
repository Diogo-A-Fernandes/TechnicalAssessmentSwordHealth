package com.example.catapp.networking.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Retrofit API interface defining endpoints to interact with
 * the Rick and Morty API.
 */
interface RickAndMortyApi {

    //---------------------------- Characters -------------------------

    /**
     * Retrieves a paginated list of characters from the API.
     * Endpoint: GET /character
     */
    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    /**
     * Retrieves characters from a full URL (used for pagination).
     * This allows fetching the next pages using the URL provided by the API.
     *
     * @param url The full URL for the character list endpoint with pagination parameters.
     */
    @GET
    suspend fun getCharactersByUrl(@Url url: String): CharacterResponse

    /**
     * Retrieves detailed information for a specific character by their ID.
     *
     * @param characterId The ID of the character to retrieve.
     */
    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Character

    //---------------------------- Episodes -------------------------

    /**
     * Retrieves detailed information for a specific episode by its full URL.
     *
     * @param url The full URL of the episode to retrieve.
     */
    @GET
    suspend fun getEpisodeByUrl(@Url url: String): Episode
}
