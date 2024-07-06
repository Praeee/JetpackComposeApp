package com.praeee.jetpackcomposeapp.data.entity.news_list_response

import android.os.Build
import androidx.annotation.RequiresApi
import com.praeee.jetpackcomposeapp.data.AppConstants
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleListUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.SourceUiState
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

data class NewsListResponse(
    val articles: List<Article>?= emptyList(),
    val status: String?="",
    val totalResults: Int?=0
)


@RequiresApi(Build.VERSION_CODES.O)
fun NewsListResponse.toArticleListUiState() : ArticleListUiState {
    return ArticleListUiState(
        articleList = this.articles?.map { it.toArticleUiState() } ?: emptyList()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Article.toArticleUiState() : ArticleUiState {
    return ArticleUiState(
        author = this.author?:"",
        title = this.title?:"",
        description = this.description?:"",
        url = this.url?:"",
        urlToImage = this.urlToImage?:"",
        publishedAt = timeUtcToFormat(time = this.publishedAt?:"", format = AppConstants.DATE_TIME_FORMAT)?:"",
        content = this.content?:"",
        source = this.source?.toSourceUiState()
    )
}

fun Source.toSourceUiState() : SourceUiState {
    return SourceUiState(
        id = this.id?:"",
        name = this.name?:""
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun timeUtcToFormat(time : String?, format : String) : String {
    // parse it to a LocalDateTime (date & time without zone or offset)
    time?.let {
        val offsetDateTime: OffsetDateTime = LocalDateTime.parse(time).atOffset(ZoneOffset.UTC)
        // define a formatter for your desired output
        val formatter = DateTimeFormatter.ofPattern(format, Locale("th"))
        return offsetDateTime.format(formatter)
    }
    return ""
}