package com.example.newsappkmp

import com.example.newsappkmp.data.mappers.toDomain
import com.example.newsappkmp.domain.repository.NewsRepository
import com.example.newsappkmp.domain.useCase.LoadNewsUseCase
import com.example.newsappkmp.util.testNewsJson
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LoadNewsUseCaseTest : FunSpec({
    val newsRepository = mock<NewsRepository> {
        everySuspend { loadNews() }.returns(
            value = Result.success(testNewsJson.toDomain())
        )
    }

    val loadNewsUseCase = LoadNewsUseCase(
        newsRepository = newsRepository
    )


    test("LoadNewsUseCase execute") {
        val result = loadNewsUseCase()
        result.shouldBe(expected = testNewsJson.toDomain())
    }
})