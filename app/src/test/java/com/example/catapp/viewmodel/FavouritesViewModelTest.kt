package com.example.catapp.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FavoritesViewModelTest {

    private lateinit var viewModel: FavoritesViewModel

    @Before
    fun setup() {
        viewModel = FavoritesViewModel()
    }

    @Test
    fun toggleFavorite_addsIdIfNotPresent() {
        val id = "123"
        assertFalse(viewModel.isFavorite(id))

        viewModel.toggleFavorite(id)

        assertTrue(viewModel.isFavorite(id))
        assertEquals(1, viewModel.favoriteIds.size)
        assertEquals(id, viewModel.favoriteIds[0])
    }

    @Test
    fun toggleFavorite_removesIdIfPresent() {
        val id = "123"
        viewModel.toggleFavorite(id)
        assertTrue(viewModel.isFavorite(id))

        viewModel.toggleFavorite(id)
        assertFalse(viewModel.isFavorite(id))
        assertEquals(0, viewModel.favoriteIds.size)
    }

    @Test
    fun isFavorite_returnsCorrectValue() {
        val id1 = "123"
        val id2 = "456"

        viewModel.toggleFavorite(id1)

        assertTrue(viewModel.isFavorite(id1))
        assertFalse(viewModel.isFavorite(id2))
    }

    @Test
    fun toggleFavorite_addsMultipleIds() {
        val ids = listOf("1", "2", "3")
        ids.forEach { viewModel.toggleFavorite(it) }

        assertEquals(3, viewModel.favoriteIds.size)
        assertTrue(viewModel.favoriteIds.containsAll(ids))
    }

    @Test
    fun toggleFavorite_removesOneFromMultiple() {
        val ids = listOf("1", "2", "3")
        ids.forEach { viewModel.toggleFavorite(it) }

        viewModel.toggleFavorite("2")

        assertEquals(2, viewModel.favoriteIds.size)
        assertTrue(viewModel.favoriteIds.containsAll(listOf("1", "3")))
        assertFalse(viewModel.favoriteIds.contains("2"))
    }

    @Test
    fun toggleFavorite_doesNotDuplicateIds() {
        val id = "1"
        viewModel.toggleFavorite(id)
        viewModel.toggleFavorite(id)
        viewModel.toggleFavorite(id)

        assertEquals(1, viewModel.favoriteIds.size)
        assertEquals(id, viewModel.favoriteIds[0])
    }

    @Test
    fun toggleFavorite_handlesEmptyString() {
        val id = ""
        viewModel.toggleFavorite(id)
        assertTrue(viewModel.isFavorite(id))

        viewModel.toggleFavorite(id)
        assertFalse(viewModel.isFavorite(id))
    }

    @Test
    fun toggleFavorite_complexSequence() {
        viewModel.toggleFavorite("a")
        viewModel.toggleFavorite("b")
        viewModel.toggleFavorite("a")
        viewModel.toggleFavorite("c")

        assertEquals(listOf("b", "c"), viewModel.favoriteIds)
    }
}
