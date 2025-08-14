package com.example.catapp.api

import com.example.catapp.networking.api.TheCatApi
import com.example.catapp.networking.model.Breed
import com.example.catapp.networking.model.Weight
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TheCatApiTest {

    private lateinit var api: TheCatApi

    private fun mockBreed(id: String, name: String) = Breed(
        weight = Weight("3", "1.5"),
        id = id,
        name = name,
        cfa_url = null,
        vetstreet_url = null,
        vcahospitals_url = null,
        temperament = null,
        origin = null,
        country_codes = null,
        country_code = null,
        description = null,
        life_span = null,
        indoor = null,
        lap = null,
        alt_names = null,
        adaptability = null,
        affection_level = null,
        child_friendly = null,
        dog_friendly = null,
        energy_level = null,
        grooming = null,
        health_issues = null,
        intelligence = null,
        shedding_level = null,
        social_needs = null,
        stranger_friendly = null,
        vocalisation = null,
        experimental = null,
        hairless = null,
        natural = null,
        rare = null,
        rex = null,
        suppressed_tail = null,
        short_legs = null,
        wikipedia_url = null,
        hypoallergenic = null,
        reference_image_id = null
    )

    @Before
    fun setup() {
        api = mockk()
    }

    @Test
    fun `getBreeds returns list of breeds`() = runBlocking {
        // Mock da resposta
        val mockBreeds = listOf(
            mockBreed(id = "1", name = "Siamese"),
            mockBreed(id = "2", name = "Persian")
        )

        coEvery { api.getBreeds(page = 1, limit = 10) } returns mockBreeds

        val result = api.getBreeds(page = 1, limit = 10)

        assertEquals(2, result.size)
        assertEquals("Siamese", result[0].name)
        assertEquals("Persian", result[1].name)
    }

    @Test
    fun `getBreeds returns empty list when no breeds`() = runBlocking {
        coEvery { api.getBreeds(page = 1, limit = 10) } returns emptyList()

        val result = api.getBreeds(page = 1, limit = 10)

        assertEquals(0, result.size)
    }
}
