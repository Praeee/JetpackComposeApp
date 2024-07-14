package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.components.BoxWithSwipeRefresh
import com.praeee.jetpackcomposeapp.ui.components.ErrorUiState
import com.praeee.jetpackcomposeapp.ui.components.GoToTop
import com.praeee.jetpackcomposeapp.ui.components.Loader
import com.praeee.jetpackcomposeapp.ui.components.TopAppBar
import com.praeee.jetpackcomposeapp.ui.components.isScrollingUp
import com.praeee.jetpackcomposeapp.ui.feature_news_page.widgets.ArticleItemCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    navEvent: (NewsNavEvent) -> Unit = {},
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    val state = newsViewModel.uiState
    val onEvent = newsViewModel::onEvent

    NewsScreenContent(
        state = state,
        onEvent = onEvent,
        onClickItemArticle = {
            navEvent.invoke(NewsNavEvent.OnNavigateToArticle(it))
        },
        onClickBack = {
            navEvent.invoke(NewsNavEvent.OnNavigateUp)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenContent(
    state: NewsUiState,
    modifier: Modifier = Modifier,
    onEvent: (NewsEvent) -> Unit,
    onClickItemArticle: (ArticleUiState) -> Unit,
    onClickBack: () -> Unit,
) {

    val color = MaterialTheme.colorScheme

    var isRefreshing by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val articleList = state.articleList?.articleList ?: emptyList()

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color.onSecondaryContainer)
    ) {

        Column {

            if (state.isLoading) {
                Loader()
            }

            if (state.isError) {
                ErrorUiState(
                    onClick = {
                        onEvent.invoke(NewsEvent.OnErrorUi)
                    }
                )
            }

            Row {
                TopAppBar(
                    titleText = stringResource(id = R.string.news_title),
                    navigationIcon = painterResource(id = R.drawable.ic_back_arrow),
                    onNavigationClick = {
                        onClickBack.invoke()
                    },
                )
            }
            BoxWithSwipeRefresh(
                onSwipe = {
                    isRefreshing = true
                    onEvent.invoke(NewsEvent.PullToRefresh)
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
                        modifier = modifier,
                        state = listState
                    ) {

                        itemsIndexed(
                            articleList
                        ) { index , article ->
                            ArticleItemCard(
                                article = article,
                                onClickItemArticle = {
                                    onClickItemArticle.invoke(it)
                                },
                                modifier = modifier.semantics { contentDescription = "ArticleItem_$index"},
                            )
                        }

                    }
                    Column {
                        AnimatedVisibility(visible = !listState.isScrollingUp(), enter = fadeIn(), exit = fadeOut()) {
                            GoToTop(
                                modifier = modifier
                            ) {
                                scope.launch {
                                    listState.scrollToItem(0)
                                }
                            }
                        }
                    }
                }

            }
        }
    }


}