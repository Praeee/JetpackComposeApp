package com.praeee.jetpackcomposeapp.ui.feature_news_page

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsRoute(
    navEvent: NewsNavEvent,
) {
    NewsScreen(
        navEvent = navEvent
    )
}