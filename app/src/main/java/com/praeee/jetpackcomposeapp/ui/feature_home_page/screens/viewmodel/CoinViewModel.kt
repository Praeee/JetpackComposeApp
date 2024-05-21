package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.toCoinDetailViewState
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.toCoinListState
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.toStatsState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinListState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinViewStateValue
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent
import com.praeee.jetpackcomposeapp.ui.repository.CoinRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    var uiState by mutableStateOf(CoinViewStateValue())
        private set

    init {
        uiState  = uiState.copy(
            isLoading = true,
            isError = false,
            isRefresh = false,
            linkInviteFriend = "https://careers.lmwn.com/"
        )
        getCoinList()

    }

    private fun generateNumbers(initial: Int, size: Int): List<Int> {
        val result = mutableListOf<Int>()
        var number = initial
        for (i in 0 until size) {
            result.add(number)
            number *= 2
        }
        return result
    }

    private fun pullToRefresh() {
        getCoinList()
    }


    private fun getCoinList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                coinRepository.getCoinList().collectLatest { coinResponse ->
                    when (coinResponse) {
                        is ResourceState.Success -> {
                            uiState  = uiState.copy(
                                isLoading = false,
                                isError = false,
                                coinSearchListState = null,
                                coinListState = CoinListState(
                                    coins = if (coinResponse.data.toCoinListState().coins?.size!! > 0 ) {
                                        coinResponse.data.toCoinListState().coins?.filter { it.rank!! > 3 }
                                    } else {
                                        coinResponse.data.toCoinListState().coins
                                    },
                                    stats = coinResponse.data.data.stats.toStatsState()
                                ) ,
                                coinTopRank = coinResponse.data.toCoinListState().coins?.filter { it.rank in 1..3 },
                                inDiceFriendIndex = generateNumbers(5,coinResponse.data.toCoinListState().coins?.size?:5).toSet(),
                            )
                        }
                        is ResourceState.Error -> {
                            uiState  = uiState.copy(
                                isError = true,
                                isLoading = false,
                                isRefresh = false
                            )
                        }
                        is ResourceState.Loading -> {
                            uiState  = uiState.copy(
                                isLoading = true,
                                isError = false,
                                isRefresh = false
                            )
                        }
                    }
                }
            } catch (error: Exception) {
                uiState  = uiState.copy(
                    isError = true,
                )
            }

        }
    }

    private fun getCoinDetail(uuid : String?) {
        if (uuid != null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    coinRepository.getCoinDetail(uuid).collectLatest { coinDetailResponse ->
                        when (coinDetailResponse) {
                            is ResourceState.Success -> {
                                uiState  = uiState.copy(
                                    isLoading = false,
                                    isError = false,
                                    coinDetailState = coinDetailResponse.data.toCoinDetailViewState(),
                                    isOpenBottomSheet = true
                                )
                            }
                            is ResourceState.Error -> {
                                uiState  = uiState.copy(
                                    isError = true,
                                    isLoading = false,
                                    isOpenBottomSheet = false
                                )
                            }
                            is ResourceState.Loading -> {
                                uiState  = uiState.copy(
                                    isLoading = true,
                                    isError = false,
                                    isOpenBottomSheet = false
                                )
                            }
                        }
                    }
                } catch (error: Exception) {
                    uiState  = uiState.copy(
                        isError = true,
                    )
                }

            }
        }
    }

    private fun onSearchText(text : String?) {
        if (!text.isNullOrEmpty() || !text.isNullOrBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    coinRepository.getCoinSearch(text).collectLatest { searchList  ->
                        when (searchList) {
                            is ResourceState.Success -> {
                                val coinSearchListState = searchList.data.toCoinListState()
                                uiState  = uiState.copy(
                                    isLoading = false,
                                    isError = false,
                                    coinSearchListState = coinSearchListState,
                                    isNoResult = coinSearchListState.coins.isNullOrEmpty()
                                )
                            }
                            is ResourceState.Error -> {
                                uiState  = uiState.copy(
                                    isError = true,
                                    isLoading = false,
                                )
                            }
                            is ResourceState.Loading -> {
                                uiState  = uiState.copy(
                                    isLoading = true,
                                    isError = false,
                                )
                            }
                        }
                    }
                } catch (error: Exception) {
                    uiState  = uiState.copy(
                        isError = true,
                    )
                }

            }
        } else {
            getCoinList()
        }
    }


    fun onEvent(event: CoinEvent) {
        when (event) {
            is CoinEvent.OnClickItemCoin -> {
                getCoinDetail(event.item.uuid)
            }

            is CoinEvent.OnErrorUi -> {
                getCoinList()
            }

            is CoinEvent.OnSearchText -> {
                onSearchText(event.text)
            }

            is CoinEvent.PullToRefresh -> {
                pullToRefresh()
            }
        }
    }

    companion object {
        const val TAG = "CoinViewModel"
    }

}