package com.praeee.feature.coin.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.praeee.feature.coin.domain.model.Coin

@Composable
fun CoinScreen(
    viewModel: CoinViewModel
) {
    val state = viewModel.coinList.value
    val search = viewModel.coinSearch.collectAsState()

    CoinScreenContent(
        data = state.data
    )

}

@Composable
fun CoinScreenContent(
    data: List<Coin>? = null,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("Coin")
    }

}