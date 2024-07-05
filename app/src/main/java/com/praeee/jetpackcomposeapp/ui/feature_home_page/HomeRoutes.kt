package com.praeee.jetpackcomposeapp.ui.feature_home_page

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeRoute(
    navEvent: HomeNavEvent,
) {
    HomeScreen(
        navEvent = navEvent,
    )
}