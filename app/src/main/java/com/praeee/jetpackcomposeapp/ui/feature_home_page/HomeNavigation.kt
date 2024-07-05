package com.praeee.jetpackcomposeapp.ui.feature_home_page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.praeee.jetpackcomposeapp.ui.navigation.Routes.homeNavigationRoute

fun NavController.navigateToHomeScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = homeNavigationRoute,
        navOptions
    )
}

fun NavGraphBuilder.homeScreen(
    navEvent: HomeNavEvent,
) {
    composable(route = homeNavigationRoute) {
        HomeRoute(navEvent = navEvent)
    }
}