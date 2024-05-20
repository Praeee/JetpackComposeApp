package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.orientationScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.ui.components.ErrorUiState
import com.praeee.jetpackcomposeapp.ui.components.Loader
import com.praeee.jetpackcomposeapp.ui.components.NotFoundKeyword
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.TAG
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinViewStateValue
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.BottomSheetDetail
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.CoinListItemPortrait
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.InviteFriendsItemPortrait
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.SearchText
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.TopLankListItem
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.TopRankTitlePortrait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContentPortrait(
    state: CoinViewStateValue,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {

    Log.d(TAG, "inDiceFriendIndex :: ${state.inDiceFriendIndex.toString()}")


    val (text, setText) = remember { mutableStateOf("") }

    val activityActionSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var openActivityActionSheet by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state.isOpenBottomSheet) {
        openActivityActionSheet = state.isOpenBottomSheet
    }

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
                        onEvent.invoke(CoinEvent.OnCloseBottomSheet(false))
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

            LazyColumn(
                modifier = modifier
            ) {

                when {
                    !state.coinSearchListState?.coins.isNullOrEmpty() -> {
                        item {
                            Text(
                                text = "Buy, sell and hold crypto",
                                modifier = modifier
                                    .padding(start = 16.dp),
                                maxLines = 1,
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                ),
                                color = Color.Black
                            )
                        }
                        itemsIndexed(
                            state.coinSearchListState?.coins ?: emptyList()
                        ) { index, coinList ->

                            CoinListItemPortrait(
                                coin = coinList,
                                modifier = modifier,
                                onEvent = onEvent
                            )

                            if (state.inDiceFriendIndex?.contains(index) == true) {
                                InviteFriendsItemPortrait("Invite your friend")
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
                                        TopRankTitlePortrait()
                                        Row(
                                            modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .padding(8.dp)
                                        ) {
                                            rowItems.forEach { coin ->
                                                TopLankListItem(coin, modifier, onEvent)
                                            }
                                            // Fill empty space if the row is not completely filled
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
                                text = "Buy, sell and hold crypto",
                                modifier = modifier
                                    .padding(start = 16.dp),
                                maxLines = 1,
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                ),
                                color = Color.Black
                            )
                        }



                        itemsIndexed(state.coinListState?.coins ?: emptyList()) { index, coinList ->
                            Log.d(TAG, "coinListState index:: ${index.toString()}")


                            CoinListItemPortrait(
                                coin = coinList,
                                modifier = modifier,
                                onEvent = onEvent
                            )

                            if (state.inDiceFriendIndex?.contains(index) == true) {
                                InviteFriendsItemPortrait("https://careers.lmwn.com/")
                            }

                        }
                    }
                    else -> {}
                }

            }

        }

    }
}