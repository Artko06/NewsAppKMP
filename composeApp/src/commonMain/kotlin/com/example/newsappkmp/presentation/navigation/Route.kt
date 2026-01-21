package com.example.newsappkmp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object NewsGraph : Route

    @Serializable
    data object NewsList : Route

    @Serializable
    data class NewsDetail(val urlNews: String) : Route
}