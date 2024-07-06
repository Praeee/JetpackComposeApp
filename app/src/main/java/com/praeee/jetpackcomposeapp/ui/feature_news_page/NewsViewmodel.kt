package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praeee.jetpackcomposeapp.data.AppConstants
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.toArticleListUiState
import com.praeee.jetpackcomposeapp.data.entity.news_response.toArticleListUiState
import com.praeee.jetpackcomposeapp.di.IoDispatcher
import com.praeee.jetpackcomposeapp.ui.repository.NewsRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    var uiState by mutableStateOf(NewsUiState())
        private set

    init {
        Log.d(TAG,"init block of NewsViewModel")
        getNews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                newsRepository.getNewsHeadline(AppConstants.COUNTRY)
                    .collectLatest { coinDetailResponse ->
                        when (coinDetailResponse) {
                            is ResourceState.Success -> {
                                uiState = uiState.copy(
                                    isError = false,
                                    isLoading = false,
                                    articleList = coinDetailResponse.data.toArticleListUiState()
                                )
                            }

                            is ResourceState.Error -> {
                                uiState = uiState.copy(
                                    isError = true,
                                    isLoading = false,
                                )
                            }

                            is ResourceState.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true,
                                    isError = false,
                                )
                            }

                            else -> {
                                uiState = uiState.copy(
                                    isError = true,
                                )
                            }
                        }
                    }
            } catch (error: Exception) {
                uiState = uiState.copy(
                    isError = true,
                )
            }


        }
    }

    fun onEvent(event: NewsEvent) {
        when (event) {

            is NewsEvent.OnErrorUi -> {
//                getNews()
            }

            is NewsEvent.PullToRefresh -> {

            }

            is NewsEvent.OnClickItemArticle -> {

            }

        }
    }

    companion object {
        const val TAG = "NewsViewModel"
    }

}