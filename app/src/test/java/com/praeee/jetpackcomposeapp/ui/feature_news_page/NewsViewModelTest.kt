package com.praeee.jetpackcomposeapp.ui.feature_news_page

import com.praeee.jetpackcomposeapp.data.AppConstants.COUNTRY
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.Article
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.NewsListResponse
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.Source
import com.praeee.jetpackcomposeapp.ui.repository.NewsRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState.Error
import com.praeee.jetpackcomposeapp.utilities.ResourceState.Loading
import com.praeee.jetpackcomposeapp.utilities.ResourceState.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    private lateinit var newsRepository: NewsRepository
    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun setUp() {
        newsRepository = mock(NewsRepository::class.java)
        newsViewModel = NewsViewModel(newsRepository)

        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNews should update uiState with success data`() = runTest {
        // Given
        val articles = listOf(
            Article(
                author = "author",
                title = "title",
                description = "description",
                url = "url",
                urlToImage = "urlToImage",
                publishedAt = "2024-07-05T14:35:54Z",
                content = "content",
                source = Source(
                    id = "id",
                    name = "name"
                )
            )
        )
        val response = NewsListResponse(
            articles = articles,
            status = "ok",
            totalResults = 100
        )
        val resourceState = Success(response)
        `when`(newsRepository.getNewsHeadline(COUNTRY)).thenReturn(flowOf(resourceState))

        // When
        newsViewModel.getNews()

        // Then
        val expect = NewsUiState(
            isError = false,
            isLoading = false,
            articleList =
            ArticleListUiState(
                articleList = listOf(
                    ArticleUiState(
                        author = "author",
                        title = "title",
                        description = "description",
                        url = "url",
                        urlToImage = "urlToImage",
                        publishedAt = "Updated : 05 Jul 2024, 21:35",
                        content = "content",
                        source = SourceUiState(
                            id = "id",
                            name = "name"
                        )
                    )
                ),

            )
        )
        assertEquals(
            expect,
            newsViewModel.uiState
        )
    }

    @Test
    fun `getNews should update uiState with error`() = runTest {
        // Given
        val errorText = "Error fetching news data"
        `when`(newsRepository.getNewsHeadline(COUNTRY)).thenReturn(flowOf(Error(errorText)))

        // When
        newsViewModel.getNews()

        // Then
        val expect = NewsUiState(
            isError = true,
            isLoading = false,
            articleList = null
        )
        assertEquals(
            expect,
            newsViewModel.uiState
        )
    }

    @Test
    fun `getNews should update uiState with loading`() = runTest {
        // Given
        `when`(newsRepository.getNewsHeadline(COUNTRY)).thenReturn(flowOf(Loading()))

        // When
        newsViewModel.getNews()

        // Then
        val expect = NewsUiState(
            isError = false,
            isLoading = true,
            articleList = null
        )

        assertEquals(
            expect,
            newsViewModel.uiState
        )
    }
}
