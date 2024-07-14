package com.praeee.feature.coin.ui.screen

import com.praeee.feature.coin.domain.model.Coin

data class CoinStateHolder(
    val isLoading: Boolean = false,
    val data: List<Coin>? = null,
    val error: String? = ""
)
