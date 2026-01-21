package com.example.newsappkmp.domain.useCase

import com.example.newsappkmp.domain.model.NewsItemList
import com.example.newsappkmp.domain.repository.NewsRepository

class LoadNewsUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): NewsItemList? {
        val res = newsRepository.loadNews()

        print(res.isFailure)

        return res.getOrNull()
    }
}