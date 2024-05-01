package com.praeee.jetpackcomposeapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent () {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "hello home screen")

    }
}
@Preview
@Composable
fun HomeScreenContentPreview () {
    JetpackComposeAppTheme {
        HomeScreen()
    }
}