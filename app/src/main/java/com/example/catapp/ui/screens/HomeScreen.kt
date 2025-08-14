package com.example.catapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catapp.ui.components.BreedCard
import com.example.catapp.viewmodel.FavoritesViewModel
import com.example.catapp.viewmodel.HomeScreenViewModel
import com.example.catapp.ui.components.SearchBar

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavController
) {
    val breeds by homeScreenViewModel.breeds.collectAsState()
    val isLoading by homeScreenViewModel.isLoading.collectAsState()
    val errorMessage by homeScreenViewModel.errorMessage.collectAsState()

    var query by remember { mutableStateOf("") }

    val filteredBreeds = breeds.filter { breed ->
        breed.name?.contains(query, ignoreCase = true) == true
    }

    Column(modifier = Modifier.fillMaxSize()) {

        SearchBar(
            query = query,
            onQueryChanged = { query = it }
        )

        when {
            errorMessage != null -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = errorMessage ?: "Unknown error")
                }
            }

            breeds.isEmpty() && isLoading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(filteredBreeds) { index, breed ->

                        BreedCard(
                            breed = breed,
                            favoritesViewModel = favoritesViewModel,
                            showLifeSpan = false,
                            onClick = {
                                navController.navigate("breedDetails/${breed.id}")
                            }
                        )

                        if (index >= filteredBreeds.size - 4) {
                            LaunchedEffect(Unit) {
                                homeScreenViewModel.fetchBreedsIfNeeded()
                            }
                        }
                    }

                    if (isLoading) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}
