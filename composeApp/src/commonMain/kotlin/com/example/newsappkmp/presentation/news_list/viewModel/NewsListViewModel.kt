package com.example.newsappkmp.presentation.news_list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappkmp.domain.model.NewsItemList
import com.example.newsappkmp.domain.useCase.LoadFavoriteNewsUseCase
import com.example.newsappkmp.domain.useCase.LoadNewsUseCase
import com.example.newsappkmp.presentation.news_list.action.NewsListAction
import com.example.newsappkmp.presentation.news_list.state.NewsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val loadNewsUseCase: LoadNewsUseCase,
    private val loadFavoriteNewsUseCase: LoadFavoriteNewsUseCase,
) : ViewModel(
) {
    private val _newsListState = MutableStateFlow(NewsListState())

    private val _observeFavoriteNews = loadFavoriteNewsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = NewsItemList(
                totalResults = 0,
                articles = emptyList()
            )
        )

    val newsListState = combine(
        _newsListState,
        _observeFavoriteNews
    ) { newsListState, observeFavoriteNews ->
        _newsListState.value.copy(
            favoriteListNews = observeFavoriteNews
        )
    }
        .onStart {
            onAction(action = NewsListAction.OnNewsLoad)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NewsListState()
        )

    fun onAction(action: NewsListAction) {
        when (action) {
            is NewsListAction.OnNewsClick -> {

            }

            NewsListAction.OnNewsLoad -> {
                viewModelScope.launch {
                    val loadNews = loadNewsUseCase()

                    _newsListState.update {
                        it.copy(
                            listNews = loadNews ?: NewsItemList(
                                totalResults = 0,
                                articles = emptyList()
                            ),
                            isLoading = false
                        )
                    }
                }
            }

            is NewsListAction.OnSelectTabIndex -> {
                _newsListState.update {
                    it.copy(
                        selectedTabIndex = action.tabIndex
                    )
                }
            }
        }
    }
}