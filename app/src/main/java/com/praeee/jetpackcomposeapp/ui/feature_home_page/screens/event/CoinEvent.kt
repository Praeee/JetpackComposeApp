package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event

import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinState

sealed class CoinEvent {
    data class onClickItem(val item: CoinState) : CoinEvent()

}