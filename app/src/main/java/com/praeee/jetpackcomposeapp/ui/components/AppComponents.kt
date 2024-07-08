package com.praeee.jetpackcomposeapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp),
            color = Color.Blue,
        )
    }

}

@Composable
fun ErrorUiState(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    val color = MaterialTheme.colorScheme

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Could not load data",
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            color = color.primary
        )
        Text(
            text = "Try again",
            modifier = modifier
                .clickable {
                    onClick.invoke()
                }
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

@Composable
fun NotFoundKeyword() {
    val color = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
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
            color = color.primary
        )
        Text(
            text = "No result match the keyword",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            style = TextStyle.Default,
            fontSize = 18.sp,
            color = color.secondary
        )
    }
}

@Composable
fun GoToTop(goToTop: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .padding(32.dp)
                .size(50.dp)
                .shadow(1.dp)
                .align(Alignment.BottomEnd),
            onClick = goToTop,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_top_arrow),
                contentDescription = "go to top"
            )
        }
    }
}

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
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
fun LoaderPreview() {
    JetpackComposeAppTheme {
        Loader()
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
fun ErrorUiStatePreview() {
    JetpackComposeAppTheme {
        ErrorUiState(onClick = {})
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
fun NotFoundKeywordPreview() {
    JetpackComposeAppTheme {
        NotFoundKeyword()
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
fun GoToTopPreview() {
    JetpackComposeAppTheme {
        GoToTop(
            goToTop = {

            }
        )
    }
}

