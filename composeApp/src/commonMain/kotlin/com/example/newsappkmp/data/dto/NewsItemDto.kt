package com.example.newsappkmp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemDto(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val publishedAt: String? = null,
    val content: String?,
    val urlToImage: String? = null,
)