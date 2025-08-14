package com.example.catapp.ui.screens

import androidx.compose.runtime.Composable
import com.example.catapp.viewmodel.FavouritesViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catapp.ui.components.BreedCard
import com.example.catapp.viewmodel.HomeScreenViewModel
import androidx.compose.foundation.lazy.grid.items

@Composable
fun FavouritesScreen(
    favouritesViewModel: FavouritesViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {
    val breeds by homeScreenViewModel.breeds.collectAsState()

    val favourites = favouritesViewModel.getFavouriteBreeds(breeds)

    if (favourites.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No favourites yet")
        }
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(favourites) { breed ->
            val breedId = breed.id!!
            BreedCard(
                breed = breed,
                favouritesViewModel = favouritesViewModel,
                showLifeSpan = true,
                onClick = { navController.navigate("breedDetails/$breedId") }
            )
        }
    }
}
