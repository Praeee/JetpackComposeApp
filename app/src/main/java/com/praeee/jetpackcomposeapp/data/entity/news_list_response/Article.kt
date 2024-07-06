package com.praeee.jetpackcomposeapp.data.entity.news_list_response

data class Article(
    val author: String?="",
    val content: String?="",
    val description: String?="",
    val publishedAt: String?="",
    val source: Source?=null,
    val title: String?="",
    val url: String?="",
    val urlToImage: String?=""
)