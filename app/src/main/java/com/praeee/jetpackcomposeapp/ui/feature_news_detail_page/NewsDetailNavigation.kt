package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.gson.Gson
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.navigation.Routes
import com.praeee.jetpackcomposeapp.ui.sharedviewmodel.NewsSharedViewModel

fun NavController.navigateToNewsDetailScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = Routes.newsDetailNavigationRoute,
        navOptions
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.newsDetailScreen(
    newsSharedViewModel: NewsSharedViewModel,
    navEvent: (NewsDetailNavEvent) -> Unit = {},
) {
    composable(
        route = Routes.newsDetailNavigationRoute,
    ) {
        NewsDetailRoute(
            newsSharedViewModel = newsSharedViewModel,
            navEvent = navEvent
        )
    }
}