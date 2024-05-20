package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.praeee.jetpackcomposeapp.ui.components.ErrorUiState
import com.praeee.jetpackcomposeapp.ui.components.Loader
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinDetailViewState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinListState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinState
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinViewStateValue
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent
import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.viewmodel.CoinViewModel
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

const val TAG = "HomeScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    coinViewModel: CoinViewModel = hiltViewModel()

) {

    val state by coinViewModel.uiState.collectAsState()
    val onEvent = coinViewModel::onEvent


    Log.d(TAG, "isLoading :: ${state.isLoading}")
    Log.d(TAG, "coinListState :: ${state.coinListState.toString()}")
    Log.d(TAG, "coinTopRank :: ${state.coinTopRank.toString()}")
    Log.d(TAG, "coinDetailState :: ${state.coinDetailState.toString()}")
    Log.d(TAG, "isOpenBottomSheet :: ${state.isOpenBottomSheet.toString()}")

    HomeScreenContent(
        state = state,
        onEvent = onEvent
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: CoinViewStateValue,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {

    val (text, setText) = remember { mutableStateOf("") }

    val activityActionSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var openActivityActionSheet by rememberSaveable { mutableStateOf(state.isOpenBottomSheet) }

    LaunchedEffect(state.isOpenBottomSheet) {
        openActivityActionSheet = state.isOpenBottomSheet
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column {

            SearchText(
                value = text,
                onValueChange = setText,
                onFocusLost = { newText ->
                    onEvent.invoke(CoinEvent.OnSearchText(newText))
                }
            )

            LazyColumn(
                modifier = modifier
            ) {

                if (openActivityActionSheet) {
                    item {
                        ModalBottomSheet(
                            sheetState = activityActionSheetState,
                            dragHandle = null,
                            onDismissRequest = {
                                openActivityActionSheet = false
                            },
                        ) {
                            if (state.coinDetailState != null) {
                                BottomSheetDetail(coin = state.coinDetailState)
                            }
                        }
                    }
                }

                if (state.isLoading) {
                    item {
                        Loader()
                    }
                }

                if (state.isError) {
                    item {
                        ErrorUiState(
                            onClick = {
                                onEvent.invoke(CoinEvent.OnErrorUi(true))
                            }
                        )
                    }

                }

                if (state.coinTopRank != null && text.isEmpty()) {

                    itemsIndexed(state.coinTopRank!!.chunked(3)) { _, rowItems ->
                        Row(
                            modifier =
                            modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column {
                                TopRankTitle()
                                Row(
                                    modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    rowItems.forEach { coin ->
                                        TopLankListItem(coin, modifier, onEvent)
                                    }
                                    // Fill empty space if the row is not completely filled
                                    if (rowItems.size < 3) {
                                        for (i in 1..(3 - rowItems.size)) {
                                            Spacer(modifier = Modifier.weight(1f))
                                        }
                                    }
                                }

                            }

                        }
                    }
                }

                if (!state.isLoading && !state.isError) {
                    item {
                        Text(
                            text = "Buy, sell and hold crypto",
                            modifier = modifier
                                .padding(start = 16.dp),
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                            ),
                            color = Color.Black
                        )
                    }
                }

                if (state.coinSearchListState?.coins.isNullOrEmpty()) {
                    itemsIndexed(state.coinListState?.coins ?: emptyList()) { index, coinList ->

                        CoinListItem(
                            coin = coinList,
                            modifier = modifier,
                            onEvent = onEvent
                        )

                        if (state.inDiceFriendIndex?.contains(index) == true) {
                            InviteFriendsItem("Invite your friend")
                        }

                    }
                } else {
                    itemsIndexed(state.coinSearchListState?.coins ?: emptyList()) { index, coinList ->

                        CoinListItem(
                            coin = coinList,
                            modifier = modifier,
                            onEvent = onEvent
                        )

                        if (state.inDiceFriendIndex?.contains(index) == true) {
                            InviteFriendsItem("Invite your friend")
                        }

                    }

                }



            }

//            SideEffect {
//                onScrollToEnd()
//            }


        }

    }
}

@Composable
fun TopRankTitle() {
    val textString = buildAnnotatedString {
        append("Top ")
        withStyle(style = SpanStyle(color = Color(0xFFFF0000), fontWeight = FontWeight.Medium)) {
            append(" 3  ")
        }
    }

    Row(
        modifier = Modifier.padding(start = 8.dp)
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
            color = Color.Black
        )

        Text(
            text = "rank crypto",
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.CenterVertically),
            maxLines = 2,
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            ),
            color = Color.Black
        )
    }
}

