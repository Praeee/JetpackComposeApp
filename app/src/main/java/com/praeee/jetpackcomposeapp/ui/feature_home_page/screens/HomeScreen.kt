package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.data.entity.Article
import com.praeee.jetpackcomposeapp.data.entity.Source
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel.CoinViewModel
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    coinViewModel: CoinViewModel = hiltViewModel()

) {
//    val newsRes by coinViewModel.news.collectAsState()
//    Log.d(TAG,"newsRes${newsRes}")

    HomeScreenContent()


//    when (newsRes) {
//        is ResourceState.Success -> {
//            val response = (newsRes as ResourceState.Success).data
//            Log.d(TAG,"Success response ${response.status}${response.totalResults}")
//            HomeScreenContent(response)
//        }
//        is ResourceState.Loading -> {
//            Log.d(TAG,"Loading")
////            Loader()
//        }
//        is ResourceState.Error -> {
//            Log.d(TAG,"Error")
//            val error = (newsRes as ResourceState.Error)
//            Log.d(TAG,"Error response $error")
//        }
//
//        else -> {
////            Loader()
//        }
//    }

}

@Composable
fun HomeScreenContent() {//response: NewsResponse) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {

            SearchText()

            CoinListItem()
            CoinListItem()

            TopLankListItem()

            InviteFriendsItem("Invite your friend")
        }


//        Text(text = "hello home screen")
//        NewList(list = response.articles ?: listOf())

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
fun SearchText() {
    TextField(
        value = "",
        onValueChange = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            Text(
                text = "Search",
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = Color(0xFFACACAC)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "icon_expend",
                modifier = Modifier
                    .height(14.dp),
                    tint = Color(0xFFCCCCCC)
            )
        },
        singleLine = false
    )
}

@Composable
fun CoilImage(
    imageUrl: String,
    sizeImage: Int,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    )

    Box(
        modifier = Modifier
            .size(sizeImage.dp)
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,
    ) {
//        Image(
//            painter = painter,
//            contentDescription = "icon",
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
        AsyncImage(
            model = imageUrl,
            contentDescription = "icon",
            modifier = Modifier
//                .wrapContentHeight()
                .fillMaxWidth()
                .size(sizeImage.dp),
            placeholder = painterResource(id = (R.drawable.placeholder_image)),
            error = painterResource(id = (R.drawable.placeholder_image)),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet() {
    val mapActionSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    ModalBottomSheet(
        sheetState = mapActionSheetState,
        dragHandle = null,
        onDismissRequest = {},
    ) {

    }
}

@Composable
fun CoinListItem() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F3F3),
        ),

        modifier = Modifier
            .height(100.dp)
            .padding(8.dp),

        ) {
        Row(
            modifier = Modifier
                .height(100.dp)
        ) {
            CoilImage(
                imageUrl = "https://cdn.coinranking.com/lhbmnCedl/5690.png",
                sizeImage = 100
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Bitcoin",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
                Text(
                    text = "BTC",
                    modifier = Modifier
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
                modifier = Modifier
                    .weight(2f)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "$187.000",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Color.Black
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        modifier = Modifier
                            .height(16.dp),
                        colorFilter = ColorFilter.tint(Color(0xFF1ABD66)),
                        painter = painterResource(id = (R.drawable.up_icon)),
                        contentDescription = "icon"
                    )
                    Text(
                        text = "0.72",
                        modifier = Modifier,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color(0xFF1ABD66)
                    )
                }

            }
        }
    }

}

@Composable
fun TopLankListItem() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F3F3),
        ),
        modifier = Modifier
            .height(150.dp)
            .width(100.dp)
            .padding(3.dp)
        ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            CoilImage(
                imageUrl = "https://lmwp.line-scdn.net/static/images/lineman-square-logo.png",
                sizeImage = 70
            )
            Text(
                text = "BTC",
                modifier = Modifier
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
                text = "Bitcoin",
                modifier = Modifier
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
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Image(
                    modifier = Modifier
                        .height(14.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF1ABD66)),
                    painter = painterResource(id = (R.drawable.up_icon)),
                    contentDescription = "icon"
                )
                Text(
                    text = "0.72",
                    modifier = Modifier,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Color(0xFF1ABD66)
                )
            }
        }
    }

}

@Composable
fun NotFoundKeyword() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sorry",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            maxLines = 1,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            style = TextStyle.Default,
            color = Color.Black
        )
        Text(
            text = "No result match the keyword",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            textAlign = TextAlign.Center,
            style = TextStyle.Default,
            fontSize = 18.sp,
            color = Color(0xFFACACAC)
        )
    }
}

@Composable
fun NewListItem(article: Article) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        ) {
        Row {
            AsyncImage(
                model = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg",
                contentDescription = null,
            )
        }
        Column {
            Text(
                text = "Bitcoin",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = TextStyle.Default
            )
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteFriendsItem(text: String) {
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
                    withStyle(style = SpanStyle(color = Color(0xFF1D8EF6), fontWeight = FontWeight.Medium)) {
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


@Preview
@Composable
fun HomeScreenContentPreview() {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF444444)
@Composable
fun NewsListItemPreview() {
    JetpackComposeAppTheme {
        NewListItem(
            Article(
                source = Source(

                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAppTheme {
        CoilImage(imageUrl = "" , sizeImage = 100)
    }
}

@Preview(showBackground = true)
@Composable
fun NotFoundKeywordPreview() {
    JetpackComposeAppTheme {
        NotFoundKeyword()
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListItemPreview() {
    JetpackComposeAppTheme {
        CoinListItem()
    }
}

@Preview(showBackground = true)
@Composable
fun InviteFriendsItemPreview() {
    JetpackComposeAppTheme {
        InviteFriendsItem("hello")
    }
}

@Preview(showBackground = true)
@Composable
fun TopLankListItemPreview() {
    JetpackComposeAppTheme {
        TopLankListItem()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextPreview() {
    JetpackComposeAppTheme {
        SearchText()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    JetpackComposeAppTheme {
        BottomSheet()
    }
}
