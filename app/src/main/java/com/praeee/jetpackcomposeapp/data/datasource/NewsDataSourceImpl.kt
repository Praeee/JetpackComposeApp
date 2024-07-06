package com.praeee.jetpackcomposeapp.data.datasource

import com.praeee.jetpackcomposeapp.data.api.NewsApiService
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.NewsListResponse
import com.praeee.jetpackcomposeapp.data.entity.news_response.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsDataSource {
    override suspend fun getNewsHeadline(country: String): Response<NewsListResponse> {
        return apiService.getNewsHeadline(country)
    }
}