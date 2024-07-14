package com.praeee.core.network.di

import com.praeee.core.network.ApiService
import com.praeee.core.network.dataproviders.CoinDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object AppConstants {

    const val COIN_RANKING_URL = "https://api.coinranking.com/"
}

@InstallIn(SingletonComponent::class)
@Module
object NetWorkModule {

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }
        val gson = GsonConverterFactory.create()

        return Builder()
            .baseUrl(AppConstants.COIN_RANKING_URL)
            .client(httpClient.build())
            .addConverterFactory(gson)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCoinDataProvider(apiService: ApiService) : CoinDataProviders {
        return CoinDataProviders((apiService))
    }

}