package com.praeee.jetpackcomposeapp.data.datasource

import com.praeee.jetpackcomposeapp.data.api.ApiService
import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.CoinDetailResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.CoinResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : CoinDataSource {
    override suspend fun getCoinList(): Response<CoinResponse> {
        return apiService.getCoinList()
    }

    override suspend fun getCoinDetail(uuid: String): Response<CoinDetailResponse> {
        return apiService.getCoinDetail(uuid)
    }

    override suspend fun getCoinSearch(search: String): Response<CoinResponse> {
        return apiService.getCoinSearch(search)
    }

}