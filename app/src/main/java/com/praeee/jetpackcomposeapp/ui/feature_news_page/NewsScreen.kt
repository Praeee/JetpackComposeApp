package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.ui.components.BoxWithSwipeRefresh
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val state = newsViewModel.uiState
    val onEvent = newsViewModel::onEvent

    NewsScreenContent(
        state = state,
        onEvent = onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenContent(
    state: NewsUiState,
    modifier: Modifier = Modifier,
    onEvent: (NewsEvent) -> Unit,
) {

    val color = MaterialTheme.colorScheme

    val (text, setText) = remember { mutableStateOf("") }

    var isRefreshing by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()


    BoxWithSwipeRefresh(
        onSwipe = {
            isRefreshing = true
//                    onEvent.invoke(CoinEvent.PullToRefresh)
            coroutineScope.launch {
                delay(2000)
                isRefreshing = false
            }
        },
        isRefreshing = isRefreshing,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
//                    LazyColumn(
//                        modifier = modifier
//                    ) {
//
//                        when {
//                            !state.coinSearchListState?.coins.isNullOrEmpty() -> {
//                                item {
//                                    Text(
//                                        text = stringResource(id = R.string.buy_and_sell_title),
//                                        modifier = modifier
//                                            .padding(start = 16.dp),
//                                        maxLines = 1,
//                                        textAlign = TextAlign.Start,
//                                        style = TextStyle(
//                                            fontSize = 16.sp,
//                                            fontWeight = FontWeight.Medium,
//                                        ),
//                                        color = color.primary
//                                    )
//                                }
//                                itemsIndexed(
//                                    state.coinSearchListState?.coins ?: emptyList()
//                                ) { index, coinList ->
//
//                                    CoinListItem(
//                                        coin = coinList,
//                                        modifier = modifier,
//                                        onEvent = onEvent
//                                    )
//
//                                    if (state.inDiceFriendIndex?.contains(index) == true) {
//                                        InviteFriendsItem(state.linkInviteFriend)
//                                    }
//
//                                }
//                            }
//                            !state.coinListState?.coins.isNullOrEmpty() -> {
//
//                                if (state.coinTopRank != null && text.isEmpty()) {
//
//                                    itemsIndexed(state.coinTopRank!!.chunked(3)) { _, rowItems ->
//                                        Row(
//                                            modifier =
//                                            modifier
//                                                .fillMaxWidth()
//                                                .padding(8.dp)
//                                        ) {
//                                            Column(
//                                                modifier = modifier
//                                            ) {
//                                                TopRankTitle()
//                                                Row(
//                                                    modifier =
//                                                    modifier
//                                                        .fillMaxWidth()
//                                                        .padding(8.dp)
//                                                ) {
//                                                    rowItems.forEach { coin ->
//                                                        TopLankListItem(coin, modifier, onEvent)
//                                                    }
//                                                    if (rowItems.size < 3) {
//                                                        for (i in 1..(3 - rowItems.size)) {
//                                                            Spacer(modifier = Modifier.weight(1f))
//                                                        }
//                                                    }
//                                                }
//
//                                            }
//
//                                        }
//                                    }
//                                }
//
//                                item {
//                                    Text(
//                                        text = stringResource(id = R.string.buy_and_sell_title),
//                                        modifier = modifier
//                                            .padding(start = 16.dp),
//                                        maxLines = 1,
//                                        textAlign = TextAlign.Start,
//                                        style = TextStyle(
//                                            fontSize = 16.sp,
//                                            fontWeight = FontWeight.Medium,
//                                        ),
//                                        color = color.primary
//                                    )
//                                }
//
//
//
//                                itemsIndexed(state.coinListState?.coins ?: emptyList()) { index, coinList ->
//
//                                    CoinListItem(
//                                        coin = coinList,
//                                        modifier = modifier,
//                                        onEvent = onEvent
//                                    )
//
//                                    if (state.inDiceFriendIndex?.contains(index) == true) {
//                                        InviteFriendsItem(state.linkInviteFriend)
//                                    }
//
//                                }
//                            }
//                            else -> {}
//                        }
//
//                    }
        }
    }


}