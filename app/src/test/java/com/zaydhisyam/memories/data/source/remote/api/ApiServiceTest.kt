package com.zaydhisyam.memories.data.source.remote.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zaydhisyam.memories.BuildConfig
import com.zaydhisyam.memories.Utils
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * have to use robolectric because mocking JSONObject is bothersome...
 */
@RunWith(RobolectricTestRunner::class)
class ApiServiceTest {

    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        val moshiConverter = MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )

        apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshiConverter)
            .build()
            .create(ApiService::class.java)

        stopKoin()
    }

    @Test
    fun `getUserList should success`() {
        val expected = Utils.getUserResponseListFromJsonFile()

        runBlocking {
            val actual = apiService.getUserList()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `getPostList should success`() {
        val expected = Utils.getPostResponseListFromJsonFile()

        runBlocking {
            val actual = apiService.getPostList()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `getCommentListPerPost should success`() {
        val expected = Utils.getCommentResponsePerPostListFromJsonFile()

        runBlocking {
            val actual = apiService.getCommentListPerPost(postId = 1)

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `getAlbumListPerUser should success`() {
        val expected = Utils.getAlbumResponseListPerUserFromJsonFile()

        runBlocking {
            val actual = apiService.getAlbumListPerUser(userId = 1)

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `getPhotoListPerAlbum should success`() {
        val expected = Utils.getPhotoResponseListPerAlbumFromJsonFile()

        runBlocking {
            val actual = apiService.getPhotoListPerAlbum(albumId = 1)

            assertEquals(expected, actual)
        }
    }
}