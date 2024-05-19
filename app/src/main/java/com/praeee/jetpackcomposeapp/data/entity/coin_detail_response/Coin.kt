package com.praeee.jetpackcomposeapp.data.entity.coin_detail_response

data class Coin(
    val `24hVolume`: String?="",
    val allTimeHigh: AllTimeHigh?=null,
    val btcPrice: String?="",
    val change: String?="",
    val coinrankingUrl: String?="",
    val color: String?="",
    val contractAddresses: List<String>?= listOf(),
    val description: String?="",
    val fullyDilutedMarketCap: String?="",
    val hasContent: Boolean?=false,
    val iconUrl: String?="",
    val links: List<Link>?= listOf(),
    val listedAt: Int?=0,
    val lowVolume: Boolean?=false,
    val marketCap: String?="",
    val name: String?="",
    val notices: String?="",
    val numberOfExchanges: Int?=0,
    val numberOfMarkets: Int?=0,
    val price: String?="",
    val priceAt: Int?=0,
    val rank: Int?=0,
    val sparkline: List<String>?= listOf(),
    val supply: Supply?=null,
    val symbol: String?="",
    val tags: List<String>?= listOf(),
    val tier: Int?=0,
    val uuid: String?="",
    val websiteUrl: String?=""
)

data class Link(
    val name: String?="",
    val type: String?="",
    val url: String?=""
)