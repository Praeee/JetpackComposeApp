package com.praeee.feature.coin.data.repo

import com.praeee.core.network.dataproviders.CoinDataProviders
import com.praeee.feature.coin.data.mapper.toDomainCoinList
import com.praeee.feature.coin.domain.model.Coin
import com.praeee.feature.coin.domain.repo.CoinRepository
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(
    private val coinDataProvider : CoinDataProviders
) : CoinRepository {
    override suspend fun getCoinList(): List<Coin> {
        return coinDataProvider.getCoinList().toDomainCoinList()
    }
}