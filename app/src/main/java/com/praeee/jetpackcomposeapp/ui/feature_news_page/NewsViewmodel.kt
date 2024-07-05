package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praeee.jetpackcomposeapp.data.AppConstants
import com.praeee.jetpackcomposeapp.data.entity.news_response.toArticleListUiState
import com.praeee.jetpackcomposeapp.ui.repository.NewsRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
//    private val newsRepository: NewsRepository
) : ViewModel() {

    var uiState by mutableStateOf(NewsUiState())
        private set

    init {
        Log.d(TAG,"init block of NewsViewModel")
        getNews(AppConstants.COUNTRY)
    }

    private fun getNews(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                newsRepository.getNewsHeadline(country).collectLatest { coinDetailResponse ->
//                    when (coinDetailResponse) {
//                        is ResourceState.Success -> {
//                            uiState = uiState.copy(
//                                isError = false,
//                                isLoading = false,
//                                articleList = coinDetailResponse.data.toArticleListUiState()
//                            )
//                        }
//                        is ResourceState.Error -> {
//                            uiState  = uiState.copy(
//                                isError = true,
//                                isLoading = false,
//                            )
//                        }
//                        is ResourceState.Loading -> {
//                            uiState  = uiState.copy(
//                                isLoading = true,
//                                isError = false,
//                            )
//                        }
//                    }
//                }
//            } catch (error: Exception) {
//                uiState  = uiState.copy(
//                    isError = true,
//                )
//            }
        }
    }

    fun onEvent(event: NewsEvent) {
        when (event) {

            is NewsEvent.OnErrorUi -> {
                getNews(country = AppConstants.COUNTRY)
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