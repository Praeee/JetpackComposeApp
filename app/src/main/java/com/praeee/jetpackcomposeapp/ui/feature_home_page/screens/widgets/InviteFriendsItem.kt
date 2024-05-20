package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteFriendsItemPortrait(text: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF9FCCF6),
        ),
        onClick = {
            context.startActivity(shareIntent)
        },
        modifier = Modifier
            .height(100.dp)
            .padding(8.dp),

        ) {
        Row(
            modifier = Modifier
                .height(100.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp),
                painter = painterResource(id = (R.drawable.gift_icon)),
                contentDescription = "icon"
            )
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .align(Alignment.CenterVertically),
            ) {
                val textString = buildAnnotatedString {
                    append("You can earn \$10 when you invite a friend to buy crypto.")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF1D8EF6),
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(" Invite your friend")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = textString,
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentHeight()
                            .align(Alignment.CenterVertically),
                        maxLines = 2,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                        color = Color.Black
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteFriendsItemLandscape(
    text: String,
    modifier: Modifier = Modifier
) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF9FCCF6),
        ),
        onClick = {
            context.startActivity(shareIntent)
        },
        modifier = modifier
            .width(300.dp)
            .height(100.dp)
            .padding(8.dp),

        ) {
        Row(
            modifier = modifier
                .height(100.dp)
        ) {
            Image(
                modifier = modifier
                    .padding(8.dp)
                    .height(70.dp),
                painter = painterResource(id = (R.drawable.gift_icon)),
                contentDescription = "icon"
            )
            Column(
                modifier = modifier
                    .height(70.dp)
                    .align(Alignment.CenterVertically),
            ) {
                val textString = buildAnnotatedString {
                    append("You can earn \$10 when you invite a friend to buy crypto.")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF1D8EF6),
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(" Invite your friend")
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = textString,
                        modifier = modifier
                            .wrapContentHeight()
                            .align(Alignment.CenterVertically),
                        maxLines = 3,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                        color = Color.Black
                    )
                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun InviteFriendsItemPortraitPreview() {
    JetpackComposeAppTheme {
        InviteFriendsItemPortrait("hello")
    }
}

@Preview(showBackground = true)
@Composable
fun InviteFriendsItemLandscapePreview() {
    JetpackComposeAppTheme {
        InviteFriendsItemLandscape("hello")
    }
}