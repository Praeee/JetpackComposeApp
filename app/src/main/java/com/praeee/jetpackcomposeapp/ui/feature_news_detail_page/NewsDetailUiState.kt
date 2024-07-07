package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState

data class NewsDetailUiState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val article: ArticleUiState? = null,
)