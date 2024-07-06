package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.ui.components.BoxWithSwipeRefresh
import com.praeee.jetpackcomposeapp.ui.components.image.CoilImage
import com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.widgets.ArticleDetailCard
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsEvent
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
//    Log.d("newsDetail", articleUiState.toString())
    NewsDetailScreenContent(
        state = state,
        onEvent = onEvent,
        newsSharedViewModel = newsSharedViewModel
    )
}

@Composable
fun NewsDetailScreenContent(
    state: NewsDetailUiState,
    modifier: Modifier = Modifier,
    newsSharedViewModel: NewsSharedViewModel,
    onEvent: (NewsDetailEvent) -> Unit,
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
        modifier = Modifier
            .fillMaxSize()
            .background(color = color.onSecondaryContainer)
    ) {

        Column {
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
                    modifier = Modifier
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
fun CoinListItemLandscapePreview() {
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
