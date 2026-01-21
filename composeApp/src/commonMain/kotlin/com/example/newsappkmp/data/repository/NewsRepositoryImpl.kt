package com.example.newsappkmp.data.repository

import com.example.newsappkmp.data.localDb.dao.NewsFavoriteDao
import com.example.newsappkmp.data.mappers.toData
import com.example.newsappkmp.data.mappers.toDomain
import com.example.newsappkmp.data.network.NetworkClient
import com.example.newsappkmp.data.network.NetworkConfiguration
import com.example.newsappkmp.domain.model.NewsItem
import com.example.newsappkmp.domain.model.NewsItemList
import com.example.newsappkmp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val networkClientImpl: NetworkClient,
    private val newsFavoriteDao: NewsFavoriteDao
) : NewsRepository {
    override suspend fun loadNews(): Result<NewsItemList> {
        return networkClientImpl.loadData(
            path = NetworkConfiguration.BASE_URL +
                    "everything?q=science&sortBy=publishedAt&apiKey=${NetworkConfiguration.API_KEY}"
        ).map { dto -> dto.toDomain() }
    }

    override fun getFavoriteNews(): Flow<List<NewsItem>> {
        return newsFavoriteDao.getAllFavoriteNews().map { newsItemEntities ->
            newsItemEntities.map { it.toDomain() }
        }
    }

    override fun isFavoriteNews(url: String): Flow<Boolean> {
        return newsFavoriteDao.getAllFavoriteNews().map { newsItemEntities ->
            newsItemEntities.any { it.url == url }
        }
    }

    override suspend fun markAsFavorite(newsItem: NewsItem) {
        newsFavoriteDao.upsertNews(news = newsItem.toData())
    }

    override suspend fun deleteFromFavorite(url: String) {
        newsFavoriteDao.deleteNews(url = url)
    }
}