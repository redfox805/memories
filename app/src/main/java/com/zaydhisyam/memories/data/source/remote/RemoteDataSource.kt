package com.zaydhisyam.memories.data.source.remote

import com.zaydhisyam.memories.coroutines.MyDefaultDispatcherProvider
import com.zaydhisyam.memories.coroutines.MyDispatcherProvider
import com.zaydhisyam.memories.data.DataUtils
import com.zaydhisyam.memories.data.source.remote.api.ApiResponse
import com.zaydhisyam.memories.data.source.remote.api.ApiService
import com.zaydhisyam.memories.data.source.remote.response_classes.CommentResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.PhotoResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomAlbumResponse
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class RemoteDataSource(
    private val apiService: ApiService,
    private val myDispatchers: MyDispatcherProvider = MyDefaultDispatcherProvider()
) {

    suspend fun getCustomPostList()
            : Flow<ApiResponse<List<CustomPostResponse>>> =
        flow {
            /**
             * comment espresso before do unit testing
             * uncomment before do instrument testing
             */
//            EspressoIdlingResource.increment()
//            Log.d("TAG", "Flow in DS - current context: ${currentCoroutineContext()}")
            try {
                val userList = apiService.getUserList()
                val postList = apiService.getPostList()
                val customPostList = DataUtils.createCustomPostList(userList, postList)

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Success(customPostList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(myDispatchers.IO)

    suspend fun getCommentListPerPost(postId: Int)
            : Flow<ApiResponse<List<CommentResponse>>> =
        flow {
            //EspressoIdlingResource.increment()
            try {
                val commentList = apiService.getCommentListPerPost(postId)

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Success(commentList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(myDispatchers.IO)

    suspend fun getCustomAlbumListPerUser(userId: Int)
            : Flow<ApiResponse<List<CustomAlbumResponse>>> =
        flow {
//            EspressoIdlingResource.increment()
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

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Success(customAlbumList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(myDispatchers.IO)

    suspend fun getPhotoListPerAlbum(albumId: Int)
            : Flow<ApiResponse<List<PhotoResponse>>> =
        flow {
//            EspressoIdlingResource.increment()
            try {
                val photoList = apiService.getPhotoListPerAlbum(albumId)

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Success(photoList))
            } catch (ex: Exception) {
                var errorMessage = ex.toString()
                if (ex is UnknownHostException)
                    errorMessage = "No internet connection"

//                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(errorMessage))
            }
        }.flowOn(myDispatchers.IO)
}