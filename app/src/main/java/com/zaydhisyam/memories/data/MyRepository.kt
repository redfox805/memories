package com.zaydhisyam.memories.data

import android.util.Log
import com.zaydhisyam.memories.data.source.remote.RemoteDataSource
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.data.source.remote.api.ApiResponse
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.model.Photo
import com.zaydhisyam.memories.domain.model.Post
import com.zaydhisyam.memories.domain.repository.IMyRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class MyRepository(
    private val remoteDataSource: RemoteDataSource
) : IMyRepository {

    override fun getPostList(): Flow<Resource<List<Post>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResponse = remoteDataSource.getCustomPostList().single()) {
                is ApiResponse.Success -> {
                    Log.d("TAG", "Flow in when - current context: ${currentCoroutineContext()}")
                    emit(
                        Resource.Success(
                            apiResponse.data.map { customPostResponse ->
                                Post.importFromCustomPostResponse(customPostResponse)
                            }
                        )
                    )
                }
                is ApiResponse.Error -> emit(
                    Resource.Error(apiResponse.errorMessage)
                )
            }
        }

    override fun getCommentListPerPost(postId: Int): Flow<Resource<List<Comment>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource = remoteDataSource.getCommentListPerPost(postId).single()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        apiResource.data.map { commentResponse ->
                            Comment.importFromCommentResponse(commentResponse)
                        }
                    )
                )
                is ApiResponse.Error -> emit(Resource.Error(apiResource.errorMessage))
            }
        }

    override fun getAlbumListPerUser(userId: Int): Flow<Resource<List<Album>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource = remoteDataSource.getCustomAlbumListPerUser(userId).single()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        apiResource.data.map { customAlbumResponse ->
                            Album.importFromCustomAlbumResponse(customAlbumResponse)
                        }
                    )
                )
                is ApiResponse.Error -> emit(Resource.Error(apiResource.errorMessage))
            }
        }

    override fun getPhotoListPerAlbum(albumId: Int): Flow<Resource<List<Photo>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource = remoteDataSource.getPhotoListPerAlbum(albumId).single()) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        apiResource.data.map { photoResponse ->
                            Photo.importFromPhotoResponse(photoResponse)
                        }
                    )
                )
                is ApiResponse.Error -> emit(Resource.Error(apiResource.errorMessage))
            }
        }
}