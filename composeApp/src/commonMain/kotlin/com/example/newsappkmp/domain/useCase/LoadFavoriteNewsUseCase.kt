package com.example.newsappkmp.domain.useCase

import com.example.newsappkmp.domain.model.NewsItemList
import com.example.newsappkmp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadFavoriteNewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<NewsItemList> {
        val res = newsRepository.getFavoriteNews()

        return res.map { items ->
            NewsItemList(
                totalResults = items.size,
                articles = items
            )
        }
    }
}