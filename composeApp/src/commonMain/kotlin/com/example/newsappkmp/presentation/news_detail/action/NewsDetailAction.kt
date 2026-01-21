package com.example.newsappkmp.presentation.news_detail.action

sealed interface NewsDetailAction {
    data object OnFavoriteClick : NewsDetailAction
    data class OnLoadNewsByUrl(val urlNews: String) : NewsDetailAction
}