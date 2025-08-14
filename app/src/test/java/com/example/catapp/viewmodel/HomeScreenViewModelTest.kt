package com.example.catapp.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catapp.data.repository.CatBreedRepository
import com.example.catapp.networking.model.Breed
import com.example.catapp.networking.model.Weight
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: CatBreedRepository
    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        val application = mockk<Application>(relaxed = true)
        viewModel = HomeScreenViewModel(application)

        repository = mockk()
        val repoField = HomeScreenViewModel::class.java.getDeclaredField("repository")
        repoField.isAccessible = true
        repoField.set(viewModel, repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

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

    @Test
    fun fetchBreedsAppendsNewBreeds() = runTest {
        val mockBreeds = listOf(mockBreed("1", "Breed1"), mockBreed("2", "Breed2"))

        coEvery { repository.getBreeds(page = 0, limit = 10) } returns mockBreeds

        // Antes de fetch
        assertEquals(0, viewModel.breeds.value.size)
        assertEquals(false, viewModel.isLoading.value)

        viewModel.fetchBreedsIfNeeded()

        testDispatcher.scheduler.advanceUntilIdle()

        // Depois de fetch
        assertEquals(2, viewModel.breeds.value.size)
        assertEquals("Breed1", viewModel.breeds.value[0].name)
        assertEquals(false, viewModel.isLoading.value)
    }


    @Test
    fun fetchBreedsSetsEndReachedWhenEmptyResponse() = runTest {
        coEvery { repository.getBreeds(page = 0, limit = 10) } returns emptyList()

        viewModel.fetchBreedsIfNeeded()
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.breeds.value.isEmpty())

        viewModel.fetchBreedsIfNeeded()
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.breeds.value.isEmpty())
    }

    @Test
    fun fetchBreedsSetsErrorMessageOnException() = runTest {
        coEvery { repository.getBreeds(page = 0, limit = 10) } throws Exception("Network error")

        viewModel.fetchBreedsIfNeeded()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals("Error loading breeds", viewModel.errorMessage.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}
