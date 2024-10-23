package com.praeee.jetpackcomposeapp.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "coin")
data class CoinEntity(
    @PrimaryKey val uuid: String,
    val symbol: String,
    val name: String,
    val color: String?,
    val iconUrl: String?,
    val marketCap: String?,
    val price: String?,
    val listedAt: Long?,
    val tier: Int?,
    val change: String?,
    val rank: Int?,
    val sparkline: List<String?>?,
    val lowVolume: Boolean?,
    val coinRankingUrl: String?,
    val volume24h: String?,
    val btcPrice: String?
)

@Dao
interface CoinDao {
    @Query("SELECT * FROM coin ORDER BY uuid DESC LIMIT 10")
    fun getCoinList() : Flow<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(crypto: CoinEntity)

    @Query("SELECT * FROM coin WHERE uuid = :uuid")
    suspend fun getCoinById(uuid: String): CoinEntity?
}