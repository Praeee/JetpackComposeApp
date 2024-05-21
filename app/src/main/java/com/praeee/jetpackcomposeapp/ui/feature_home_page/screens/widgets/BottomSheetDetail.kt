package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.components.image.CoilImage
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinDetailViewState
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme


@Composable
fun BottomSheetDetail(
    coin: CoinDetailViewState,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme

    Column( modifier = modifier
        .background(color.background)
    ) {
        Row(
            modifier = modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            CoilImage(
                imageUrl = coin.iconUrl ?: "",
                sizeImage = 100
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row {
                    Text(
                        text = coin.name ?: "",
                        modifier = modifier
                            .padding(top = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = color.primary
                    )
                    Text(
                        text = coin.symbol ?: "-",
                        modifier = modifier
                            .padding(top = 8.dp, start = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = color.primary
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.price_title),
                        modifier = modifier
                            .padding(top = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = color.primary
                    )
                    Text(
                        text = coin.price ?: "",
                        modifier = modifier
                            .padding(top = 8.dp, start = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = color.primary
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.market_cap_title),
                        modifier = modifier
                            .padding(top = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = color.primary
                    )
                    Text(
                        text = coin.marketCap ?: "",
                        modifier = modifier
                            .padding(top = 8.dp, start = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = color.primary
                    )
                }
            }
        }

        if (coin.description.isNullOrBlank() || coin.description.isEmpty()) {
            Text(
                text = stringResource(id = R.string.no_description_title),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = color.onPrimary
            )
        } else {
            Text(
                text = coin.description,
                modifier = modifier
                    .padding(16.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = color.onPrimary
            )
        }


        val localContext = LocalContext.current

        if (coin.websiteUrl != null) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(coin.websiteUrl)
                        )
                        localContext.startActivity(urlIntent)
                    }
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = stringResource(id = R.string.go_to_website_title),
                    modifier = modifier
                        .padding(vertical = 24.dp)
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
fun BottomSheetDetailPreview() {
    JetpackComposeAppTheme {
        BottomSheetDetail(CoinDetailViewState())
    }
}