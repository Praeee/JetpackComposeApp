package com.praeee.jetpackcomposeapp.ui.feature_home_page.screens.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun SearchText(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusLost: (String) -> Unit,
) {
    var text by remember { mutableStateOf(value) }
    var focusedState by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val color = MaterialTheme.colorScheme


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
        colors = TextFieldDefaults.colors(color.primary),
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
                text = stringResource(id = R.string.search_title),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = color.secondary
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "icon_expend",
                modifier = Modifier
                    .height(14.dp),
                tint = color.primaryContainer
            )
        },
        singleLine = false
    )
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
fun SearchTextPreview() {
    JetpackComposeAppTheme {
        SearchText(
            value = "",
            onValueChange = {},
            onFocusLost = {}
        )
    }
}
