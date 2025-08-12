package com.example.catapp.ui.screens

import androidx.compose.runtime.Composable
import com.example.catapp.viewmodel.FavoritesViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catapp.ui.components.BreedCard
import com.example.catapp.viewmodel.HomeScreenViewModel

data class Item(
    val id: String,
    val name: String
)

@Composable
fun FavouritesScreen(
    favoritesViewModel: FavoritesViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {
<<<<<<< Updated upstream
    val breeds by homeScreenViewModel.breeds.collectAsState()

    // Filtra só as raças que estão na lista de favoritos
    val favorites = breeds.filter { favoritesViewModel.isFavorite(it.id) }
=======
    val allItems = List(20) { Item(it.toString(), "Item #$it") }

    val favorites = allItems.filter { favoritesViewModel.isFavorite(it.id) }
>>>>>>> Stashed changes

    if (favorites.isEmpty()) {
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
<<<<<<< Updated upstream
        items(favorites) { breed ->
            BreedCard(
                breed = breed,
                favoritesViewModel = favoritesViewModel,
                onClick = {  }
            )
=======
        items(favorites) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = item.name,
                            fontSize = 18.sp,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }

                    IconToggleButton(
                        checked = true,
                        onCheckedChange = { _ ->
                            favoritesViewModel.toggleFavorite(item.id)
                        }, modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Remove from favorites",
                            tint = Color.Yellow
                        )
                    }
                }
            }
>>>>>>> Stashed changes
        }
    }
}
