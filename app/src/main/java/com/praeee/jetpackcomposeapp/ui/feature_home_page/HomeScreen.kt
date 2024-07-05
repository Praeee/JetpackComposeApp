package com.praeee.jetpackcomposeapp.ui.feature_home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

const val TAG = "HomeScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navEvent: HomeNavEvent,
) {

    HomeScreenContent(
        navEvent = navEvent
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navEvent: HomeNavEvent,
) {

    val color = MaterialTheme.colorScheme

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = color.onSecondaryContainer,
                ),
                onClick = {
                    navEvent.onNavigateToNews.invoke()
                },
                modifier = modifier
                    .wrapContentHeight()
                    .padding(8.dp),
            ) {
                Column {
                    Text(
                        text = "News",
                        modifier = modifier
                            .padding(top = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = color.onTertiary
                    )
                }

            }

        }

    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    JetpackComposeAppTheme {
        HomeScreen(
            navEvent = HomeNavEvent()
        )
    }
}








