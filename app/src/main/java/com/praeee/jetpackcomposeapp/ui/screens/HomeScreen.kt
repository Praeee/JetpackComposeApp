package com.praeee.jetpackcomposeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.load.model.GlideUrl
import com.praeee.jetpackcomposeapp.data.entity.Article
import com.praeee.jetpackcomposeapp.data.entity.NewsResponse
import com.praeee.jetpackcomposeapp.data.entity.Source
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
        NewList(list = response.articles ?: listOf())

    }
}

@Composable
fun NewList(list: List<Article>) {
    Box {
        LazyColumn {
            items(list) { article ->
                NewListItem(article)
            }
        }
    }


}

@Composable
fun NewListItem(article: Article) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue,
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

    ) {
        Column {
//            loadImage()

            Text(
                text = article.title?:"",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = TextStyle.Default
            )
        }


    }

}
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun loadImage() {
//    GlideUrl("https://apnews.com/article/israel-aljazeera-hamas-gaza-war-eba9416aea82f505ab908ee60d1de5e4")
//
//}


//@Composable
//fun Normal

@Preview
@Composable
fun HomeScreenContentPreview () {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF444444)
@Composable
fun NewsListItemPreview () {
    JetpackComposeAppTheme {
        NewListItem(
            Article(
                source = Source(

                )
            )
        )
    }
}