package com.example.newsappkmp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "news"
)
data class NewsItemEntity(
    val author: String?,
    val title: String?,
    val description: String?,
    @PrimaryKey(autoGenerate = false) val url: String,
    val publishedAt: String? = null,
    val content: String?,
    val urlToImage: String? = null,
)