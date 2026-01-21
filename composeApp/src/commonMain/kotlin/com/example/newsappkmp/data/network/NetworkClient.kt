package com.example.newsappkmp.data.network

import com.example.newsappkmp.data.dto.NewsItemListDto

interface NetworkClient {
    suspend fun loadData(path: String): Result<NewsItemListDto>
}