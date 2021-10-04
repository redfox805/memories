package com.zaydhisyam.memories

import com.zaydhisyam.memories.data.source.remote.RemoteDataSource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MyRemoteDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private val apiService = MyFakeApiService()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource(
            apiService = apiService,
            myDispatchers = coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `getCustomPostList should success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val expected = DataSourceFakeResponse.getCustomPostList(apiService = apiService)

        val result = remoteDataSource.getCustomPostList().single()

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun `getCommentListPerPost should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val expected = DataSourceFakeResponse.getCommentListPerPost(
                apiService = apiService,
                postId = 0
            )

            val result = remoteDataSource.getCommentListPerPost(postId = 0).single()

            assertNotNull(result)
            assertEquals(expected, result)
        }

    @Test
    fun `getCustomAlbumListPerUser should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val expected = DataSourceFakeResponse.getCustomAlbumListPerUser(
                apiService = apiService,
                userId = 0
            )

            val result = remoteDataSource.getCustomAlbumListPerUser(userId = 0).single()

            assertNotNull(result)
            assertEquals(expected, result)
        }

    @Test
    fun `getPhotoListPerAlbum should success`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val expected = DataSourceFakeResponse.getPhotoListPerAlbum(
                apiService = apiService,
                albumId = 0
            )

            val result = remoteDataSource.getPhotoListPerAlbum(albumId = 0).single()

            assertNotNull(result)
            assertEquals(expected, result)
        }
}