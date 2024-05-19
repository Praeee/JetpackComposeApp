package com.praeee.jetpackcomposeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(
        modifier = modifier
            .fillMaxWidth()
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
            color = Color.Black
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

@Preview(showBackground = true)
@Composable
fun LoaderPreview() {
    JetpackComposeAppTheme {
        Loader()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorUiStatePreview() {
    JetpackComposeAppTheme {
        ErrorUiState(onClick = {})
    }
}

