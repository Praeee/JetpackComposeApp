package com.praeee.core.network

import com.praeee.core.network.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v2/coins")
    suspend fun getCoinList(): CoinResponse

//    @GET("v2/coin/{uuid}")
//    suspend fun getCoinDetail(@Path("uuid") uuid: String): Response<CoinDetailResponse>

    @GET("v2/coins")
    suspend fun getCoinSearch(
        @Query("search") text: String,
    ): CoinResponse
}