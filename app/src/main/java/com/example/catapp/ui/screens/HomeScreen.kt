package com.example.catapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items // <- Import fundamental para items() na LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.catapp.networking.model.Breed
import com.example.catapp.viewmodel.FavoritesViewModel
import com.example.catapp.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavController
) {
    val breeds by homeScreenViewModel.breeds.collectAsState()
    val isLoading by homeScreenViewModel.isLoading.collectAsState()
    val errorMessage by homeScreenViewModel.errorMessage.collectAsState()

    when {
        isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Loading breeds...")
            }
        }
        errorMessage != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage ?: "Unknown error")
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
                items(breeds) { breed ->
                    BreedCard(
                        breed = breed,
                        favoritesViewModel = favoritesViewModel,
                        onClick = { navController.navigate("breedDetails/${breed.id}") }
                    )
                }
            }
        }
    }
}

@Composable
fun BreedCard(
    breed: Breed,
    favoritesViewModel: FavoritesViewModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageUrl = breed.reference_image_id?.let {
                    "https://cdn2.thecatapi.com/images/$it.jpg"
                }

                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = breed.name ?: "Cat image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No image")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = breed.name ?: "Unknown",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            val isFav = favoritesViewModel.isFavorite(breed.id)

            IconToggleButton(
                checked = isFav,
                onCheckedChange = { favoritesViewModel.toggleFavorite(breed.id) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                if (isFav) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Favorite",
                        tint = Color.Yellow
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = "Not favorite",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}
