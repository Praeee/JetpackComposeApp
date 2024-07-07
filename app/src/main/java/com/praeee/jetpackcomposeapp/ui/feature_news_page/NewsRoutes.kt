package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsRoute(
    navEvent: (NewsNavEvent) -> Unit,
) {
    NewsScreen(
        navEvent = navEvent
    )
}