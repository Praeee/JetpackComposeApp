package com.praeee.feature.coin.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praeee.core.common.UiEvent
import com.praeee.feature.coin.domain.usecase.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinListUseCase : GetCoinListUseCase
) : ViewModel() {

    private val _coinList = mutableStateOf(CoinStateHolder())
    val coinList : State<CoinStateHolder> get() = _coinList


    private val _coinSearch : MutableStateFlow<String> = MutableStateFlow("")
    val coinSearch : StateFlow<String> get() = _coinSearch

    init {
        getCoinList()
    }

    fun setSearch (search : String) {
        _coinSearch.value = search
    }

    private fun getCoinList() {
        viewModelScope.launch {
            getCoinListUseCase.invoke().onEach {
                when (it) {
                    is UiEvent.Error -> {
                        _coinList.value = CoinStateHolder(error = it.message.toString())
                    }

                    is UiEvent.Loading -> {
                        _coinList.value = CoinStateHolder(isLoading = true)
                    }

                    is UiEvent.Success -> {
                        _coinList.value = CoinStateHolder(data = it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}