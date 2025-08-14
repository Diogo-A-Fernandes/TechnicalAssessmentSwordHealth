package com.example.catapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
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
import com.example.catapp.viewmodel.FavouritesViewModel
import com.example.catapp.viewmodel.HomeScreenViewModel

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Cats", Icons.Filled.Home)
    object Favourites : Screen("favourites", "Favourites", Icons.Filled.Star)
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val favouritesViewModel: FavouritesViewModel = viewModel()
    val homeScreenViewModel: HomeScreenViewModel = viewModel()
    val screens = listOf(Screen.Home, Screen.Favourites)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

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
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    favoritesViewModel = favouritesViewModel,
                    homeScreenViewModel = homeScreenViewModel,
                    navController = navController
                )
            }

            composable(Screen.Favourites.route) {
                FavouritesScreen(
                    navController = navController,
                    homeScreenViewModel = homeScreenViewModel,
                    favouritesViewModel = favouritesViewModel
                )
            }

            composable(
                "breedDetails/{breedId}",
                arguments = listOf(navArgument("breedId") { type = NavType.StringType })
            ) { backStackEntry ->
                val breedId = backStackEntry.arguments?.getString("breedId") ?: ""
                CatDetailsScreen(
                    breedId = breedId,
                    homeScreenViewModel = homeScreenViewModel,
                    favouritesViewModel = favouritesViewModel,
                    navController = navController
                )
            }
        }
    }
}
