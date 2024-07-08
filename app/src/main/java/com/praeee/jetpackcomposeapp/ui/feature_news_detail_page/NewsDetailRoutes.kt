package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsNavEvent
import com.praeee.jetpackcomposeapp.ui.sharedviewmodel.NewsSharedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsDetailRoute(
    newsSharedViewModel: NewsSharedViewModel,
    navEvent: (NewsDetailNavEvent) -> Unit = {},
) {
    NewsDetailScreen(
        newsSharedViewModel = newsSharedViewModel,
        navEvent = navEvent
    )
}