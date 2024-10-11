package com.praeee.jetpackcomposeapp.ui.feature_login_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.praeee.jetpackcomposeapp.R
import com.praeee.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

const val TAG = "LoginScreen"

@Composable
fun LoginScreen(

) {
    LoginScreenContent()
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,

) {
    val color = MaterialTheme.colorScheme
//    val oneTapSignInState = remember
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

    // Login With Google Button
        Column {

            Image(
                modifier = modifier
                    .padding(all = 32.dp)
                    .padding(top = 32.dp)
                    .wrapContentHeight(),
                painter = painterResource(
                    id = R.drawable.ic_logo_welcome,
                ),
                contentDescription = "welcome_logo"
            )


            Button(
                onClick = {
//                    startForResult.launch(googleSignInClient?.signInIntent)
                },
                modifier = modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "GoogleSingInButton"
                    }
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                )
            ) {
                Image(
                    modifier = modifier
                        .wrapContentHeight(),
                    painter = painterResource(id = R.drawable.ic_logo_google),
                    contentDescription = "google_sign_in_logo"
                )
                Text(
                    text = "Sign in with Google",
                    modifier = modifier.padding(horizontal = 16.dp),
                    color = Color.White
                )
            }

        }

    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    JetpackComposeAppTheme {
        LoginScreen(
//            navEvent = HomeNavEvent()
        )
    }
}
