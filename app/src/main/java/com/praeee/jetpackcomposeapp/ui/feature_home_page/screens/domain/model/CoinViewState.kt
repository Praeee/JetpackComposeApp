package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model

data class CoinViewStateValue (
    val coinListState: CoinListState?= null,
    val coinDetailState: CoinDetailViewState? = null,
    var coinTopRank: List<CoinState>?= listOf(),
    val isLoading: Boolean = true,
    val isError: Boolean = true,
    val inDiceFriendIndex : Set<Int>?= setOf(),
    val isOpenBottomSheet: Boolean = false,
    val coinSearchListState: CoinListState?= null,

    )