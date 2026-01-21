package com.example.newsappkmp.presentation.news_list.action

import com.example.newsappkmp.domain.model.NewsItem

sealed interface NewsListAction {
    data class OnNewsClick(val news: NewsItem) : NewsListAction
    data object OnNewsLoad : NewsListAction
    data class OnSelectTabIndex(val tabIndex: TabIndexAction) : NewsListAction
}