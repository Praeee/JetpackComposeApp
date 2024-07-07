package com.praeee.jetpackcomposeapp.ui.feature_news_page

data class NewsUiState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val articleList: ArticleListUiState? = null,
)

data class ArticleListUiState (
    val articleList: List<ArticleUiState>? = emptyList()
)

data class ArticleUiState (
    val author : String?="",
    val title : String?="",
    val description : String?="",
    val url : String?="",
    val urlToImage : String?="",
    val publishedAt : String?="",
    val content : String?="",
    val source : SourceUiState? = null,
)

data class SourceUiState(
    val id: String?="",
    val name: String?="",
)