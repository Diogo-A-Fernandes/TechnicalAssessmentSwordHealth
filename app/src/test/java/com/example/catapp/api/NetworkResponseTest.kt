package com.example.catapp.api

import com.example.catapp.networking.api.NetworkResponse
import org.junit.Assert.*
import org.junit.Test

class NetworkResponseTest {

    @Test
    fun `Success should store and return correct data`() {
        val data = listOf("cat1", "cat2")
        val response = NetworkResponse.Success(data)

        assertTrue(response is NetworkResponse.Success)
        assertEquals(data, response.data)
    }

    @Test
    fun `Error should store and return correct message`() {
        val message = "Something went wrong"
        val response = NetworkResponse.Error(message)

        assertTrue(response is NetworkResponse.Error)
        assertEquals(message, response.message)
    }

    @Test
    fun `Loading should be the same instance`() {
        val loading1 = NetworkResponse.Loading
        val loading2 = NetworkResponse.Loading

        assertSame(loading1, loading2)
    }

    @Test
    fun `Different Success instances with same data should be equal`() {
        val data = "Cat data"
        val r1 = NetworkResponse.Success(data)
        val r2 = NetworkResponse.Success(data)

        assertEquals(r1, r2)
        assertEquals(r1.hashCode(), r2.hashCode())
    }

    @Test
    fun `Different Error instances with same message should be equal`() {
        val msg = "Error message"
        val e1 = NetworkResponse.Error(msg)
        val e2 = NetworkResponse.Error(msg)

        assertEquals(e1, e2)
        assertEquals(e1.hashCode(), e2.hashCode())
    }
}
