package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState

sealed class NewsDetailEvent {
    data object PullToRefresh : NewsDetailEvent()
    data class SetArticleDetail(val article: ArticleUiState) : NewsDetailEvent()


}