package com.example.catapp.ui.screens

import androidx.compose.runtime.Composable
import com.example.catapp.viewmodel.FavoritesViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

data class Item(
    val id: String, // mudou para String
    val name: String
)

@Composable
fun FavouritesScreen(
    favoritesViewModel: FavoritesViewModel, navController: NavController
) {
    // Criar lista de itens com id como String
    val allItems = List(20) { Item(it.toString(), "Item #$it") }

    // Filtrar favoritos com id String
    val favorites = allItems.filter { favoritesViewModel.isFavorite(it.id) }

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
                        checked = true, // todos aqui são favoritos
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
        }
    }
}
