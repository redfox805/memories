package com.zaydhisyam.memories

import com.zaydhisyam.memories.data.source.remote.api.ApiService
import com.zaydhisyam.memories.data.source.remote.response_classes.*
import kotlinx.coroutines.delay

class MyFakeApiService : ApiService {

    override suspend fun getUserList(): List<UserResponse> {
        delay(1000L)

        val result = arrayListOf<UserResponse>()
        val user = ApiServiceFakeResponse.createUserResponse(0)
        result.add(user)

        return result
    }

    override suspend fun getPostList(): List<PostResponse> {
        delay(1000L)

        val result = arrayListOf<PostResponse>()
        val post = ApiServiceFakeResponse.createPostResponse(0)
        result.add(post)

        return result
    }

    override suspend fun getCommentListPerPost(postId: Int): List<CommentResponse> {
        delay(1000L)

        val result = arrayListOf<CommentResponse>()
        val post = ApiServiceFakeResponse.createCommentResponse(0)
        result.add(post)

        return result
    }

    override suspend fun getAlbumListPerUser(userId: Int): List<AlbumResponse> {
        delay(1000L)

        val result = arrayListOf<AlbumResponse>()
        val post = ApiServiceFakeResponse.createAlbumResponse(0)
        result.add(post)

        return result
    }

    override suspend fun getPhotoListPerAlbum(albumId: Int): List<PhotoResponse> {
        delay(1000L)

        val result = arrayListOf<PhotoResponse>()
        val post = ApiServiceFakeResponse.createPhotoResponse(0)
        result.add(post)

        return result
    }
}