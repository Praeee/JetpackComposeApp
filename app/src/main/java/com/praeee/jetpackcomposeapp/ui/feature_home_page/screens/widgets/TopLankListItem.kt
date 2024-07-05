package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

//import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.domain.model.CoinState
//import com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.event.CoinEvent

//@Composable
//fun TopLankListItem(
//    coin: CoinState,
//    modifier: Modifier = Modifier,
//    onEvent: (CoinEvent) -> Unit,
//) {
//    val color = MaterialTheme.colorScheme
//
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = color.onSecondaryContainer,
//        ),
//        modifier = modifier
//            .height(150.dp)
//            .width(100.dp)
//            .padding(4.dp)
//            .clickable {
//                onEvent(CoinEvent.OnClickItemCoin(coin))
//            }
//    ) {
//        Column(
//            modifier = modifier
//                .align(Alignment.CenterHorizontally)
//        ) {
//            Row(
//                modifier = modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                CoilImage(
//                    imageUrl = coin.iconUrl ?: "",
//                    sizeImage = 70
//                )
//            }
//
//            Text(
//                text = coin.symbol ?: "",
//                modifier = modifier
//                    .fillMaxWidth()
//                    .align(Alignment.CenterHorizontally),
//                maxLines = 1,
//                textAlign = TextAlign.Center,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                ),
//                color = Color.Black
//            )
//            Text(
//                text = coin.name ?: "",
//                modifier = modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(2.dp),
//                textAlign = TextAlign.Center,
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Normal
//                ),
//                color = Color(0xFFACACAC)
//            )
//            Row(
//                modifier = modifier
//                    .padding(2.dp)
//                    .align(Alignment.CenterHorizontally),
//            ) {
//                Image(
//                    modifier = modifier
//                        .height(14.dp),
//                    colorFilter = if (coin.lowVolume == true) ColorFilter.tint(Color(0xFF1ABD66)) else ColorFilter.tint(
//                        Color(0xFFFF0000)
//                    ),
//                    painter = if (coin.lowVolume == true) painterResource(id = (R.drawable.up_icon)) else painterResource(
//                        id = (R.drawable.down_icon)
//                    ),
//                    contentDescription = "icon"
//                )
//                Text(
//                    text = coin.change ?: "",
//                    modifier = modifier,
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Medium
//                    ),
//                    color = if (coin.lowVolume == true) Color(0xFF1ABD66) else Color(0xFFFF0000)
//                )
//            }
//        }
//    }
//
//}
//
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "DefaultPreviewDark"
//)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    name = "DefaultPreviewLight"
//)
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun TopLankListItemPreview() {
//    JetpackComposeAppTheme {
//        TopLankListItem(CoinState(), onEvent = {})
//    }
//}
