package com.praeee.jetpackcomposeapp.data.entity.news_response

import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleListUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.SourceUiState

data class NewsResponse(
    val status : String?="",
    val totalResults : Int?=0,
    val articles: List<Article> ?= emptyList()
)

data class Article (
    val author : String?="",
    val title : String?="",
    val description : String?="",
    val url : String?="",
    val urlToImage : String?="",
    val publishedAt : String?="",
    val content : String?="",
    val source : Source
)

data class Source(
    val id: String?="",
    val name: String?="",
)


fun NewsResponse.toArticleListUiState() : ArticleListUiState {
    return ArticleListUiState(
        articleList = this.articles?.map { it.toArticleUiState() }
    )
}

fun Article.toArticleUiState() : ArticleUiState {
    return ArticleUiState(
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
        source = this.source.toSourceUiState()
    )
}

fun Source.toSourceUiState() : SourceUiState {
    return SourceUiState(
        id = this.id,
        name = this.name
    )
}