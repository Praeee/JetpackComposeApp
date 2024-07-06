package com.praeee.jetpackcomposeapp.ui.feature_news_page

import androidx.compose.runtime.Composable

@Composable
fun NewsRoute(
    navEvent: NewsNavEvent,
) {
    NewsScreen(
        navEvent = navEvent
    )
}