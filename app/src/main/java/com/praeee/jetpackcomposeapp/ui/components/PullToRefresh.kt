package com.praeee.jetpackcomposeapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxWithSwipeRefresh(
    onSwipe: () -> Unit,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {

    val state = rememberPullToRefreshState()

    LaunchedEffect(state.isRefreshing) {
        if (state.isRefreshing) {
            onSwipe()
        }
    }

    LaunchedEffect(isRefreshing) {
        if (!isRefreshing) {
            state.endRefresh()
        }
    }

    Box(
        modifier = modifier
            .nestedScroll(state.nestedScrollConnection)
            .fillMaxSize()
    ) {
        content()
        if (isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = state
            )
        }

    }

}