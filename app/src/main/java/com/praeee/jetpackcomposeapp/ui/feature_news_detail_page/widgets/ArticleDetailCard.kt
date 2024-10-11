package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page.widgets

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.components.image.CoilImage
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsEvent
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun ArticleDetailCard(
    article: ArticleUiState,
    modifier: Modifier = Modifier,
) {
    val color = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }

    Column(
        modifier = modifier
            .padding(bottom = 8.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CoilImage(
                imageUrl = article.urlToImage ?: "",
            )
        }
        Column(
            modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = article.source?.name ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = color.onPrimary
            )
            Text(
                text = article.title ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = color.onTertiary
            )
            Text(
                text = article.description ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = color.onTertiaryContainer
            )
            Text(
                text = article.content ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = color.onTertiaryContainer
            )
            Text(
                text = article.publishedAt ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = color.onPrimary
            )
            article.url?.let {
                val localContext = LocalContext.current
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            val urlIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(article.url)
                            )
                            localContext.startActivity(urlIntent)
                        }
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = stringResource(id = R.string.go_to_website_title),
                        modifier = modifier
                            .padding(vertical = 32.dp)
                            .align(Alignment.Bottom)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color(0xFF1D8EF6)
                    )
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
        ArticleDetailCard(
            ArticleUiState(
                title = "Bitcoin slumps below \$59,000 amid market uncertainty",
                description = "Bitcoin’s (BTC) value dropped below $59,000 on Thursday, trading at $58,827. Market data shows that Bitcoin has fallen 3.38% in… Continue reading Bitcoin slumps below $59,000 amid market uncertainty\nThe post Bitcoin slumps below $59,000 amid market uncertain \nBitcoin’s (BTC) value dropped below \$59,000 on Thursday, trading at \$58,827. Market data shows that Bitcoin has fallen 3.38% in… Continue reading Bitcoin slumps below \$59,000 amid market uncertainty\n" +
                        "The post Bitcoin slumps below \$59,000 amid market uncertain",
                publishedAt = "19 Feb 2022, 19:36"
            ),
        )
    }
}
