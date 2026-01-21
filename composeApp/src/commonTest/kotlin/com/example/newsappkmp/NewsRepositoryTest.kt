package com.example.newsappkmp

import com.example.newsappkmp.data.localDb.dao.NewsFavoriteDao
import com.example.newsappkmp.data.network.NetworkClient
import com.example.newsappkmp.data.repository.NewsRepositoryImpl
import com.example.newsappkmp.domain.repository.NewsRepository
import com.example.newsappkmp.util.testNewsJson
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var newsRepository: NewsRepository
    private lateinit var networkClient: NetworkClient
    private lateinit var newsFavoriteDao: NewsFavoriteDao

    @BeforeTest
    fun setup() {
        networkClient = mock()
        newsFavoriteDao = mock()

        newsRepository = NewsRepositoryImpl(
            networkClientImpl = networkClient,
            newsFavoriteDao = newsFavoriteDao
        )

        Dispatchers.setMain(dispatcher = testDispatcher)
    }

    @Test
    fun `fetch news successfully`() = runTest {
        val result = Result.success(
            value = testNewsJson
        )

        everySuspend { networkClient.loadData(path = any()) }.returns(
            value = result
        )

        verifySuspend {
            newsRepository.loadNews()
        }
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}