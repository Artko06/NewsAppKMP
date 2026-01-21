package com.example.newsappkmp

import com.example.newsappkmp.data.mappers.toDomain
import com.example.newsappkmp.domain.repository.NewsRepository
import com.example.newsappkmp.domain.useCase.LoadNewsUseCase
import com.example.newsappkmp.presentation.news_list.action.NewsListAction
import com.example.newsappkmp.presentation.news_list.viewModel.NewsListViewModel
import com.example.newsappkmp.util.testNewsJson
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest : FunSpec({
    lateinit var viewModel: NewsListViewModel
    lateinit var newsRepository: NewsRepository

    beforeTest {
        newsRepository = mock {
            everySuspend { loadNews() }.returns(
                value = Result.success(testNewsJson.toDomain())
            )
        }

        val loadNewsUseCase = LoadNewsUseCase(newsRepository)
        viewModel = NewsListViewModel(loadNewsUseCase)
    }

    test("should load news when OnNewsLoad action is called") {
        viewModel.onAction(NewsListAction.OnNewsLoad)

        runTest {
            viewModel.newsListState.value.listNews.shouldBe(testNewsJson.toDomain())
        }
    }
})