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
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.model.Photo
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
class PhotoListViewModelTest {

    private lateinit var photoListViewModel: PhotoListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var photoResponseListObserver: Observer<Resource<List<Photo>>>

    private val apiService = MyFakeApiService()

    @Before
    fun setUp() {
        val remoteDataSource = RemoteDataSource(
            apiService,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )

        val myRepository = MyRepository(remoteDataSource)

        val myUsecase = MyInteractor(myRepository)

        photoListViewModel = PhotoListViewModel(
            context = application,
            myUsecase,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `setPhotoListPerAlbumLiveData should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            photoListViewModel.photoList.observeForever(
                photoResponseListObserver
            )

            val expected1 = Resource.Loading
            val expected2 = RepositoryFakeResponse.getPhotoListPerAlbum(
                apiService,
                albumId = 0
            )

            photoListViewModel.setPhotoListPerAlbumLiveData(albumId = 0)

            verify(photoResponseListObserver).onChanged(expected1)
            assertEquals(expected1, photoListViewModel.photoList.value)

            delay(1000L) //<-- if test not succeeded, try to increase this delay value

            verify(photoResponseListObserver).onChanged(expected2)
            assertEquals(expected2, photoListViewModel.photoList.value)
        }
}