package com.example.newsappkmp.domain.model

data class NewsItem(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val publishedAt: String? = null,
    val content: String?,
    val urlToImage: String? = null,
)
