package com.praeee.jetpackcomposeapp.data.entity.coin_list_response

data class CoinResponse(
    val `data`: Data,
    val status: String
)

data class Data(
    val coins: List<Coin>,
    val stats: Stats
)