package com.praeee.jetpackcomposeapp.ui.feature_news_page

data class NewsNavEvent(
    val onNavigateBack: () -> Unit = {},
    val onNavigateToNewsDetail: (ArticleUiState) -> Unit = {},
)
