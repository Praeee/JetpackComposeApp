package com.praeee.jetpackcomposeapp.data.entity

data class NewsResponse(
    val status : String?="",
    val totalResults : Int?=0,
    val articles: List<Article> ?= listOf()
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
