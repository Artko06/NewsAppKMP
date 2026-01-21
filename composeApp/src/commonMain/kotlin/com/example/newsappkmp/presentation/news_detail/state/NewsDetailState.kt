package com.example.newsappkmp.presentation.news_detail.state

import com.example.newsappkmp.domain.model.NewsItem

data class NewsDetailState(
    val news: NewsItem? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = true
)
