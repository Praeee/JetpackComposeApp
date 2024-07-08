package com.praeee.jetpackcomposeapp.ui.repository

import com.praeee.jetpackcomposeapp.data.datasource.NewsDataSource
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.NewsListResponse
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    fun getNewsHeadline(country: String): Flow<ResourceState<NewsListResponse>> = flow {
        try {
            val response = newsDataSource.getNewsHeadline(country)

            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error fetching news data"))
            }
        } catch (e: Exception) {
            emit(ResourceState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }



}