@Composable
fun SearchText(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusLost: (String) -> Unit,
) {
    var text by remember { mutableStateOf(value) }
    var focusedState by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.isFocused) {
                    keyboardController?.show()
                    focusedState = true
                } else {
                    focusedState = false
                    onFocusLost(text)
                }
            }
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                onFocusLost(text)
            }
        ),
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

@Composable
fun BottomSheetDetail(
    coin: CoinDetailViewState,
    modifier: Modifier = Modifier
) {
    Column {
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
                        color = Color.Magenta//Color(0xFF ${ coin.color })//Color.Black
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
                        color = Color.Black//Color(0xFF ${ coin.color })//Color.Black
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text(
                        text = "PRICE",//coin.name?:"",
                        modifier = modifier
                            .padding(top = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black//Color(0xFF ${ coin.color })//Color.Black
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
                        color = Color.Black//Color(0xFF ${ coin.color })//Color.Black
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text(
                        text = "MARKET CAP",//coin.name?:"",
                        modifier = modifier
                            .padding(top = 8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black//Color(0xFF ${ coin.color })//Color.Black
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
                        color = Color.Black//Color(0xFF ${ coin.color })//Color.Black
                    )
                }
            }
        }

        if (coin.description.isNullOrBlank() || coin.description.isEmpty()) {
            Text(
                text = "No description",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Color(0xFFACACAC)
            )
        } else {
            Text(
                text = coin.description ?: "",
                modifier = modifier
                    .padding(16.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Color(0xFFACACAC)
            )
        }

//        val sections = listOf("A", "B", "C", "D", "E", "F", "G")
//
//        LazyColumn(reverseLayout = true, contentPadding = PaddingValues(6.dp)) {
//            sections.forEach { section ->
//                stickyHeader {
//                    Text(
//                        "Section $section",
//                        Modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp)
//                    )
//                }
//                items(10) {
//                    Text("Item $it from the section $section")
//                }
//            }
//        }



//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//                .background(Color.Gray)
//                .align(Alignment.CenterHorizontally)
//        ) {
//            Canvas(modifier = modifier.fillMaxWidth()) {
//                drawLine(
//                    color = Color.Black, // Change color as needed
//                    start = Offset(0f, 0f),
//                    end = Offset(300f, 300f),
//                    strokeWidth = 4f, // Adjust stroke width as needed
//                )
//            }
//
//        }

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
                    text = "GO TO WEBSITE",
                    modifier = modifier
                        .padding(16.dp)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListItem(
    coin: CoinState,
    modifier: Modifier = Modifier,
    onEvent: (CoinEvent) -> Unit,
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F3F3),
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
                    color = Color.Black
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
                    color = Color.Black
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
            CoilImage(
                imageUrl = coin.iconUrl ?: "",
                sizeImage = 70
            )
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
                .padding(8.dp),
            textAlign = TextAlign.Center,
            style = TextStyle.Default,
            fontSize = 18.sp,
            color = Color(0xFFACACAC)
        )
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAppTheme {
        CoilImage(imageUrl = "", sizeImage = 100)
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
        CoinListItem(CoinState(), onEvent = {})
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
        TopLankListItem(CoinState(), onEvent = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextPreview() {
    JetpackComposeAppTheme {
        SearchText(
            value = "",
            onValueChange = {},
            onFocusLost = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopRankTitlePreview() {
    JetpackComposeAppTheme {
        TopRankTitle()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetDetailPreview() {
    JetpackComposeAppTheme {
        BottomSheetDetail(CoinDetailViewState())
    }
}

