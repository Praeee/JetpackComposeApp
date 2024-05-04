package com.praeee.jetpackcomposeapp.data.api

import com.praeee.jetpackcomposeapp.BuildConfig
import com.praeee.jetpackcomposeapp.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>

}