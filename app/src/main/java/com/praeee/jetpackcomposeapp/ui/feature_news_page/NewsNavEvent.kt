package com.praeee.jetpackcomposeapp.ui.feature_news_page

import com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.NewsDetailNavEvent

sealed class NewsNavEvent {
    data class OnNavigateToArticle(val article: ArticleUiState) : NewsNavEvent()
    data object OnNavigateUp : NewsNavEvent()

}
