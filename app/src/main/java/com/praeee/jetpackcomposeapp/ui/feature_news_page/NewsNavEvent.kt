package com.praeee.jetpackcomposeapp.ui.feature_news_page

sealed class NewsNavEvent {
    data class OnNavigateToArticle(val article: ArticleUiState) : NewsNavEvent()

}
