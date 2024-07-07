package com.praeee.jetpackcomposeapp.data.api

import com.praeee.jetpackcomposeapp.BuildConfig
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("kotlin:S6517")
interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String = BuildConfig.NEWS_API_KEY
    ): Response<NewsListResponse>


}