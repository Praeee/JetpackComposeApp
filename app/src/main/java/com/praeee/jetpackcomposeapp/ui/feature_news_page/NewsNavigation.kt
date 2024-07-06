package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.praeee.jetpackcomposeapp.ui.navigation.Routes
import com.praeee.jetpackcomposeapp.ui.sharedviewmodel.NewsSharedViewModel

fun NavController.navigateToNewsScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = Routes.newsNavigationRoute,
        navOptions
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.newsScreen(
    newsSharedViewModel: NewsSharedViewModel,
    navigationEvent : (NewsNavEvent) -> Unit,
) {
    composable(
        route = Routes.newsNavigationRoute,
    ) {
        NewsRoute(
            navEvent = navigationEvent
        )
    }
}