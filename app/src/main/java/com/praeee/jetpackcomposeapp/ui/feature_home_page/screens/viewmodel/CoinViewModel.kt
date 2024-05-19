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

    val value = "value"
    private val _news: MutableStateFlow<ResourceState<NewsResponse>> =
        MutableStateFlow(ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news

    private val _coinList: MutableStateFlow<ResourceState<CoinResponse>> =
        MutableStateFlow(ResourceState.Loading())
    val coinList: StateFlow<ResourceState<CoinResponse>> = _coinList

    private val _coinDetail: MutableStateFlow<ResourceState<CoinDetailResponse>> =
        MutableStateFlow(ResourceState.Loading())
    val coinDetail: StateFlow<ResourceState<CoinDetailResponse>> = _coinDetail

    val viewState : MutableLiveData<CoinViewStateValue> = MutableLiveData()

    private val _uiState = MutableStateFlow(CoinViewStateValue())
    val uiState: StateFlow<CoinViewStateValue> = _uiState.asStateFlow()

    init {
        _uiState.value = CoinViewStateValue(
            isLoading = true,
        )
        Log.d(TAG,"init block of CoinViewModel")
        getCoinList()
//        mapDataTopLank()
//        getCoinDetail("Qwsogvtv82FCd")


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
                _coinList.emit(ResourceState.Error(error.localizedMessage?: "error catch"))
            }

        }
    }

    private fun getCoinDetail(uuid : String?) {
        Log.d(TAG,"init getCoinDetail")

        if (uuid != null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    coinRepository.getCoinDetail(uuid).collectLatest { coinDetailResponse ->
                        _coinDetail.value = coinDetailResponse
                        _uiState.value = CoinViewStateValue(
                            isLoading = false,
                            coinDetailState = (coinDetailResponse as ResourceState.Success).data.toCoinDetailViewState()
                        )
                    }
                } catch (error: Exception) {
                    _coinList.emit(ResourceState.Error(error.localizedMessage?: "error catch"))
                }

            }
        }
        return

    }

    private fun doSomething() {
        print("doSomething")
    }

    companion object {
        const val TAG = "NewsViewModel"
    }

}