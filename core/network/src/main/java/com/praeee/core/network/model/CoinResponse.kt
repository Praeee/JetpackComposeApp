package com.praeee.core.network.model

data class CoinResponse(
    val `data`: Data,
    val status: String?=""
)

data class Data(
    val coins: List<CoinDto>,
    val stats: Stats
)

data class CoinDto(
    val `24hVolume`: String? = "",
    val btcPrice: String? = "",
    val change: String? = "",
    val coinrankingUrl: String? = "",
    val color: String? = "",
    val contractAddresses: List<String>? = emptyList(),
    val iconUrl: String? = "",
    val listedAt: Int? = 0,
    val lowVolume: Boolean? = false,
    val marketCap: String? = "",
    val name: String? = "",
    val price: String? = "",
    val rank: Int? = 0,
    val sparkline: List<String>,
    val symbol: String? = "",
    val tier: Int? = 0,
    val uuid: String? = "",
)

data class Stats(
    val total: Int?=0,
    val total24hVolume: String?="",
    val totalCoins: Int?=0,
    val totalExchanges: Int?=0,
    val totalMarketCap: String?="",
    val totalMarkets: Int?=0,
)