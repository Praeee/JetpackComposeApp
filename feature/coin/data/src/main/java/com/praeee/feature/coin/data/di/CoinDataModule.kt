package com.praeee.feature.coin.data.di

import com.praeee.core.network.dataproviders.CoinDataProviders
import com.praeee.feature.coin.data.repo.CoinRepoImpl
import com.praeee.feature.coin.domain.repo.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CoinDataModule {

    @Provides
    fun provideCoinRepo(coinDataProviders: CoinDataProviders) : CoinRepository {
        return CoinRepoImpl(coinDataProviders)
    }
}