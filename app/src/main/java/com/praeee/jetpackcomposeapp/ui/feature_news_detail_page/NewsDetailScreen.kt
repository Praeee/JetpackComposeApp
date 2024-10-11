package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.components.BoxWithSwipeRefresh
import com.praeee.jetpackcomposeapp.ui.components.TopAppBar
import com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.widgets.ArticleDetailCard
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.widgets.ArticleItemCard
import com.praeee.jetpackcomposeapp.ui.sharedviewmodel.NewsSharedViewModel
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsDetailScreen(
    newsSharedViewModel: NewsSharedViewModel,
    navEvent: (NewsDetailNavEvent) -> Unit = {},
    viewModel: NewsDetailViewModel = hiltViewModel()
) {

    val state = viewModel.uiState
    val onEvent = viewModel::onEvent
    NewsDetailScreenContent(
        state = state,
        onEvent = onEvent,
        newsSharedViewModel = newsSharedViewModel,
        onClickBack = {
            navEvent.invoke(NewsDetailNavEvent.OnNavigateUp)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreenContent(
    state: NewsDetailUiState,
    modifier: Modifier = Modifier,
    newsSharedViewModel: NewsSharedViewModel,
    onEvent: (NewsDetailEvent) -> Unit,
    onClickBack: () -> Unit,
    ) {

    val color = MaterialTheme.colorScheme

    var isRefreshing by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val article = state.article

    LaunchedEffect(key1 = newsSharedViewModel.article) {
        newsSharedViewModel.article?.let {
            onEvent.invoke(NewsDetailEvent.SetArticleDetail(it))
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(color = color.onSecondaryContainer)
    ) {


        Column {
            Row {
                TopAppBar(
                    titleText = stringResource(id = R.string.news_detail_title),
                    navigationIcon = painterResource(id = R.drawable.ic_back_arrow),
                    onNavigationClick = {
                        onClickBack.invoke()
                    },
                )
            }
            BoxWithSwipeRefresh(
                onSwipe = {
                    isRefreshing = true
//                    onEvent.invoke(NewsEvent.PullToRefresh)
                    coroutineScope.launch {
                        delay(2000)
                        isRefreshing = false
                    }
                },
                isRefreshing = isRefreshing,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    state.article?.let {
                        ArticleDetailCard(state.article)
                    }
                }
            }
        }
    }


}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)


@Preview(showBackground = true)
@Composable
fun ArticleItemCardPreview() {
    JetpackComposeAppTheme {
        ArticleItemCard(
            ArticleUiState(
                title = "Bitcoin slumps below \$59,000 amid market uncertainty",
                description = "Bitcoin’s (BTC) value dropped below $59,000 on Thursday, trading at $58,827. Market data shows that Bitcoin has fallen 3.38% in… Continue reading Bitcoin slumps below $59,000 amid market uncertainty\nThe post Bitcoin slumps below $59,000 amid market uncertain",
                publishedAt = "19 Feb 2022, 19:36"
            ),
            onClickItemArticle = {}
        )
    }
}
