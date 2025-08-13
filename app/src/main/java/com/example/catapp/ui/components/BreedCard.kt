package com.example.catapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.catapp.networking.model.Breed
import com.example.catapp.ui.utils.lifeSpanFormatter
import com.example.catapp.viewmodel.FavoritesViewModel

@Composable
fun BreedCard(
    breed: Breed,
    favoritesViewModel: FavoritesViewModel,
    onClick: () -> Unit,
    showLifeSpan: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, start = 8.dp, end = 8.dp),
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
                            .height(180.dp)
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
                    fontSize = 18.sp
                )

                if (showLifeSpan && !breed.life_span.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Life span: ${lifeSpanFormatter(breed.life_span)} years",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
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
                        tint = Color(0xFFFFD700)
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
