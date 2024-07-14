package com.praeee.jetpackcomposeapp.ui.feature_home_page

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenKtTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenContent_navigateToNews_onClick() {
        // Given a mock HomeNavEvent
        val mockNavEvent : HomeNavEvent
        var navigatedToNews = false
        mockNavEvent = HomeNavEvent(
            onNavigateToNews = {
                navigatedToNews = true
            },
            onNavigateBack = {

            }
        )

        // Set the content
        composeTestRule.setContent {
            HomeScreen(
                navEvent = mockNavEvent,
            )
        }

        // Check if the text is displayed
        composeTestRule.onNodeWithText("News").assertIsDisplayed()

        // Perform a click on the card
        composeTestRule.onNodeWithText("News").performClick()

        // Assert that the navigation event is triggered
        assert(navigatedToNews)
    }

}