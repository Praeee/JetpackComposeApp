package com.praeee.jetpackcomposeapp.ui.feature_news_page.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.ui.components.image.CoilImage
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsEvent
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun ArticleItemCard(
    article: ArticleUiState,
    modifier: Modifier = Modifier,
    onClickItemArticle: (ArticleUiState) -> Unit,
) {
    val color = MaterialTheme.colorScheme

    Card(
        colors = CardDefaults.cardColors(
            containerColor = color.onSecondaryContainer,
        ),
        onClick = {
            onClickItemArticle.invoke(article)

        },
        modifier = modifier
            .padding(bottom = 8.dp, top = 8.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
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
            modifier = modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = article.title ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                overflow = TextOverflow.Ellipsis,
                color = color.onTertiary
            )
            Text(
                text = article.description ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                maxLines = 3,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                overflow = TextOverflow.Ellipsis,
                color = color.onTertiaryContainer
            )
            Text(
                text = article.publishedAt ?: "",
                modifier = modifier
                    .padding(top = 8.dp),
                maxLines = 3,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                overflow = TextOverflow.Ellipsis,
                color = color.onPrimary
            )
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
        ArticleItemCard(
            ArticleUiState(
                title = "Bitcoin slumps below \$59,000 amid market uncertainty",
                description = "Bitcoin’s (BTC) value dropped below $59,000 on Thursday, trading at $58,827. Market data shows that Bitcoin has fallen 3.38% in… Continue reading Bitcoin slumps below $59,000 amid market uncertainty\nThe post Bitcoin slumps below $59,000 amid market uncertain",
                publishedAt = "19 Feb 2022, 19:36"
            ),
            onClickItemArticle = {}
        )
    }
}
