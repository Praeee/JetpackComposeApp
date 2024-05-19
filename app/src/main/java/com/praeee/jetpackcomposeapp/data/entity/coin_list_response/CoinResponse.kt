package com.praeee.jetpackcomposeapp.data.entity.coin_list_response

import android.annotation.SuppressLint
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinListState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.StatsState

data class CoinResponse(
    val `data`: Data,
    val status: String?=""
)

data class Data(
    val coins: List<Coin>,
    val stats: Stats
)

fun CoinResponse.toCoinListState() : CoinListState {
    val coinList = this.data.coins
    val mapCoinList : List<CoinState> = coinList.map { coin ->
        coin.toCoinState()
    }
    return CoinListState(
        coins = mapCoinList,
        stats = this.data.stats.toStatsState()
    )
}

fun Stats.toStatsState() : StatsState {
    return StatsState(
        total = this.total,
        total24hVolume = this.total24hVolume,
        totalCoins = this.totalCoins,
        totalExchanges = this.totalExchanges,
        totalMarketCap = this.totalMarketCap,
        totalMarkets = this.totalMarkets
    )
}

fun Coin.toCoinState(): CoinState {
    return CoinState(
        btcPrice = this.btcPrice,
        change = this.change,
        coinRankingUrl = this.coinrankingUrl,
        color = this.color,
        iconUrl = this.iconUrl,
        listedAt = this.listedAt,
        lowVolume = isPositive(this.change?:"0"),
        marketCap = this.marketCap,
        name = this.name,
        price = formatToFiveDecimalPlaces((this.price?: "0.00000").toString()),
        rank = this.rank,
        symbol = this.symbol,
        tier = this.tier,
        uuid = this.uuid
    )
}

@SuppressLint("DefaultLocale")
fun formatToFiveDecimalPlaces(number: String): String {
    val stringToDouble = number.toDouble()
    return String.format("%.5f", stringToDouble) ?: "0.00000"
}

fun isPositive(number: String): Boolean {
    val stringToDouble = number.toDouble()
    return stringToDouble > 0
}