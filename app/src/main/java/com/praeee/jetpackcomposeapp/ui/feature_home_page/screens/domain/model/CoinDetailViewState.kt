package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model

data class CoinDetailViewState (
    val btcPrice: String?="",
    val change: String?="",
    val coinRankingUrl: String?="",
    val color: String?="",
    val description: String?="",
    val fullyDilutedMarketCap: String?="",
    val hasContent: Boolean?=false,
    val iconUrl: String?="",
    val marketCap: String?="",
    val name: String?="",
    val numberOfExchanges: Int?=0,
    val numberOfMarkets: Int?=0,
    val price: String?="",
    val rank: Int?=0,
    val symbol: String?="",
    val tier: Int?=0,
    val uuid: String?="",
    val websiteUrl: String?="",
)
