package com.example.newsappkmp.presentation.news_list.state

import com.example.newsappkmp.domain.model.NewsItemList
import com.example.newsappkmp.presentation.news_list.action.TabIndexAction

data class NewsListState(
    val listNews: NewsItemList = NewsItemList(totalResults = 0, articles = emptyList()),
    val favoriteListNews: NewsItemList = NewsItemList(totalResults = 0, articles = emptyList()),
    val selectedTabIndex: TabIndexAction = TabIndexAction.LOAD_NEWS,
    val isLoading: Boolean = true,
)