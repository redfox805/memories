package com.zaydhisyam.memories.data.source

import com.zaydhisyam.memories.CoroutineTestRule
import com.zaydhisyam.memories.data.MyRepository
import com.zaydhisyam.memories.data.source.remote.RemoteDataSource
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.data.source.remote.api.MyFakeApiService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MyRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private val apiService = MyFakeApiService()

    private lateinit var myRepository: MyRepository

    @Before
    fun setUp() {
        val remoteDataSource = RemoteDataSource(
            apiService = apiService,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )

        myRepository = MyRepository(remoteDataSource)
    }

    @Test
    fun `getPostList should success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        var count = 0
        myRepository.getPostList().collect { result ->
            if (count == 0) {
                val expected = Resource.Loading

                assertNotNull(result)
                assertEquals(expected, result)
                count++
            } else {
                val expected = RepositoryFakeResponse.getPostList(apiService)

                assertNotNull(result)
                assertEquals(expected, result)
            }
        }
    }

    @Test
    fun `getCommentListPerPost should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            var count = 0
            myRepository.getCommentListPerPost(0).collect { result ->
                if (count == 0) {
                    val expected = Resource.Loading

                    assertNotNull(result)
                    assertEquals(expected, result)
                    count++
                } else {
                    val expected = RepositoryFakeResponse.getCommentListPerPost(
                        apiService,
                        postId = 0
                    )

                    assertNotNull(result)
                    assertEquals(expected, result)
                }
            }
        }

    @Test
    fun `getAlbumListPerUser should success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        var count = 0
        myRepository.getAlbumListPerUser(0).collect { result ->
            if (count == 0) {
                val expected = Resource.Loading

                assertNotNull(result)
                assertEquals(expected, result)
                count++
            } else {
                val expected = RepositoryFakeResponse.getAlbumListPerUser(
                    apiService,
                    userId = 0
                )

                assertNotNull(result)
                assertEquals(expected, result)
            }
        }
    }

    @Test
    fun `getPhotoListPerAlbum should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            var count = 0
            myRepository.getPhotoListPerAlbum(0).collect { result ->
                if (count == 0) {
                    val expected = Resource.Loading

                    assertNotNull(result)
                    assertEquals(expected, result)
                    count++
                } else {
                    val expected = RepositoryFakeResponse.getPhotoListPerAlbum(
                        apiService,
                        albumId = 0
                    )

                    assertNotNull(result)
                    assertEquals(expected, result)
                }
            }
        }
}