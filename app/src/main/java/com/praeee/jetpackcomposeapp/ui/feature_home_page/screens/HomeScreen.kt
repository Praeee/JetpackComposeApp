package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel.CoinViewModel
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.orientationScreen.HomeScreenContentLandscape
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets.orientationScreen.HomeScreenContentPortrait
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

const val TAG = "HomeScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    coinViewModel: CoinViewModel = hiltViewModel()
) {

    val state = coinViewModel.uiState
    val onEvent = coinViewModel::onEvent

    val context = LocalContext.current
    val orientation = context.resources.configuration.orientation

    when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            HomeScreenContentPortrait(
                state = state,
                onEvent = onEvent
            )
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            HomeScreenContentLandscape(
                state = state,
                onEvent = onEvent
            )
        }
        else -> {}
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}








