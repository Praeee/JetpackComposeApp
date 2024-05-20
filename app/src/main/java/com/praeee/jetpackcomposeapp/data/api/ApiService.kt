package com.praeee.jetpackcomposeapp.data.api

import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.CoinDetailResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/coins")
    suspend fun getCoinList(): Response<CoinResponse>

    @GET("v2/coin/{uuid}")
    suspend fun getCoinDetail(@Path("uuid") uuid: String): Response<CoinDetailResponse>

    @GET("v2/coins")
    suspend fun getCoinSearch(
        @Query("search") text: String,
    ): Response<CoinResponse>


}