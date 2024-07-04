package com.praeee.jetpackcomposeapp.data.datasource

import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.CoinDetailResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.CoinResponse
import retrofit2.Response

interface NewsDataSource  {

    suspend fun getCoinList(): Response<CoinResponse>

    suspend fun getCoinDetail(
        uuid: String,
    ): Response<CoinDetailResponse>


    suspend fun getCoinSearch(
        uuid: String,
    ): Response<CoinResponse>



}