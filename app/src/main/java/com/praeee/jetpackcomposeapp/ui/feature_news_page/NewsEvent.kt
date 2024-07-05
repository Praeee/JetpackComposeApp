package com.praeee.jetpackcomposeapp.ui.feature_news_page

sealed class NewsEvent {
    data object PullToRefresh : NewsEvent()
    data class OnErrorUi(val error: Boolean) : NewsEvent()
    data class OnClickItemArticle(val item: ArticleUiState) : NewsEvent()


}