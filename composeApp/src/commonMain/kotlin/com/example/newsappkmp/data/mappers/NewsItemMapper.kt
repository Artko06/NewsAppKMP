package com.example.newsappkmp.data.mappers

import com.example.newsappkmp.data.dto.NewsItemDto
import com.example.newsappkmp.data.dto.NewsItemListDto
import com.example.newsappkmp.data.entity.NewsItemEntity
import com.example.newsappkmp.domain.model.NewsItem
import com.example.newsappkmp.domain.model.NewsItemList

fun NewsItem.toData(): NewsItemEntity = NewsItemEntity(
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    publishedAt = this.publishedAt,
    content = this.content,
    urlToImage = this.urlToImage,
)

fun NewsItemEntity.toDomain(): NewsItem = NewsItem(
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    publishedAt = this.publishedAt,
    content = this.content,
    urlToImage = this.urlToImage,
)

fun NewsItemDto.toDomain(): NewsItem = NewsItem(
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    publishedAt = this.publishedAt,
    content = this.content,
    urlToImage = this.urlToImage,
)

fun NewsItemListDto.toDomain(): NewsItemList = NewsItemList(
    totalResults = this.totalResults,
    articles = this.articles.map { it.toDomain() }
)