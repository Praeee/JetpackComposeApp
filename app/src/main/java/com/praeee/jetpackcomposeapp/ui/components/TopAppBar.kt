package com.praeee.jetpackcomposeapp.ui.components

import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.widgets.ArticleItemCard
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    titleText: String,
    navigationIcon: Painter? = null,
    navigationIconContentDescription: String?= null,
    actionIcon: Painter? = null,
    actionIconContentDescription: String? = null,
    modifier: Modifier = Modifier,
    modifierIcon: Modifier = Modifier.size(32.dp),
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    textStyle: TextStyle = LocalTextStyle.current,
) {
    val color = MaterialTheme.colorScheme

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleText,
                modifier = modifier
                    .padding(top = 4.dp),
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                overflow = TextOverflow.Ellipsis,
                color = color.primary
            )
        },
        navigationIcon = {
            navigationIcon?.let {
                Icon(
                    painter = navigationIcon,
                    contentDescription = "icon",
                    modifier = modifier
                        .padding(start = 16.dp)
                        .clickable {
                            onNavigationClick.invoke()
                        }
                )
            }
        },
        colors = colors,
        modifier = modifier
            .testTag("TopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
fun TopAppBarPreview() {
    JetpackComposeAppTheme {
        TopAppBar(
            titleText = stringResource(id = R.string.news_detail_title),
            navigationIcon = painterResource(id = R.drawable.ic_back_arrow),
            onActionClick = {},
        )
    }
}