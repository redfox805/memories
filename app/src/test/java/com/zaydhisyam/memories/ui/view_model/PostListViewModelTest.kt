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
import com.zaydhisyam.memories.domain.model.Post
import com.zaydhisyam.memories.domain.usecase.MyInteractor
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostListViewModelTest {

    private lateinit var postListViewModel: PostListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var postResponseListObserver: Observer<Resource<List<Post>>>

    private val apiService = MyFakeApiService()

    @Before
    fun setUp() {
        val remoteDataSource = RemoteDataSource(
            apiService,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )

        val myRepository = MyRepository(remoteDataSource)

        val myUsecase = MyInteractor(myRepository)

        postListViewModel = PostListViewModel(
            application,
            myUsecase
        )
    }

    @Test
    fun `setCommentListPerPostLiveData should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            postListViewModel.postList.observeForever(
                postResponseListObserver
            )

            val expected = RepositoryFakeResponse.getPostList(apiService)

            verify(postResponseListObserver).onChanged(expected)
            assertEquals(expected, postListViewModel.postList.value)
        }
}