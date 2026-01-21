package com.example.newsappkmp.presentation.news_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsappkmp.domain.model.NewsItem
import com.example.newsappkmp.presentation.news_list.action.NewsListAction
import com.example.newsappkmp.presentation.news_list.action.TabIndexAction
import com.example.newsappkmp.presentation.news_list.screen.components.NewsListView
import com.example.newsappkmp.presentation.news_list.viewModel.NewsListViewModel
import com.example.newsappkmp.presentation.util.DarkBlue
import com.example.newsappkmp.presentation.util.DesertWhite
import com.example.newsappkmp.presentation.util.SandYellow
import neswsappkmp.composeapp.generated.resources.Res
import neswsappkmp.composeapp.generated.resources.favorite_news
import neswsappkmp.composeapp.generated.resources.load_news
import neswsappkmp.composeapp.generated.resources.load_news_is_empty
import neswsappkmp.composeapp.generated.resources.news_title
import neswsappkmp.composeapp.generated.resources.no_favorite_news
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsListScreen(
    newsListViewModel: NewsListViewModel,
    onNewsClick: (NewsItem) -> Unit,
) {
    val newsListState by newsListViewModel.newsListState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState { 2 }
    val loadResultsListScrollState = rememberLazyListState()
    val favoriteNewsListScrollState = rememberLazyListState()

    LaunchedEffect(newsListState.selectedTabIndex) {
        pagerState.animateScrollToPage(newsListState.selectedTabIndex.ordinal)
    }

    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> {
                newsListViewModel.onAction(
                    action = NewsListAction.OnSelectTabIndex(
                        tabIndex = TabIndexAction.LOAD_NEWS
                    )
                )
            }

            1 -> {
                newsListViewModel.onAction(
                    action = NewsListAction.OnSelectTabIndex(
                        tabIndex = TabIndexAction.FAVORITE_NEWS
                    )
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(Res.string.news_title),
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SecondaryTabRow(
                    selectedTabIndex = newsListState.selectedTabIndex.ordinal,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    contentColor = DesertWhite
                ) {
                    Tab(
                        selected = newsListState.selectedTabIndex == TabIndexAction.LOAD_NEWS,
                        onClick = {
                            newsListViewModel.onAction(
                                action = NewsListAction.OnSelectTabIndex(
                                    tabIndex = TabIndexAction.LOAD_NEWS
                                )
                            )
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.load_news),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }

                    Tab(
                        selected = newsListState.selectedTabIndex == TabIndexAction.FAVORITE_NEWS,
                        onClick = {
                            newsListViewModel.onAction(
                                action = NewsListAction.OnSelectTabIndex(
                                    tabIndex = TabIndexAction.FAVORITE_NEWS
                                )
                            )
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorite_news),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if (newsListState.isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    when {
                                        newsListState.listNews.articles.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.load_news_is_empty),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            NewsListView(
                                                newsItems = newsListState.listNews.articles,
                                                onNewsClick = onNewsClick,
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = loadResultsListScrollState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (newsListState.favoriteListNews.articles.isEmpty()) {
                                    Text(
                                        text = stringResource(Res.string.no_favorite_news),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                } else {
                                    NewsListView(
                                        newsItems = newsListState.favoriteListNews.articles,
                                        onNewsClick = onNewsClick,
                                        modifier = Modifier.fillMaxSize(),
                                        scrollState = favoriteNewsListScrollState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
