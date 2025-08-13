package com.example.catapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomTopBar(title: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Make the top bar span the full width of the screen
            .height(56.dp) // Set a fixed height for the top bar
            .background(MaterialTheme.colorScheme.surface) // Use the surface color from the theme as background
            .padding(horizontal = 8.dp), // Add horizontal padding inside the bar
        contentAlignment = Alignment.Center // Center the content inside the box
    ) {
        // Display the screen title centered in the top bar
        Text(
            text = title, // The title text to show
            style = MaterialTheme.typography.titleLarge, // Use a large title text style from the theme
            fontWeight = FontWeight.Bold, // Make the title text bold
            color = MaterialTheme.colorScheme.onSurfaceVariant // Color
        )

        // Back button icon aligned to the start (left) of the top bar
        IconButton(
            onClick = onBack, // Trigger the passed callback when the back button is clicked
            modifier = Modifier.align(Alignment.CenterStart) // Position the button at the start of the bar
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Automatically mirrored arrow icon for RTL support
                contentDescription = "Back", // Accessibility description for screen readers
                tint = MaterialTheme.colorScheme.onSurfaceVariant // Match the icon color to the text color
            )
        }
    }
}
