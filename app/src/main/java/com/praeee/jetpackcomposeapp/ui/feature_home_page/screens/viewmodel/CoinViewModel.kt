package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praeee.jetpackcomposeapp.data.entity.NewsResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.CoinDetailResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_detail_response.toCoinDetailViewState
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.CoinResponse
import com.praeee.jetpackcomposeapp.data.entity.coin_list_response.toCoinListState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinListState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinViewStateValue
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent
import com.praeee.jetpackcomposeapp.ui.repository.CoinRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinViewStateValue())
    val uiState: StateFlow<CoinViewStateValue> = _uiState.asStateFlow()

    init {
        _uiState.value = CoinViewStateValue(
            isLoading = true,
        )
        Log.d(TAG,"init block of CoinViewModel")
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


    private fun getCoinList() {
        Log.d(TAG,"init getCoinList")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                coinRepository.getCoinList().collectLatest { coinResponse ->
                    when (coinResponse) {
                        is ResourceState.Success -> {
                            _uiState.value = CoinViewStateValue(
                                isLoading = false,
                                isError = false,
                                coinListState = coinResponse.data.toCoinListState(),
                                coinTopRank = coinResponse.data.toCoinListState().coins?.filter { it.rank in 1..3 },
                                inDiceFriendIndex = generateNumbers(5,coinResponse.data.toCoinListState().coins?.size?:5).toSet()
                            )
                        }
                        is ResourceState.Error -> {
                            _uiState.value = CoinViewStateValue(
                                isError = true,
                                isLoading = false,
                            )
                        }
                        is ResourceState.Loading -> {
                            _uiState.value = CoinViewStateValue(
                                isLoading = true,
                                isError = false,
                            )
                        }
                    }
                }
            } catch (error: Exception) {
                _uiState.value = CoinViewStateValue(
                    isError = true,
                )
            }

        }
    }

    private fun getCoinDetail(uuid : String?) {
        Log.d(TAG,"init getCoinDetail")
        Log.d(TAG,"init uuid $uuid")

        if (uuid != null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    coinRepository.getCoinDetail(uuid).collectLatest { coinDetailResponse ->
                        when (coinDetailResponse) {
                            is ResourceState.Success -> {
                                _uiState.value = CoinViewStateValue(
                                    isLoading = false,
                                    isError = false,
                                    coinListState = _uiState.value.coinListState,
                                    coinDetailState = coinDetailResponse.data.toCoinDetailViewState(),
                                    isOpenBottomSheet = true
                                )
                            }
                            is ResourceState.Error -> {
                                _uiState.value = CoinViewStateValue(
                                    isError = true,
                                    isLoading = false,
                                )
                            }
                            is ResourceState.Loading -> {
                                _uiState.value = CoinViewStateValue(
                                    isLoading = true,
                                    isError = false,
                                )
                            }
                        }
                    }
                } catch (error: Exception) {
                    _uiState.value = CoinViewStateValue(
                        isError = true,
                    )
                }

            }
        }
    }

    fun onEvent(event: CoinEvent) {
        when (event) {
            is CoinEvent.onClickItem -> {
                Log.d(TAG,"onEvent item ${event.item}")
                getCoinDetail(event.item.uuid)

            }

            is CoinEvent.onErrorUi -> {
                getCoinList()
            }
        }
    }

    companion object {
        const val TAG = "NewsViewModel"
    }

}