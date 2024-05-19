package com.praeee.jetpackcomposeapp.data.entity.coin_detail_response

import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinDetailViewState

data class CoinDetailResponse(
    val `data`: Data,
    val status: String?=""
)

fun CoinDetailResponse.toCoinDetailViewState() : CoinDetailViewState {
    val getDataDetail = this.data.coin
    return CoinDetailViewState(
        btcPrice = getDataDetail?.btcPrice,
        change = getDataDetail?.change,
        coinRankingUrl = getDataDetail?.coinrankingUrl,
        color = getDataDetail?.color,
        description = getDataDetail?.description,
        fullyDilutedMarketCap = getDataDetail?.fullyDilutedMarketCap,
        hasContent = getDataDetail?.hasContent,
        iconUrl = getDataDetail?.iconUrl,
        marketCap = getDataDetail?.marketCap,
        name = getDataDetail?.name,
        numberOfExchanges = getDataDetail?.numberOfExchanges,
        numberOfMarkets = getDataDetail?.numberOfMarkets,
        price = getDataDetail?.price,
        rank = getDataDetail?.rank,
        symbol = getDataDetail?.symbol,
        tier = getDataDetail?.tier,
        uuid = getDataDetail?.uuid,
        websiteUrl = getDataDetail?.websiteUrl
    )
}