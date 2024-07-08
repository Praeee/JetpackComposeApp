package com.praeee.jetpackcomposeapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.praeee.jetpackcomposeapp.ui.feature_home_page.HomeNavEvent
import com.praeee.jetpackcomposeapp.ui.feature_home_page.homeScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.NewsDetailNavEvent
import com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.navigateToNewsDetailScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.newsDetailScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsNavEvent
import com.praeee.jetpackcomposeapp.ui.feature_news_page.navigateToNewsScreen
import com.praeee.jetpackcomposeapp.ui.feature_news_page.newsScreen
import com.praeee.jetpackcomposeapp.ui.navigation.Routes.homeNavigationRoute
import com.praeee.jetpackcomposeapp.ui.sharedviewmodel.NewsSharedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JetPackComposeApp() {
    val navController = rememberNavController()
    AppNavigationGraph(
        navController = navController
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigationGraph(
    navController: NavHostController
) {

    val newsSharedViewModel = hiltViewModel<NewsSharedViewModel>()

    NavHost(
        navController = navController,
        startDestination = homeNavigationRoute
    ) {
        homeScreen(
            navEvent = HomeNavEvent(
                onNavigateBack = {
//                    navController.popBackStack()
                },
                onNavigateToNews = {
                    navController.navigateToNewsScreen()
                }
            )
        )
        newsScreen(
            newsSharedViewModel = newsSharedViewModel
        ) {
            when (it) {
                is NewsNavEvent.OnNavigateToArticle -> {
                    newsSharedViewModel.setCurrentArticleModel(it.article)
                    navController.navigateToNewsDetailScreen()
                }
                is NewsNavEvent.OnNavigateUp -> {
                    navController.popBackStack()
                }
            }
        }
        newsDetailScreen(
            newsSharedViewModel = newsSharedViewModel
        ) {

            when (it) {
                is NewsDetailNavEvent.OnNavigateUp -> {
                    navController.popBackStack()
                }
            }

        }
    }


}