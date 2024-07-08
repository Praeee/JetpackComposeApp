package com.praeee.jetpackcomposeapp.ui.sharedviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.praeee.jetpackcomposeapp.ui.feature_news_page.ArticleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsSharedViewModel @Inject constructor() : ViewModel() {

    var article by mutableStateOf<ArticleUiState?>(null)
        private set

    fun setCurrentArticleModel(article: ArticleUiState? = null) {
        this.article = article
    }
}
