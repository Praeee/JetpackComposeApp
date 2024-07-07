package com.praeee.jetpackcomposeapp.ui.feature_news_detail_page

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
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import com.praeee.jetpackcomposeapp.ui.feature_news_page.NewsEvent
import com.praeee.jetpackcomposeapp.ui.repository.NewsRepository
import com.praeee.jetpackcomposeapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NewsDetailViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(NewsDetailUiState())
        private set

    init {
        Log.d(TAG, "init block of NewsDetailViewModel")
        uiState = uiState.copy(
            isLoading = true,
        )
    }

    fun onEvent(event: NewsDetailEvent) {
        when (event) {
            NewsDetailEvent.PullToRefresh -> TODO()
            is NewsDetailEvent.SetArticleDetail -> {
                setArticleDetail(event.article)
            }
        }
    }

    private fun setArticleDetail(articleUiState: ArticleUiState) {
        uiState = uiState.copy(
            article = articleUiState
        )
    }


    companion object {
        const val TAG = "NewsDetailViewModel"
    }

}