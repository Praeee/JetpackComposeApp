package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

sealed class NewsDetailNavEvent {
    data object OnNavigateUp : NewsDetailNavEvent()

}
