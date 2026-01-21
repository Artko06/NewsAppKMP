package com.example.newsappkmp.domain.model

data class NewsItemList(
    val totalResults: Int,
    val articles: List<NewsItem>,
)
