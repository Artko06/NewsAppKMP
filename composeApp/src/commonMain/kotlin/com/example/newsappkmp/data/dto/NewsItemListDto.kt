package com.example.newsappkmp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemListDto(
    val totalResults: Int,
    val articles: List<NewsItemDto>,
)
