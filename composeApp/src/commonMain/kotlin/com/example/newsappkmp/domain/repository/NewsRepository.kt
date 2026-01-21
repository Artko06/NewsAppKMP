package com.example.newsappkmp.domain.repository

import com.example.newsappkmp.domain.model.NewsItem
import com.example.newsappkmp.domain.model.NewsItemList
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun loadNews(): Result<NewsItemList>
    fun getFavoriteNews(): Flow<List<NewsItem>>
    fun isFavoriteNews(url: String): Flow<Boolean>
    suspend fun markAsFavorite(newsItem: NewsItem)
    suspend fun deleteFromFavorite(url: String)
}