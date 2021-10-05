package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zaydhisyam.memories.CoroutineTestRule
import com.zaydhisyam.memories.data.MyRepository
import com.zaydhisyam.memories.data.source.RepositoryFakeResponse
import com.zaydhisyam.memories.data.source.remote.RemoteDataSource
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.data.source.remote.api.MyFakeApiService
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.usecase.MyInteractor
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailUserViewModelTest {

    private lateinit var detailUserViewModel: DetailUserViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var albumResponseListPerUserObserver: Observer<Resource<List<Album>>>

    private val apiService = MyFakeApiService()

    @Before
    fun setUp() {
        val remoteDataSource = RemoteDataSource(
            apiService,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )

        val myRepository = MyRepository(remoteDataSource)

        val myUsecase = MyInteractor(myRepository)

        detailUserViewModel = DetailUserViewModel(
            context = application,
            myUsecase,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `setCommentListPerPostLiveData should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            detailUserViewModel.albumListPerUser.observeForever(
                albumResponseListPerUserObserver
            )

            val expected1 = Resource.Loading
            val expected2 = RepositoryFakeResponse.getAlbumListPerUser(
                apiService,
                userId = 0
            )

            detailUserViewModel.setAlbumListPerUserLiveData(userId = 0)

            verify(albumResponseListPerUserObserver).onChanged(expected1)
            assertEquals(expected1, detailUserViewModel.albumListPerUser.value)

            delay(5000L) //<-- if test not succeeded, try to increase this delay value

            verify(albumResponseListPerUserObserver).onChanged(expected2)
            assertEquals(expected2, detailUserViewModel.albumListPerUser.value)
        }
}