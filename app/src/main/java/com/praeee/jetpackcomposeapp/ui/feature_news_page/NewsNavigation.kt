package com.praeee.jetpackcomposeapp.ui.feature_news_page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.praeee.jetpackcomposeapp.ui.navigation.Routes

fun NavController.navigateToNewsScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = Routes.newsNavigationRoute,
        navOptions
    )
}

fun NavGraphBuilder.newsScreen(
    navEvent: NewsNavEvent,
) {
    composable(
        route = Routes.newsNavigationRoute,
    ) {
        NewsRoute(
            navEvent = navEvent
        )
    }
}