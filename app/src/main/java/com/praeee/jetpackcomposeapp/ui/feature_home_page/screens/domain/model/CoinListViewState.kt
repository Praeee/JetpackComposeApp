package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model

data class CoinListState (
    val coins: List<CoinState>?= listOf(),
    val stats: StatsState?=null
)

data class StatsState(
    val total: Int?=0,
    val total24hVolume: String?="",
    val totalCoins: Int?=0,
    val totalExchanges: Int?=0,
    val totalMarketCap: String?="",
    val totalMarkets: Int?=0
)

data class CoinState(
    val btcPrice: String?="",
    val change: String?="",
    val coinRankingUrl: String?="",
    val color: String?="",
    val iconUrl: String?="",
    val listedAt: Int?=0,
    val lowVolume: Boolean?=false,
    val marketCap: String?="",
    val name: String?="",
    val price: String?="",
    val rank: Int?=0,
    val symbol: String?="",
    val tier: Int?=0,
    val uuid: String?="",
)