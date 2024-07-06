package com.praeee.jetpackcomposeapp.ui.feature_home_page.widgets


//@Composable
//fun BottomSheetDetail(
//    coin: CoinDetailViewState,
//    modifier: Modifier = Modifier
//) {
//    val color = MaterialTheme.colorScheme
//
//    Column( modifier = modifier
//        .background(color.background)
//    ) {
//        Row(
//            modifier = modifier
//                .height(100.dp)
//                .fillMaxWidth()
//        ) {
//            CoilImage(
//                imageUrl = coin.iconUrl ?: "",
//                sizeImage = 100
//            )
//            Column(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Row {
//                    Text(
//                        text = coin.name ?: "",
//                        modifier = modifier
//                            .padding(top = 8.dp),
//                        maxLines = 1,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        color = color.primary
//                    )
//                    Text(
//                        text = coin.symbol ?: "-",
//                        modifier = modifier
//                            .padding(top = 8.dp, start = 8.dp),
//                        maxLines = 1,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        color = color.primary
//                    )
//                }
//                Row(
//                    modifier = Modifier
//                        .wrapContentWidth()
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.price_title),
//                        modifier = modifier
//                            .padding(top = 8.dp),
//                        maxLines = 1,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        color = color.primary
//                    )
//                    Text(
//                        text = coin.price ?: "",
//                        modifier = modifier
//                            .padding(top = 8.dp, start = 8.dp),
//                        maxLines = 1,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Normal
//                        ),
//                        color = color.primary
//                    )
//                }
//                Row(
//                    modifier = Modifier
//                        .wrapContentWidth()
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.market_cap_title),
//                        modifier = modifier
//                            .padding(top = 8.dp),
//                        maxLines = 1,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        ),
//                        color = color.primary
//                    )
//                    Text(
//                        text = coin.marketCap ?: "",
//                        modifier = modifier
//                            .padding(top = 8.dp, start = 8.dp),
//                        maxLines = 1,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Normal
//                        ),
//                        color = color.primary
//                    )
//                }
//            }
//        }
//
//        if (coin.description.isNullOrBlank() || coin.description.isEmpty()) {
//            Text(
//                text = stringResource(id = R.string.no_description_title),
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                textAlign = TextAlign.Center,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium
//                ),
//                color = color.onPrimary
//            )
//        } else {
//            Text(
//                text = coin.description,
//                modifier = modifier
//                    .padding(16.dp),
//                textAlign = TextAlign.Start,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium
//                ),
//                color = color.onPrimary
//            )
//        }
//
//
//        val localContext = LocalContext.current
//
//        if (coin.websiteUrl != null) {
//            Row(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//                    .clickable {
//                        val urlIntent = Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse(coin.websiteUrl)
//                        )
//                        localContext.startActivity(urlIntent)
//                    }
//                    .align(Alignment.CenterHorizontally),
//            ) {
//                Text(
//                    text = stringResource(id = R.string.go_to_website_title),
//                    modifier = modifier
//                        .padding(vertical = 24.dp)
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    style = TextStyle(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold
//                    ),
//                    color = Color(0xFF1D8EF6)
//                )
//            }
//        }
//
//
//    }
//
//}

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
//fun BottomSheetDetailPreview() {
//    JetpackComposeAppTheme {
//        BottomSheetDetail(CoinDetailViewState())
//    }
//}