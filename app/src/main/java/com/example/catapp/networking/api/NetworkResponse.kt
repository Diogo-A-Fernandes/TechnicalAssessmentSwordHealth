package com.example.catapp.networking.api

/**
 * Represents the state of a network response.
 * It can be either a successful response with data,
 * an error with a message, or a loading state.
 */
sealed class NetworkResponse<out T> {

    /**
     * Success case containing the expected data.
     */
    data class Success<out T>(val data: T) : NetworkResponse<T>()

    /**
     * Error case containing an error message.
     */
    data class Error(val message: String) : NetworkResponse<Nothing>()

    /**
     * Loading state indicating the request is in progress.
     */
    object Loading : NetworkResponse<Nothing>()
}