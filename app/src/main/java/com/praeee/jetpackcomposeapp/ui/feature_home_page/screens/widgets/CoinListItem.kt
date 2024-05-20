package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.components.image.CoilImage
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListItemLandscape(
    coin: CoinState,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {

    val color = MaterialTheme.colorScheme

    Card(
        colors = CardDefaults.cardColors(
            containerColor = color.onSecondaryContainer,
        ),
        onClick = {
            onEvent(CoinEvent.OnClickItemCoin(coin))
        },
        modifier = modifier
            .height(100.dp)
            .padding(8.dp),
    ) {
        Row(
            modifier = modifier
                .height(100.dp)
        ) {
            CoilImage(
                imageUrl = coin.iconUrl ?: "",
                sizeImage = 100
            )
            Column(
                modifier = modifier
                    .weight(2f)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = coin.name ?: "",
                    modifier = modifier
                        .padding(top = 8.dp)
                        .weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = color.primary
                )
                Text(
                    text = coin.symbol ?: "",
                    modifier = modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp)
                        .weight(1f),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFACACAC)
                )
            }
            Column(
                modifier = modifier
                    .weight(2f)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "$${coin.price}",
                    modifier = modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = color.primary
                )
                Row(
                    modifier = modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        modifier = modifier
                            .height(16.dp),
                        colorFilter = if (coin.lowVolume == true) ColorFilter.tint(Color(0xFF1ABD66)) else ColorFilter.tint(
                            Color(0xFFFF0000)
                        ),
                        painter = if (coin.lowVolume == true) painterResource(id = (R.drawable.up_icon)) else painterResource(
                            id = (R.drawable.down_icon)
                        ),
                        contentDescription = "icon"
                    )
                    Text(
                        text = coin.change ?: "",
                        modifier = modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = if (coin.lowVolume == true) Color(0xFF1ABD66) else Color(0xFFFF0000)
                    )
                }

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListItemPortrait(
    coin: CoinState,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {
    val color = MaterialTheme.colorScheme

    Card(
        colors = CardDefaults.cardColors(
            containerColor = color.onSecondaryContainer,
        ),
        onClick = {
            onEvent(CoinEvent.OnClickItemCoin(coin))
        },
        modifier = modifier
            .height(100.dp)
            .padding(8.dp),
    ) {
        Row(
            modifier = modifier
                .height(100.dp)
        ) {
            CoilImage(
                imageUrl = coin.iconUrl ?: "",
                sizeImage = 100
            )
            Column(
                modifier = modifier
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Text(
                    text = coin.name ?: "",
                    modifier = modifier
                        .padding(top = 8.dp)
                        .weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = color.primary
                )
                Text(
                    text = coin.symbol ?: "",
                    modifier = modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp)
                        .weight(1f),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFACACAC)
                )
            }
            Column(
                modifier = modifier
                    .weight(2f)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "$${coin.price}",
                    modifier = modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = color.primary
                )
                Row(
                    modifier = modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        modifier = modifier
                            .height(16.dp),
                        colorFilter = if (coin.lowVolume == true) ColorFilter.tint(Color(0xFF1ABD66)) else ColorFilter.tint(
                            Color(0xFFFF0000)
                        ),
                        painter = if (coin.lowVolume == true) painterResource(id = (R.drawable.up_icon)) else painterResource(
                            id = (R.drawable.down_icon)
                        ),
                        contentDescription = "icon"
                    )
                    Text(
                        text = coin.change ?: "",
                        modifier = modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = if (coin.lowVolume == true) Color(0xFF1ABD66) else Color(0xFFFF0000)
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
        CoinListItemLandscape(CoinState(), onEvent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListItemPortraitPreview() {
    JetpackComposeAppTheme {
        CoinListItemPortrait(CoinState(), onEvent = {})
    }
}