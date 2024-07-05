package com.praeee.jetpackcomposeapp.data.datasource

import com.praeee.jetpackcomposeapp.data.entity.news_response.NewsResponse
import retrofit2.Response

fun interface NewsDataSource  {

    suspend fun getNewsHeadline(
        country: String,
    ): Response<NewsResponse>

}