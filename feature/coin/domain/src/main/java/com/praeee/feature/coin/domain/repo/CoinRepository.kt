package com.praeee.feature.coin.domain.repo

import com.praeee.feature.coin.domain.model.Coin

interface CoinRepository {

    suspend fun getCoinList() : List<Coin>
}