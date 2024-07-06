package com.praeee.jetpackcomposeapp.ui.feature_news_page.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.praeee.jetpackcomposeapp.data.AppConstants
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.toArticleListUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleListUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsUiState
import com.praeee.jetpackcomposeapp.ui.repository.NewsRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import kotlinx.coroutines.flow.firstOrNull

@Suppress("kotlin:S6517")
interface MapNewsListResponseToUiStateUseCase {
    suspend fun execute() : NewsUiState?
}

class MapNewsListResponseToUiStateUseCaseImpl(
    private val newsRepository: NewsRepository,
) : MapNewsListResponseToUiStateUseCase {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun execute() : NewsUiState {
        return when (val newsList = newsRepository.getNewsHeadline(AppConstants.COUNTRY).firstOrNull()) {
            is ResourceState.Success -> {
                NewsUiState(
                    isError = false,
                    isLoading = false,
                    articleList = newsList.data.toArticleListUiState()
                )
            }

            is ResourceState.Error -> {
                NewsUiState(
                    isError = true,
                    isLoading = false,
                )
            }

            is ResourceState.Loading -> {
                NewsUiState(
                    isLoading = true,
                    isError = false,
                )
            }

            else -> {
                NewsUiState(
                    isError = true,
                )
            }
        }

    }
}