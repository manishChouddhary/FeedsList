package com.synerzip.feeds

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.synerzip.feeds.comunication.DataRepository
import com.synerzip.feeds.database.FeedsDao
import com.synerzip.feeds.network.CommunicationService
import com.synerzip.feeds.ui.FeedsViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: FeedsViewModel

    lateinit var dataRepository: DataRepository

    @MockK
    lateinit var communicationService: CommunicationService

    @MockK
    lateinit var feedsDao: FeedsDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        RxUnitTestTool.asyncToSync()
        dataRepository = DataRepository(communicationService, feedsDao)
        viewModel = FeedsViewModel(dataRepository)
        setupMocks()
    }

    @Test
    fun test_for_getFeeds() {
        viewModel.getFeedsUpdate(true)
        assert(viewModel.feedEntityLiveData.value?.size == 1)
        assert(viewModel.feedEntityLiveData.value?.get(0) == MockDataProvider.getMockEntity())
    }

    private fun setupMocks() {
        every { communicationService.getFeeds() } returns Single.just(MockDataProvider.getMockFeedsData())
    }
}