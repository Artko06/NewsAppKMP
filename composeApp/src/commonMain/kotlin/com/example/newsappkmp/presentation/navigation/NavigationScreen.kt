package com.example.newsappkmp.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.newsappkmp.presentation.news_detail.screen.NewsDetailScreen
import com.example.newsappkmp.presentation.news_detail.viewModel.NewsDetailViewModel
import com.example.newsappkmp.presentation.news_list.screen.NewsListScreen
import com.example.newsappkmp.presentation.news_list.viewModel.NewsListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.NewsGraph
    ) {
        navigation<Route.NewsGraph>(
            startDestination = Route.NewsList
        ) {
            composable<Route.NewsList> {
                NewsListScreen(
                    newsListViewModel = koinViewModel<NewsListViewModel>(),
                    onNewsClick = { newsItem ->
                        navController.navigate(
                            route = Route.NewsDetail(urlNews = newsItem.url)
                        )
                    },
                )
            }

            composable<Route.NewsDetail>(
                enterTransition = {
                    slideInHorizontally { initialOffset ->
                        initialOffset
                    }
                },
                exitTransition = {
                    slideOutHorizontally { initialOffset ->
                        initialOffset
                    }
                }
            ) { entry ->
                val args = entry.toRoute<Route.NewsDetail>()
                val newsDetailViewModel = koinViewModel<NewsDetailViewModel>()

                NewsDetailScreen(
                    newsDetailViewModel = newsDetailViewModel,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    urlNews = args.urlNews
                )
            }
        }
    }
}