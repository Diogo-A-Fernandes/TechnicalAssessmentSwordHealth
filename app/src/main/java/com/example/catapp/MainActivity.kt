package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.catapp.data.database.DatabaseProvider
import com.example.catapp.ui.navigation.NavGraph
import com.example.catapp.ui.theme.CatAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// MainActivity is the entry point of the app, hosting the Compose UI content
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display, so content can draw behind system bars for immersive UI
        enableEdgeToEdge()

        CoroutineScope(Dispatchers.IO).launch {
            val db = DatabaseProvider.getDatabase(this@MainActivity)
            db.clearAllTables()
        }

        // Set the content view with Jetpack Compose UI
      setContent {
            // Apply the custom theme for the app
            CatAppTheme {
                // Launch the navigation graph which handles navigation and screen logic
                NavGraph() // Here we call the navigation bar and all associated logic
            }
        }
    }
}
