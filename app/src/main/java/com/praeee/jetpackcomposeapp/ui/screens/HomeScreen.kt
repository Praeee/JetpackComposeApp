package com.praeee.jetpackcomposeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import com.praeee.jetpackcomposeapp.ui.viewmodel.NewsViewModel
import com.praeee.jetpackcomposeapp.utilities.ResourceState

const val TAG = "HomeScreen"
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()

) {
    val newsRes by newsViewModel.news.collectAsState()
    Log.d(TAG,"newsRes${newsRes}")

    when (newsRes) {
        is ResourceState.Loading -> {
            Log.d(TAG,"Loading")
        }
        is ResourceState.Error -> {
            Log.d(TAG,"Error")

        }
        is ResourceState.Success -> {
            Log.d(TAG,"Success")

        }
    }
    HomeScreenContent()
}

@Composable
fun HomeScreenContent (
//    newsViewModel: NewsViewModel
) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "hello home screen")

    }
}
@Preview
@Composable
fun HomeScreenContentPreview () {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}