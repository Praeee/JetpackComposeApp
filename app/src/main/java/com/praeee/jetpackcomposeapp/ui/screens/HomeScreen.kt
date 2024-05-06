package com.praeee.jetpackcomposeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.praeee.jetpackcomposeapp.data.entity.Article
import com.praeee.jetpackcomposeapp.data.entity.NewsResponse
import com.praeee.jetpackcomposeapp.ui.components.Loader
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
            Loader()
        }
        is ResourceState.Error -> {
            Log.d(TAG,"Error")
            val error = (newsRes as ResourceState.Error)
            Log.d(TAG,"Error response $error")
        }
        is ResourceState.Success -> {
            val response = (newsRes as ResourceState.Success).data
            Log.d(TAG,"Success response ${response.status}${response.totalResults}")

            HomeScreenContent(response)
        }
        else -> {
            Loader()
        }
    }
}

@Composable
fun HomeScreenContent(response: NewsResponse) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "hello home screen")

    }
}

@Composable
fun NewList(response: NewsResponse) {
    LazyColumn {
//        items(response.articles) { article ->
//
//
//        }
    }

}

//@Composable
//fun Normal

@Preview
@Composable
fun HomeScreenContentPreview () {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}