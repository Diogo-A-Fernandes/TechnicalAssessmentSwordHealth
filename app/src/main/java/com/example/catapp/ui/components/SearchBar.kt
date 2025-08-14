package com.example.catapp.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun SearchBar(
    query: String, // Holds the current value typed in the search bar
    onQueryChanged: (String) -> Unit, // Callback function triggered whenever the text changes
) {
    OutlinedTextField(
        value = query, // Binds the current input text to the text field
        onValueChange = onQueryChanged, // Updates the query state on each text change
        placeholder = { Text("Search...") }, // Displays this text when the field is empty
        modifier = Modifier
            .fillMaxWidth() // Makes the text field expand to fill the full width of the screen
            .padding(16.dp), // Adds 16dp padding around the text field
        singleLine = true, // Ensures the text field only allows a single line of input
        shape = RoundedCornerShape(12.dp) // Applies rounded corners to the text field
    )
}
