package com.zaydhisyam.memories.data.source.remote

import android.util.Log
import com.zaydhisyam.memories.data.DataUtils
import com.zaydhisyam.memories.data.source.remote.api.ApiResponse
import com.zaydhisyam.memories.data.source.remote.api.ApiService
import com.zaydhisyam.memories.data.source.remote.response_classes.CommentResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.PhotoResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.UserResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomAlbumResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getCustomPostList()
            : Flow<ApiResponse<List<CustomPostResponse>>> =
        flow {
            try {
                val userList = apiService.getUserList()
                val postList = apiService.getPostList()
                val customPostList = DataUtils.createCustomPostList(userList, postList)

                emit(ApiResponse.Success(customPostList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getCommentListPerPost(postId: Int)
            : Flow<ApiResponse<List<CommentResponse>>> =
        flow {
            try {
                val commentList = apiService.getCommentListPerPost(postId)

                emit(ApiResponse.Success(commentList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getCustomAlbumListPerUser(userId: Int)
            : Flow<ApiResponse<List<CustomAlbumResponse>>> =
        flow {
            try {
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

                emit(ApiResponse.Success(customAlbumList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getPhotoListPerAlbum(albumId: Int)
            : Flow<ApiResponse<List<PhotoResponse>>> =
        flow {
            try {
                val photoList = apiService.getPhotoListPerAlbum(albumId)

                emit(ApiResponse.Success(photoList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(Dispatchers.IO)
}