package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun TopRankTitlePortrait() {
    val color = MaterialTheme.colorScheme

    val textString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = color.primary, fontWeight = FontWeight.Medium)) {
            append(stringResource(id = R.string.top_title))
        }
        withStyle(style = SpanStyle(color = color.onSecondary, fontWeight = FontWeight.Medium)) {
            append(stringResource(id = R.string.top_3_title))
        }
    }

    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = textString,
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.CenterVertically),
            maxLines = 2,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            color = color.primary
        )

        Text(
            text = stringResource(id = R.string.rank_crypto_title),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            maxLines = 2,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            color =  color.primary
        )
    }

}


@Composable
fun TopRankTitleLandscape() {
    val color = MaterialTheme.colorScheme

    val textString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = color.primary, fontWeight = FontWeight.Medium)) {
            append(stringResource(id = R.string.top_title))
        }
        withStyle(style = SpanStyle(color = Color(0xFFFF0000), fontWeight = FontWeight.Medium)) {
            append(stringResource(id = R.string.top_3_title))
        }
    }

    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = textString,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            maxLines = 2,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            color =  color.primary
        )

        Text(
            text = stringResource(id = R.string.rank_crypto_title),
            modifier = Modifier
                .align(Alignment.CenterVertically),
            maxLines = 2,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            color =  color.primary
        )
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
fun TopRankTitlePortraitPreview() {
    JetpackComposeAppTheme {
        TopRankTitlePortrait()
    }
}

@Preview(showBackground = true)
@Composable
fun TopRankTitleLandscapePreview() {
    JetpackComposeAppTheme {
        TopRankTitleLandscape()
    }
}

