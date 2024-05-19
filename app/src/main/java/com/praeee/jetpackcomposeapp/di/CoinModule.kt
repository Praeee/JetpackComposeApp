package com.praeee.jetpackcomposeapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoinModule {
//    @Binds
//    abstract fun bindGetCoinListUseCase(useCaseImpl: GetCoinListUseCaseImpl) : GetCoinListUseCase

}