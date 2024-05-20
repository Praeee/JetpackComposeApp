package com.praeee.jetpackcomposeapp.di

import com.praeee.jetpackcomposeapp.BuildConfig
import com.praeee.jetpackcomposeapp.data.AppConstants.COIN_RANKING_URL
import com.praeee.jetpackcomposeapp.data.api.ApiService
import com.praeee.jetpackcomposeapp.data.datasource.CoinDataSource
import com.praeee.jetpackcomposeapp.data.datasource.CoinDataSourceImpl
import com.praeee.jetpackcomposeapp.ui.repository.CoinRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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
            readTimeout(60,TimeUnit.SECONDS)
        }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(COIN_RANKING_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinDataSource(apiService: ApiService): CoinDataSource {
        return CoinDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinDataSource: CoinDataSource) : CoinRepository {
        return CoinRepository(coinDataSource)
    }

}