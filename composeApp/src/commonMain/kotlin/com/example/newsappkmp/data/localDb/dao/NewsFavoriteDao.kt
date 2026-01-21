package com.example.newsappkmp.data.localDb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsappkmp.data.entity.NewsItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsFavoriteDao {
    @Upsert
    suspend fun upsertNews(news: NewsItemEntity)

    @Query("SELECT * FROM news")
    fun getAllFavoriteNews(): Flow<List<NewsItemEntity>>

    @Query("SELECT * FROM news WHERE url = :url")
    suspend fun getFavoriteNewsByUrl(url: String): NewsItemEntity

    @Query("DELETE FROM news WHERE url = :url")
    suspend fun deleteNews(url: String)
}