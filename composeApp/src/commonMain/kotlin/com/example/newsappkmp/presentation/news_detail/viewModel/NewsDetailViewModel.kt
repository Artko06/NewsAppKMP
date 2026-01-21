package com.example.newsappkmp.presentation.news_detail.viewModel

import androidx.lifecycle.ViewModel
import com.example.newsappkmp.presentation.news_detail.action.NewsDetailAction
import com.example.newsappkmp.presentation.news_detail.state.NewsDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsDetailViewModel : ViewModel() {
    private val _newsDetailState = MutableStateFlow(NewsDetailState())
    val newsDetailState = _newsDetailState.asStateFlow()

    fun onAction(action: NewsDetailAction) {
        when (action) {
            NewsDetailAction.OnFavoriteClick -> {

            }

            is NewsDetailAction.OnLoadNewsByUrl -> {

            }
        }
    }
}