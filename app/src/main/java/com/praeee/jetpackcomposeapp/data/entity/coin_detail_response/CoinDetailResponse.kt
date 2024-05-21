package com.praeee.jetpackcomposeapp.data.entity.coin_detail_response

import android.annotation.SuppressLint
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
        color = getDataDetail?.color?:"#000000",
        description = getDataDetail?.description,
        fullyDilutedMarketCap = getDataDetail?.fullyDilutedMarketCap,
        hasContent = getDataDetail?.hasContent,
        iconUrl = getDataDetail?.iconUrl,
        marketCap = mapDataMarketCap(getDataDetail?.marketCap?:""),
        name = getDataDetail?.name,
        numberOfExchanges = getDataDetail?.numberOfExchanges,
        numberOfMarkets = getDataDetail?.numberOfMarkets,
        price = "$ ${formatToTwoDecimalPlaces(getDataDetail?.price?:"")}",
        rank = getDataDetail?.rank,
        symbol = "(${getDataDetail?.symbol?:"-"})",
        tier = getDataDetail?.tier,
        uuid = getDataDetail?.uuid,
        websiteUrl = getDataDetail?.websiteUrl
    )
}

fun subString(string : String) : String {
    // Step 1: Get the substring from index 0 to 3
    val subString = if (string.length >= 3) string.substring(0, 4) else string

    // Step 2: Insert the dot at index 2
    return if (subString.length >= 2) {
        subString.substring(0, 2) + "." + subString.substring(2)
    } else {
        subString // In case the input is shorter than 3 characters
    }
}

fun mapDataMarketCap(string : String) : String {
    val formatString = subString(string)
    return when  {
        string.length in 6..9 -> {
            "$ $formatString million"
        }
        string.length in 10..12 -> {
            "$ $formatString billion"
        }
        (string.length > 12) -> {
            "$ $formatString trillion"
        }
        else -> {
            "$ $formatString"
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatToTwoDecimalPlaces(number: String): String {
    val stringToDouble = number.toDouble()
    return String.format("%.2f", stringToDouble) ?: "0.00000"
}