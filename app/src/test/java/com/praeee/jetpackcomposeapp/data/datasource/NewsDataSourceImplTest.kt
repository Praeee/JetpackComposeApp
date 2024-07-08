package com.praeee.jetpackcomposeapp.data.datasource

import com.praeee.jetpackcomposeapp.data.api.NewsApiService
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.Article
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.NewsListResponse
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewsDataSourceImplTest {

    private lateinit var apiService: NewsApiService
    private lateinit var newsDataSource: NewsDataSourceImpl

    @Before
    fun setUp() {
        apiService = mock(NewsApiService::class.java)
        newsDataSource = NewsDataSourceImpl(apiService)
    }

    @Test
    fun `getNewsHeadline should return data empty from apiService`() = runBlockingTest {
        // Given
        val country = "us"
        val expectedResponse = Response.success(NewsListResponse(listOf()))
        `when`(apiService.getNewsHeadline(country)).thenReturn(expectedResponse)

        // When
        val result = newsDataSource.getNewsHeadline(country)

        // Then
        assertTrue(result.isSuccessful)
        assertEquals(expectedResponse, result)
        verify(apiService).getNewsHeadline(country)
    }

    @Test
    fun `getNewsHeadline should return data from apiService`() = runBlockingTest {
        // Given
        val country = "us"
        val response = NewsListResponse(
            articles = listOf(
                Article(
                    author = "author",
                    title = "title",
                    description = "description",
                    url = "url",
                    urlToImage = "urlToImage",
                    publishedAt = "2024-07-05T11:10:00Z",
                    content = "content",
                    source = Source(
                        id = "id",
                        name = "name"
                    )
                )
            ),
            status = "ok",
            totalResults = 100
        )
        val expectedResponse = Response.success(response)
        `when`(apiService.getNewsHeadline(country)).thenReturn(expectedResponse)

        // When
        val result = newsDataSource.getNewsHeadline(country)

        // Then
        assertTrue(result.isSuccessful)
        assertEquals(expectedResponse, result)
        verify(apiService).getNewsHeadline(country)
    }

    @Test
    fun `getNewsHeadline should handle API errors`() = runBlockingTest {
        // Given
        val country = "us"
        val expectedResponse = Response.error<NewsListResponse>(404, mock(ResponseBody::class.java))
        `when`(apiService.getNewsHeadline(country)).thenReturn(expectedResponse)

        // When
        val result = newsDataSource.getNewsHeadline(country)

        // Then
        assertTrue(!result.isSuccessful)
        assertEquals(expectedResponse, result)
        verify(apiService).getNewsHeadline(country)
    }
}
