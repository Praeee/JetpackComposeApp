package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

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

@Composable
fun NewsScreenContent(
    state: NewsUiState,
    modifier: Modifier = Modifier,
    onEvent: (NewsEvent) -> Unit,
) {

}