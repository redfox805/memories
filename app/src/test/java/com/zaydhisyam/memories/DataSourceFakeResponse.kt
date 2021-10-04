package com.zaydhisyam.memories

import com.zaydhisyam.memories.data.DataUtils
import com.zaydhisyam.memories.data.source.remote.api.ApiResponse
import com.zaydhisyam.memories.data.source.remote.api.ApiService
import com.zaydhisyam.memories.data.source.remote.response_classes.CommentResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.PhotoResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomAlbumResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse

object DataSourceFakeResponse {

    suspend fun getCustomPostList(apiService: ApiService)
            : ApiResponse<List<CustomPostResponse>> {
        val userList = apiService.getUserList()
        val postList = apiService.getPostList()
        val customPostList = DataUtils.createCustomPostList(userList, postList)

        return ApiResponse.Success(customPostList)
    }

    suspend fun getCommentListPerPost(
        apiService: ApiService,
        postId: Int
    ): ApiResponse<List<CommentResponse>> {
        val commentList = apiService.getCommentListPerPost(postId)

        return ApiResponse.Success(commentList)
    }

    suspend fun getCustomAlbumListPerUser(
        apiService: ApiService,
        userId: Int
    ): ApiResponse<List<CustomAlbumResponse>> {
        val albumList = apiService.getAlbumListPerUser(userId)
        val customAlbumList = arrayListOf<CustomAlbumResponse>()

        for (album in albumList) {
            val firstPhotoInAlbum = apiService.getPhotoListPerAlbum(album.id)[0]
            val customAlbum = CustomAlbumResponse(
                userId = album.userId,
                id = album.id,
                title = album.title,
                thumbnailUrl = firstPhotoInAlbum.thumbnailUrl
            )
            customAlbumList.add(customAlbum)
        }

        return ApiResponse.Success(customAlbumList)
    }

    suspend fun getPhotoListPerAlbum(
        apiService: ApiService,
        albumId: Int
    ): ApiResponse<List<PhotoResponse>> {
        val photoList = apiService.getPhotoListPerAlbum(albumId)

        return ApiResponse.Success(photoList)
    }

}