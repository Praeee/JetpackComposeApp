package com.praeee.jetpackcomposeapp.ui.feature_news_page.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.ui.components.image.CoilImage
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun ArticleItemCard(
    article: ArticleUiState,
    modifier: Modifier = Modifier,
//    onEvent: (CoinEvent) -> Unit,
) {
    val color = MaterialTheme.colorScheme

    Card(
        colors = CardDefaults.cardColors(
            containerColor = color.onSecondaryContainer,
        ),
        onClick = {
//            onEvent(CoinEvent.OnClickItemCoin(coin))
        },
        modifier = modifier
            .wrapContentHeight()
            .padding(8.dp),
    ) {
        Row(
            modifier = modifier
                .height(100.dp)
        ) {
            CoilImage(
                imageUrl = article.urlToImage ?: "",
                sizeImage = 240
            )
        }
        Column {
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
                color = color.onTertiary
            )
        }
    }

}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "DefaultPreviewDark"
//)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    name = "DefaultPreviewLight"
//)



//@Preview(showBackground = true)
//@Composable
//fun CoinListItemLandscapePreview() {
//    JetpackComposeAppTheme {
//        ArticleItemCard(ArticleUiState(
//            title = "Brazil data protection authority bans Meta from training AI models with data originating in the country"
//        ), //onEvent = {})
//    }
//}
