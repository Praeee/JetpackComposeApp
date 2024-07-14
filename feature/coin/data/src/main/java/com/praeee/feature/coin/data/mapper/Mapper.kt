package com.praeee.feature.coin.data.mapper

import com.praeee.core.network.model.CoinResponse
import com.praeee.feature.coin.domain.model.Coin

fun CoinResponse.toDomainCoinList() : List<Coin> {
    return this.data.coins.map {
        Coin(it.iconUrl)
    }
}