package com.locationBasedApplication.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.locationBasedApplication.ui.main.AddLocationScreen
import com.locationBasedApplication.ui.main.LocationListScreen
import com.locationBasedApplication.ui.main.MapPreviewScreen
import com.locationBasedApplication.ui.main.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object List : Screen("location_list")
    object Add : Screen("add_location")
    object Map : Screen("map_preview")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    // Navigate to the location list screen after the splash delay.
                    navController.navigate(Screen.List.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.List.route) {
            LocationListScreen(
                onAddClicked = { navController.navigate(Screen.Add.route) },
                onMapClicked = { navController.navigate(Screen.Map.route) }
            )
        }
        composable(Screen.Add.route) {
            AddLocationScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Map.route) {
            MapPreviewScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
