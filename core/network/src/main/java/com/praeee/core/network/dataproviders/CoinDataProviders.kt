package com.praeee.core.network.dataproviders

import com.praeee.core.network.ApiService
import javax.inject.Inject

class CoinDataProviders @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCoinList() = apiService.getCoinList()

}