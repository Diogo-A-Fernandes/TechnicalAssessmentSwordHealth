package com.example.catapp.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FavoritesViewModelTest {

    private lateinit var viewModel: FavouritesViewModel

    @Before
    fun setup() {
        viewModel = FavouritesViewModel()
    }

    @Test
    fun toggleFavorite_addsIdIfNotPresent() {
        val id = "123"
        assertFalse(viewModel.isFavourite(id))

        viewModel.toggleFavourite(id)

        assertTrue(viewModel.isFavourite(id))
        assertEquals(1, viewModel.favouriteIds.size)
        assertEquals(id, viewModel.favouriteIds[0])
    }

    @Test
    fun toggleFavorite_removesIdIfPresent() {
        val id = "123"
        viewModel.toggleFavourite(id)
        assertTrue(viewModel.isFavourite(id))

        viewModel.toggleFavourite(id)
        assertFalse(viewModel.isFavourite(id))
        assertEquals(0, viewModel.favouriteIds.size)
    }

    @Test
    fun isFavorite_returnsCorrectValue() {
        val id1 = "123"
        val id2 = "456"

        viewModel.toggleFavourite(id1)

        assertTrue(viewModel.isFavourite(id1))
        assertFalse(viewModel.isFavourite(id2))
    }

    @Test
    fun toggleFavorite_addsMultipleIds() {
        val ids = listOf("1", "2", "3")
        ids.forEach { viewModel.toggleFavourite(it) }

        assertEquals(3, viewModel.favouriteIds.size)
        assertTrue(viewModel.favouriteIds.containsAll(ids))
    }

    @Test
    fun toggleFavorite_removesOneFromMultiple() {
        val ids = listOf("1", "2", "3")
        ids.forEach { viewModel.toggleFavourite(it) }

        viewModel.toggleFavourite("2")

        assertEquals(2, viewModel.favouriteIds.size)
        assertTrue(viewModel.favouriteIds.containsAll(listOf("1", "3")))
        assertFalse(viewModel.favouriteIds.contains("2"))
    }

    @Test
    fun toggleFavorite_doesNotDuplicateIds() {
        val id = "1"
        viewModel.toggleFavourite(id)
        viewModel.toggleFavourite(id)
        viewModel.toggleFavourite(id)

        assertEquals(1, viewModel.favouriteIds.size)
        assertEquals(id, viewModel.favouriteIds[0])
    }

    @Test
    fun toggleFavorite_handlesEmptyString() {
        val id = ""
        viewModel.toggleFavourite(id)
        assertTrue(viewModel.isFavourite(id))

        viewModel.toggleFavourite(id)
        assertFalse(viewModel.isFavourite(id))
    }

    @Test
    fun toggleFavorite_complexSequence() {
        viewModel.toggleFavourite("a")
        viewModel.toggleFavourite("b")
        viewModel.toggleFavourite("a")
        viewModel.toggleFavourite("c")

        assertEquals(listOf("b", "c"), viewModel.favouriteIds)
    }
}
