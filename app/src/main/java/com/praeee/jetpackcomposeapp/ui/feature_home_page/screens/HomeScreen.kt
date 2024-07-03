package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.components.BoxWithSwipeRefresh
import com.praeee.jetpackcomposeapp.ui.components.ErrorUiState
import com.praeee.jetpackcomposeapp.ui.components.Loader
import com.praeee.jetpackcomposeapp.ui.components.NotFoundKeyword
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinViewStateValue
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel.CoinViewModel
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.BottomSheetDetail
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.CoinListItem
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.InviteFriendsItem
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.SearchText
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.TopLankListItem
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.TopRankTitle
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG = "HomeScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    coinViewModel: CoinViewModel = hiltViewModel()
) {

    val state = coinViewModel.uiState
    val onEvent = coinViewModel::onEvent

    HomeScreenContent(
        state = state,
        onEvent = onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: CoinViewStateValue,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {

    val color = MaterialTheme.colorScheme

    val (text, setText) = remember { mutableStateOf("") }

    val activityActionSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var openActivityActionSheet by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state.isOpenBottomSheet) {
        openActivityActionSheet = state.isOpenBottomSheet
    }

    var isRefreshing by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {

            SearchText(
                value = text,
                onValueChange = setText,
                onFocusLost = { newText ->
                    onEvent.invoke(CoinEvent.OnSearchText(newText))
                }
            )

            if (state.isNoResult) {
                NotFoundKeyword()
            }

            if (openActivityActionSheet) {
                ModalBottomSheet(
                    sheetState = activityActionSheetState,
                    dragHandle = null,
                    onDismissRequest = {
                        openActivityActionSheet = false
                    },
                ) {
                    if (state.coinDetailState != null) {
                        BottomSheetDetail(coin = state.coinDetailState)
                    }
                }

            }

            if (state.isLoading) {
                Loader()
            }

            if (state.isError) {
                ErrorUiState(
                    onClick = {
                        onEvent.invoke(CoinEvent.OnErrorUi(true))
                    }
                )
            }

            BoxWithSwipeRefresh(
                onSwipe = {
                    isRefreshing = true
                    onEvent.invoke(CoinEvent.PullToRefresh)
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
                    LazyColumn(
                        modifier = modifier
                    ) {

                        when {
                            !state.coinSearchListState?.coins.isNullOrEmpty() -> {
                                item {
                                    Text(
                                        text = stringResource(id = R.string.buy_and_sell_title),
                                        modifier = modifier
                                            .padding(start = 16.dp),
                                        maxLines = 1,
                                        textAlign = TextAlign.Start,
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                        ),
                                        color = color.primary
                                    )
                                }
                                itemsIndexed(
                                    state.coinSearchListState?.coins ?: emptyList()
                                ) { index, coinList ->

                                    CoinListItem(
                                        coin = coinList,
                                        modifier = modifier,
                                        onEvent = onEvent
                                    )

                                    if (state.inDiceFriendIndex?.contains(index) == true) {
                                        InviteFriendsItem(state.linkInviteFriend)
                                    }

                                }
                            }
                            !state.coinListState?.coins.isNullOrEmpty() -> {

                                if (state.coinTopRank != null && text.isEmpty()) {

                                    itemsIndexed(state.coinTopRank!!.chunked(3)) { _, rowItems ->
                                        Row(
                                            modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .padding(8.dp)
                                        ) {
                                            Column(
                                                modifier = modifier
                                            ) {
                                                TopRankTitle()
                                                Row(
                                                    modifier =
                                                    modifier
                                                        .fillMaxWidth()
                                                        .padding(8.dp)
                                                ) {
                                                    rowItems.forEach { coin ->
                                                        TopLankListItem(coin, modifier, onEvent)
                                                    }
                                                    if (rowItems.size < 3) {
                                                        for (i in 1..(3 - rowItems.size)) {
                                                            Spacer(modifier = Modifier.weight(1f))
                                                        }
                                                    }
                                                }

                                            }

                                        }
                                    }
                                }

                                item {
                                    Text(
                                        text = stringResource(id = R.string.buy_and_sell_title),
                                        modifier = modifier
                                            .padding(start = 16.dp),
                                        maxLines = 1,
                                        textAlign = TextAlign.Start,
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                        ),
                                        color = color.primary
                                    )
                                }



                                itemsIndexed(state.coinListState?.coins ?: emptyList()) { index, coinList ->

                                    CoinListItem(
                                        coin = coinList,
                                        modifier = modifier,
                                        onEvent = onEvent
                                    )

                                    if (state.inDiceFriendIndex?.contains(index) == true) {
                                        InviteFriendsItem(state.linkInviteFriend)
                                    }

                                }
                            }
                            else -> {}
                        }

                    }
                }
            }



        }

    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}








