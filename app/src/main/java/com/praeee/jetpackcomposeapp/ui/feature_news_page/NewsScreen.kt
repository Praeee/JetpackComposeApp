package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.ui.components.BoxWithSwipeRefresh
import com.praeee.jetpackcomposeapp.ui.feature_home_page.HomeNavEvent
import com.praeee.jetpackcomposeapp.ui.feature_news_page.widgets.ArticleItemCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    navEvent: NewsNavEvent,
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

    val articleList = state.articleList?.articleList ?: emptyList()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {
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
                    LazyColumn(
                        modifier = modifier
                    ) {

                        itemsIndexed(
                            articleList
                        ) { _ , article ->
                            ArticleItemCard(article = article)
                        }

                    }
                }
            }
        }
    }


}