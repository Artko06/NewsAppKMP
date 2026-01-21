package com.example.newsappkmp.util

import com.example.newsappkmp.data.dto.NewsItemDto
import com.example.newsappkmp.data.dto.NewsItemListDto

val testNewsJson = NewsItemListDto(
    totalResults = 1,
    articles = listOf(
        NewsItemDto(
            author = "las vegas review-journal",
            title = "TESLAS TORCHED IN VEGAS...",
            description = "Police are investigating after an individual is believed to have set several vehicles on fire at a Tesla service center in Las Vegas.",
            url = "https://www.reviewjournal.com/post/3322726",
            urlToImage = "https://www.reviewjournal.com/wp-content/uploads/2025/03/20271272_web1_Teslafire_c2420f.jpg",
            publishedAt = "2025-03-18T18:00:03Z",
            content = "A suspect police who said set multiple Teslas on fire, used Molotov cocktails and shot three rounds into the vehicles at a Tesla Collision Center early Tuesday morning is still on the loose, accordin… [+1891 chars]"
        )
    )
)