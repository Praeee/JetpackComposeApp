package com.praeee.jetpackcomposeapp.ui.feature_news_page

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praeee.jetpackcomposeapp.data.AppConstants.COUNTRY
import com.praeee.jetpackcomposeapp.data.entity.news_list_response.toArticleListUiState
import com.praeee.jetpackcomposeapp.ui.repository.NewsRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    var uiState by mutableStateOf(NewsUiState())
        private set

    init {
        Log.d(TAG, "init block of NewsViewModel")
        uiState = uiState.copy(
            isLoading = true,
        )
        getNews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsHeadline(COUNTRY)
                .collectLatest { newsResponse ->
                    when (newsResponse) {
                        is ResourceState.Success -> {
                            uiState = uiState.copy(
                                isError = false,
                                isLoading = false,
                                articleList = newsResponse.data.toArticleListUiState()
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

                    }


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