package com.praeee.jetpackcomposeapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.praeee.jetpackcomposeapp.feature.home.HomeRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComposeApp() {
    AppNavigationGraph()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigationGraph(
    modifier: Modifier = Modifier,
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(startDestination) { HomeRoute() }
    }
}