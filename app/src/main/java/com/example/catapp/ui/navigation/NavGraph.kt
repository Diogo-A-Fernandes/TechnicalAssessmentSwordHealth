package com.example.catapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.catapp.ui.screens.CatDetailsScreen
import com.example.catapp.ui.screens.FavouritesScreen
import com.example.catapp.ui.screens.HomeScreen
import com.example.catapp.viewmodel.FavoritesViewModel
import com.example.catapp.viewmodel.HomeScreenViewModel

// Define the screens for navigation
sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Favourites : Screen("favourites", "Favourites", Icons.Filled.Person)
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()
    val homeScreenViewModel: HomeScreenViewModel = viewModel()
    val screens = listOf(Screen.Home, Screen.Favourites)

    // Scaffold with bottom navigation bar
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Render each bottom nav item
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost manages screen navigation
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Home screen route
            composable(Screen.Home.route) {
                HomeScreen(
                    favoritesViewModel = favoritesViewModel,
                    homeScreenViewModel = homeScreenViewModel,
                    navController = navController
                )
            }

            // Favourites list screen route
            composable(Screen.Favourites.route) {
                FavouritesScreen(
                    navController = navController,
                    homeScreenViewModel = homeScreenViewModel,
                    favoritesViewModel = favoritesViewModel
                )
            }

            // Breed details route
            composable(
                "breedDetails/{breedId}",
                arguments = listOf(navArgument("breedId") { type = NavType.StringType })
            ) { backStackEntry ->
                val breedId = backStackEntry.arguments?.getString("breedId") ?: ""
                CatDetailsScreen(
                    breedId = breedId,
                    homeScreenViewModel = homeScreenViewModel,
                    favoritesViewModel = favoritesViewModel,
                    navController = navController
                )
            }
        }
    }
}

