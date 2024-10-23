package com.praeee.jetpackcomposeapp.data.local.di

import android.content.Context
import androidx.room.Room
import com.praeee.jetpackcomposeapp.data.local.database.AppDatabase
import com.praeee.jetpackcomposeapp.data.local.database.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideMyModelDao(appDatabase: AppDatabase): CoinDao {
        return appDatabase.coinDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "CoinModel"
        ).build()
    }
}