package com.praeee.jetpackcomposeapp.ui.feature_news_page

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.praeee.jetpackcomposeapp.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsScreenKtTest {

    @get:Rule(order = 0)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loader_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            NewsScreenContent(
                state = NewsUiState(isLoading = true),
                onEvent = {},
                onClickItemArticle = {},
                onClickBack = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("Loader").assertIsDisplayed()
    }


    @Test
    fun errorUiState_whenScreenIsError_exists() {

        val errorUi by lazy {
            hasText(
                composeTestRule.activity.resources.getString(R.string.error_text),
            )
        }

        composeTestRule.setContent {
            NewsScreenContent(
                state = NewsUiState(isError = true),
                onEvent = {},
                onClickItemArticle = {},
                onClickBack = {}
            )

        }

        composeTestRule
            .onNode(errorUi)
            .assertExists()

    }

    @Test
    fun contentUiState_whenContentHaveData_exists() {

        val testData = articleTestData.map { it.copy() }

        val scrollableNode = composeTestRule
            .onAllNodes(hasScrollToNodeAction())
            .onFirst()

        composeTestRule.setContent {
            NewsScreenContent(
                state = NewsUiState(
                    articleList = ArticleListUiState(
                        articleList = testData
                    )
                ),
                onEvent = {},
                onClickItemArticle = {},
                onClickBack = {}
            )

        }

        testData.forEachIndexed { index, _ ->
            scrollableNode.performScrollToIndex(index)

            if (index > 0) {
                composeTestRule
                    .onNodeWithContentDescription("go to top")
                    .assertIsNotDisplayed()
                    .performClick()
            }

            composeTestRule
                .onNodeWithContentDescription("ArticleItem_$index")
                .assertExists()
                .assertHasClickAction()

        }
    }

    @Test
    fun articleUiState_navigateToNewsDetail_onClickIndex0() {

        val testData = articleTestData.map { it.copy() }

        var capturedEvent: NewsNavEvent? = null

        composeTestRule.setContent {
            NewsScreenContent(
                state = NewsUiState(
                    articleList = ArticleListUiState(
                        articleList = testData
                    )
                ),
                onEvent = {},
                onClickItemArticle = {
                    capturedEvent = NewsNavEvent.OnNavigateToArticle(it)
                },
                onClickBack = {}
            )

        }

        // Test click on specific items
        composeTestRule
            .onNodeWithContentDescription("ArticleItem_0")
            .assertExists()
            .assertHasClickAction()
            .performClick()


//        assertNotNull(capturedEvent)
//
//        if (capturedEvent is NewsNavEvent.OnNavigateToArticle) {
//            val clickedArticle = (capturedEvent as NewsNavEvent.OnNavigateToArticle).article
//            // Assert details of the clicked article (e.g., title)
//            assertEquals(articleTestData[0],clickedArticle)
//        }


    }
}



val articleTestData: List<ArticleUiState> = listOf(
    ArticleUiState(
        author = "AlJazeera",
        title = "At least 22 killed after school building collapses in Nigeria - Al Jazeera English",
        description = "A Plateau state police spokesman said 132 people were rescued and are being treated for injuries in various hospitals.",
        url = "https://www.aljazeera.com/news/2024/7/12/several-children-killed-after-school-collapse-in-nigeria",
        urlToImage = "https://www.aljazeera.com/wp-content/uploads/2024/07/AFP__20240712__363Z2BE__v1__HighRes__NigeriaAccidentSchool-1720853185.jpg?resize=1920%2C1440",
        publishedAt = "2024-07-13T06:56:15Z",
        content = "At least 22 people, including students, have been confirmed dead after a two-storey school building collapsed in central Nigeria, according to the authorities, sending rescuers on a frantic search fo… [+2761 chars]",
        source = SourceUiState(
            id = "al-jazeera-english",
            name = "Al Jazeera English"
        )
    ),
    ArticleUiState(
        author = "Axios",
        title = "More than 24M people watched Biden NATO presser - Axios",
        description = "",
        url = "https://www.axios.com/2024/07/12/biden-press-conference-nato-viewers-watched",
        urlToImage = "",
        publishedAt = "2024-07-13T06:35:50Z",
        content = "",
        source = SourceUiState(
            id = "axios",
            name = "Axios"
        )
    ),
    ArticleUiState(
        author = "POLITICO",
        title = "SpaceX rocket accident leaves company’s Starlink satellites in wrong orbit - POLITICO",
        description = "",
        url = "https://www.politico.com/news/2024/07/13/spacex-rocket-accident-leaves-companys-starlink-satellites-in-wrong-orbit-00167959",
        urlToImage = "",
        publishedAt = "2024-07-13T06:30:00Z",
        content = "",
        source = SourceUiState(
            id = "politico",
            name = "Politico",
        )
    )
)