package com.praeee.feature.coin.domain.di

import com.praeee.feature.coin.domain.repo.CoinRepository
import com.praeee.feature.coin.domain.usecase.GetCoinListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CoinDomainModule {

    @Provides
    fun provideGetCoinListUseCase(coinRepository: CoinRepository) : GetCoinListUseCase {
        return GetCoinListUseCase(coinRepository)
    }

}