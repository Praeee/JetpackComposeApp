package com.praeee.jetpackcomposeapp.ui.feature_news_page

sealed class NewsEvent {
    data object PullToRefresh : NewsEvent()
    data object OnErrorUi: NewsEvent()
    data class OnClickItemArticle(val item: ArticleUiState) : NewsEvent()


}