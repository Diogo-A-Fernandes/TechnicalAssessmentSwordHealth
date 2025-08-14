package com.example.catapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.catapp.networking.model.Breed
import com.example.catapp.ui.utils.lifeSpanFormatter
import com.example.catapp.viewmodel.FavouritesViewModel

@Composable
fun BreedCard(
    breed: Breed,
    favouritesViewModel: FavouritesViewModel,
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
            breed.id?.let { safeId ->
                val isFav = favouritesViewModel.isFavourite(safeId)
                IconToggleButton(
                    checked = isFav,
                    onCheckedChange = { favouritesViewModel.toggleFavourite(safeId) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (isFav) Icons.Filled.Star else Icons.Outlined.StarOutline,
                        contentDescription = if (isFav) "Favorite" else "Not favorite",
                        tint = if (isFav) Color(0xFFFFD700) else Color.Gray
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = breed.reference_image_id?.let { "https://cdn2.thecatapi.com/images/$it.jpg" },
                    contentDescription = breed.name ?: "Cat image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

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
        }
    }
}