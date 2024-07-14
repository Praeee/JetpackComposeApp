package com.praeee.feature.coin.ui.di

import com.praeee.feature.coin.ui.navigation.CoinApi
import com.praeee.feature.coin.ui.navigation.CoinApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CoinUiModule {

    @Provides
    fun providerCoinApi() : CoinApi {
        return CoinApiImpl()
    }
}