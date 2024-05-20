package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event

import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinState

sealed class CoinEvent {
    data class OnClickItemCoin(val item: CoinState) : CoinEvent()
    data class OnErrorUi(val error: Boolean) : CoinEvent()
    data class OnSearchText(val text: String?) : CoinEvent()
    data class OnCloseBottomSheet(val error: Boolean) : CoinEvent()


}