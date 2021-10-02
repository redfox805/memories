package com.zaydhisyam.memories.data.source.remote.api

import com.zaydhisyam.memories.data.source.remote.response_classes.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUserList(): List<UserResponse>

    @GET("posts")
    suspend fun getPostList(): List<PostResponse>

    @GET("comments")
    suspend fun getCommentListPerPost(
        @Query("postId") postId: Int
    ): List<CommentResponse>

    @GET("albums")
    suspend fun getAlbumListPerUser(
        @Query("userId") userId: Int
    ): List<AlbumResponse>

    @GET("photos")
    suspend fun getPhotoListPerAlbum(
        @Query("albumId") albumId: Int
    ): List<PhotoResponse>
}