package com.praeee.jetpackcomposeapp.data.entity.news_response

import android.os.Build
import androidx.annotation.RequiresApi
import com.praeee.jetpackcomposeapp.data.AppConstants.DATE_TIME_FORMAT
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleListUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.SourceUiState
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

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
    val source : Source ?= null
)

data class Source(
    val id: String?="",
    val name: String?="",
)


@RequiresApi(Build.VERSION_CODES.O)
fun NewsResponse.toArticleListUiState() : ArticleListUiState {
    return ArticleListUiState(
        articleList = this.articles?.map { it.toArticleUiState() }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Article.toArticleUiState() : ArticleUiState {
    return ArticleUiState(
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = timeUtcToFormat(time = this.publishedAt, format = DATE_TIME_FORMAT),
        content = this.content,
        source = this.source?.toSourceUiState()
    )
}

fun Source.toSourceUiState() : SourceUiState {
    return SourceUiState(
        id = this.id,
        name = this.name
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun timeUtcToFormat(time : String?, format : String) : String {
    // parse it to a LocalDateTime (date & time without zone or offset)
    val offsetDateTime: OffsetDateTime = LocalDateTime.parse(time).atOffset(ZoneOffset.UTC)
    // define a formatter for your desired output
    val formatter = DateTimeFormatter.ofPattern(format, Locale("th"))
    return offsetDateTime.format(formatter)
}