package com.example.newsappkmp.data.network

import com.example.newsappkmp.data.dto.NewsItemListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NetworkClientImpl(private val httpClient: HttpClient) : NetworkClient {
    override suspend fun loadData(path: String): Result<NewsItemListDto> = try {
        // GET - request
        val data = httpClient.get(urlString = path)
        val result = data.body<NewsItemListDto>()
        Result.success(value = result)
    } catch (exception: Exception) {
        Result.failure(exception = exception)
    }
}
