package com.praeee.jetpackcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.praeee.jetpackcomposeapp.ui.navigation.Routes

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(
        navController = navController,
        startDestination = Routes.homeNavigationRoute
    ) {
        navigationProvider.coinApi.registerGraph(
            navController = navController,
            navGraphBuilder = this
        )

    }
}