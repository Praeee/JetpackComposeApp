package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun TopLankListItem(
    coin: CoinState,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F3F3),
        ),
        modifier = modifier
            .height(150.dp)
            .width(100.dp)
            .padding(4.dp)
            .clickable {
                onEvent(CoinEvent.OnClickItemCoin(coin))
            }
    ) {
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CoilImage(
                    imageUrl = coin.iconUrl ?: "",
                    sizeImage = 70
                )
            }

            Text(
                text = coin.symbol ?: "",
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            Text(
                text = coin.name ?: "",
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = Color(0xFFACACAC)
            )
            Row(
                modifier = modifier
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Image(
                    modifier = modifier
                        .height(14.dp),
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = if (coin.lowVolume == true) Color(0xFF1ABD66) else Color(0xFFFF0000)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun TopLankListItemPreview() {
    JetpackComposeAppTheme {
        TopLankListItem(CoinState(), onEvent = {})
    }
}
