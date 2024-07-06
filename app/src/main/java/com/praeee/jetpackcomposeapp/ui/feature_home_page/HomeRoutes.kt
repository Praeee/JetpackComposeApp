package com.praeee.jetpackcomposeapp.ui.feature_home_page

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
    navEvent: HomeNavEvent,
) {
    HomeScreen(
        navEvent = navEvent,
    )
}