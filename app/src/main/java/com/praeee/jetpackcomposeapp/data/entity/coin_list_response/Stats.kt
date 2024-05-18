package com.praeee.jetpackcomposeapp.data.entity.coin_list_response

data class Stats(
    val total: Int,
    val total24hVolume: String,
    val totalCoins: Int,
    val totalExchanges: Int,
    val totalMarketCap: String,
    val totalMarkets: Int
)