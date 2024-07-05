package com.praeee.jetpackcomposeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.praeee.jetpackcomposeapp.ui.feature_home_page.HomeNavEvent
import com.praeee.jetpackcomposeapp.ui.feature_home_page.HomeScreen
import com.praeee.jetpackcomposeapp.ui.feature_home_page.homeScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsNavEvent
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_page.navigateToNewsScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_page.newsScreen
import com.praeee.jetpackcomposeapp.ui.navigation.Routes.homeNavigationRoute
import com.praeee.jetpackcomposeapp.ui.navigation.Routes.newsNavigationRoute

@Composable
fun JetPackComposeApp() {
    val navController = rememberNavController()
    AppNavigationGraph(
        navController = navController
    )
}

@Composable
fun AppNavigationGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = homeNavigationRoute
    ) {
        homeScreen(
            navEvent = HomeNavEvent(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToNews = {
                    navController.navigateToNewsScreen()
                }
            )
        )
        newsScreen(
            navEvent = NewsNavEvent(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToNewsDetail = {

                }
            )
        )
    }


}