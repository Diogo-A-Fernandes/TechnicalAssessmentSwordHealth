package com.example.catapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.catapp.ui.components.CustomTopBar
import com.example.catapp.viewmodel.FavoritesViewModel
import com.example.catapp.viewmodel.HomeScreenViewModel

@Composable
fun CatDetailsScreen(
    breedId: String,
    homeScreenViewModel: HomeScreenViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavController
) {
    val breeds by homeScreenViewModel.breeds.collectAsState()
    val isLoading by homeScreenViewModel.isLoading.collectAsState()
    val errorMessage by homeScreenViewModel.errorMessage.collectAsState()
    val breed = breeds.find { it.id == breedId }
    val isFav = breed?.let { favoritesViewModel.isFavorite(it.id) } ?: false

    Scaffold(
        topBar = {
            CustomTopBar(
                title = breed?.name ?: "Details",
                onBack = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        when {
            isLoading -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Loading breed details...")
                }
            }

            errorMessage != null -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(errorMessage ?: "Unknown error")
                }
            }

            breed == null -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Breed not found")
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    breed.reference_image_id?.let {
                        AsyncImage(
                            model = "https://cdn2.thecatapi.com/images/$it.jpg",
                            contentDescription = breed.name ?: "Cat image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = breed.name ?: "Unknown",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Origin: ${breed.origin ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Temperament: ${breed.temperament ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = breed.description ?: "No description available",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = { breed.id.let { favoritesViewModel.toggleFavorite(it) } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFav) Color(0xFFFFC107) else Color.Gray
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = if (isFav) Icons.Filled.Star else Icons.Outlined.StarOutline,
                            contentDescription = if (isFav) "Remove from favorites" else "Add to favorites",
                            tint = Color.Black
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(if (isFav) "Remove from favourites" else "Add to favourites")
                    }
                }
            }
        }
    }
}
