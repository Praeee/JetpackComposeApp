package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model

data class CoinViewStateValue (
    val coinListState: CoinListState?= null,
    val coinDetailState: CoinDetailViewState? = null,
    var coinTopRank: List<CoinState>?= listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val inDiceFriendIndex : Set<Int>?= setOf(),
)

sealed class CoinViewState {
    data class Success(val viewState: CoinViewStateValue) : CoinViewState()
    data class Error(val message: String) : CoinViewState()
    data object Loading : CoinViewState()
}

sealed class CoinUiState {
    data object Loading : CoinUiState()
    data class Success(val data: CoinViewStateValue) : CoinUiState()
    data class Error(val exception: Exception) : CoinUiState()
}