package com.example.catapp.data.repository

import com.example.catapp.data.dao.CatBreedDao
import com.example.catapp.data.entity.CatBreedEntity
import com.example.catapp.networking.api.TheCatApi
import com.example.catapp.networking.model.Breed
import com.example.catapp.networking.model.Weight
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CatBreedRepositoryTest {

    private lateinit var api: TheCatApi
    private lateinit var dao: CatBreedDao
    private lateinit var repository: CatBreedRepository

    @Before
    fun setup() {
        api = mock()
        dao = mock()
        repository = CatBreedRepository(api, dao)
    }

    @Test
    fun `should return cached breeds when cache is not empty and page is 0`() = runBlocking {
        val cachedEntities = listOf(
            CatBreedEntity(
                id = "id1",
                name = "Breed One",
                origin = "Portugal",
                temperament = "Calm",
                description = "A calm and friendly cat",
                lifeSpan = "12-15",
                referenceImageId = "img1"
            ),
            CatBreedEntity(
                id = "id2",
                name = "Breed Two",
                origin = "Spain",
                temperament = "Playful",
                description = "A playful and active cat",
                lifeSpan = "10-12",
                referenceImageId = "img2"
            )
        )

        whenever(dao.getAllCatsList()).thenReturn(cachedEntities)

        val result = repository.getBreeds(page = 0)

        verify(api, never()).getBreeds(any(), any())
        assertEquals(cachedEntities.size, result.size)
        assertEquals(cachedEntities.first().id, result.first().id)
    }

    @Test
    fun `should call API and save to DB when cache is empty`() = runBlocking {
        val breedsFromApi = listOf(
            Breed(
                weight = Weight("7-10", "3-5"),
                id = "id1",
                name = "Breed One",
                cfa_url = null,
                vetstreet_url = null,
                vcahospitals_url = null,
                temperament = "Calm",
                origin = "Portugal",
                country_codes = null,
                country_code = null,
                description = "A calm and friendly cat",
                life_span = "12-15",
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
                reference_image_id = "img1"
            )
        )

        whenever(dao.getAllCatsList()).thenReturn(emptyList())
        whenever(api.getBreeds(0, 10)).thenReturn(breedsFromApi)

        val result = repository.getBreeds(page = 0)

        verify(api).getBreeds(0, 10)
        verify(dao).insertAll(any())
        assertEquals(breedsFromApi, result)
    }
}
