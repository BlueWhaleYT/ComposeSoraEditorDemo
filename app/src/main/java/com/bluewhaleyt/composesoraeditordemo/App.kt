package com.bluewhaleyt.composesoraeditordemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluewhaleyt.composesoraeditordemo.ui.screen.MainScreen
import com.bluewhaleyt.composesoraeditordemo.ui.screen.SettingsScreen

enum class ScreenRoute {
    Main, Settings
}

@Composable
fun App() {
    ScreenNavController()
}

@Composable
fun ScreenNavController() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Main.name
    ) {
        composable(ScreenRoute.Main.name) { MainScreen(navController) }
        composable(ScreenRoute.Settings.name) { SettingsScreen(navController) }
    }
}