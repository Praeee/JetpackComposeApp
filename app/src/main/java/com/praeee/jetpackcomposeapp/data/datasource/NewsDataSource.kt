package com.praeee.jetpackcomposeapp.data.datasource

import com.praeee.jetpackcomposeapp.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    fun getNewsHeadline(
        country: String,
    ): Response<NewsResponse>

}