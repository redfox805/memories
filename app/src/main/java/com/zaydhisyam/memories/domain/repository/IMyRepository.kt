package com.zaydhisyam.memories.domain.repository

import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse
import com.zaydhisyam.memories.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IMyRepository {

    fun getPostList(): Flow<Resource<List<Post>>>

    fun getCommentListPerPost(postId: Int): Flow<Resource<List<Comment>>>

    fun getAlbumListPerUser(userId: Int): Flow<Resource<List<Album>>>

    fun getPhotoListPerAlbum(albumId: Int): Flow<Resource<List<Photo>>>
}