package com.example.newsappkmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.newsappkmp.data.localDb.dao.NewsFavoriteDao
import com.example.newsappkmp.data.localDb.database.NewsDatabaseFactory
import com.example.newsappkmp.data.localDb.database.NewsRoomDatabase
import com.example.newsappkmp.data.network.HttpClientFactory
import com.example.newsappkmp.data.network.NetworkClient
import com.example.newsappkmp.data.network.NetworkClientImpl
import com.example.newsappkmp.data.repository.NewsRepositoryImpl
import com.example.newsappkmp.domain.repository.NewsRepository
import com.example.newsappkmp.domain.useCase.LoadFavoriteNewsUseCase
import com.example.newsappkmp.domain.useCase.LoadNewsUseCase
import com.example.newsappkmp.presentation.news_detail.viewModel.NewsDetailViewModel
import com.example.newsappkmp.presentation.news_list.viewModel.NewsListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule: Module = module {
    single<HttpClient> { HttpClientFactory.create(engine = get<HttpClientEngine>()) }
    single<NetworkClientImpl> { NetworkClientImpl(httpClient = get<HttpClient>()) }
        .bind<NetworkClient>()

    single<NewsRoomDatabase> {
        get<NewsDatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single<NewsFavoriteDao> { get<NewsRoomDatabase>().newsDao }

    single<NewsRepositoryImpl> {
        NewsRepositoryImpl(
            networkClientImpl = get<NetworkClientImpl>(),
            newsFavoriteDao = get<NewsFavoriteDao>()
        )
    }.bind<NewsRepository>()

    factory<LoadNewsUseCase> { LoadNewsUseCase(newsRepository = get<NewsRepository>()) }
    factory<LoadFavoriteNewsUseCase> { LoadFavoriteNewsUseCase(newsRepository = get<NewsRepository>()) }

    viewModel<NewsListViewModel> {
        NewsListViewModel(
            loadNewsUseCase = get<LoadNewsUseCase>(),
            loadFavoriteNewsUseCase = get<LoadFavoriteNewsUseCase>()
        )
    }
    viewModel<NewsDetailViewModel> { NewsDetailViewModel() }
}