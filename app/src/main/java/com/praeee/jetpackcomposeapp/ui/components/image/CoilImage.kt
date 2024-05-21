package com.praeee.jetpackcomposeapp.ui.components.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun CoilImage(
    imageUrl: String,
    sizeImage: Int,
) {

    Box(
        modifier = Modifier
            .size(sizeImage.dp)
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "icon",
            modifier = Modifier
                .fillMaxWidth()
                .size(sizeImage.dp),
            placeholder = painterResource(id = (R.drawable.placeholder_image)),
            error = painterResource(id = (R.drawable.placeholder_image)),
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAppTheme {
        CoilImage(imageUrl = "", sizeImage = 100)
    }
}