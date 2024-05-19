package com.praeee.jetpackcomposeapp.data.entity.coin_list_response

data class Stats(
    val total: Int?=0,
    val total24hVolume: String?="",
    val totalCoins: Int?=0,
    val totalExchanges: Int?=0,
    val totalMarketCap: String?="",
    val totalMarkets: Int?=0,
)