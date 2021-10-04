package com.zaydhisyam.memories.data.source

import com.zaydhisyam.memories.data.source.remote.RemoteDataSourceFakeResponse
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.data.source.remote.api.ApiResponse
import com.zaydhisyam.memories.data.source.remote.api.ApiService
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.model.Photo
import com.zaydhisyam.memories.domain.model.Post

object RepositoryFakeResponse {

    suspend fun getPostList(apiService: ApiService): Resource<List<Post>> {
        val apiResponse =
            RemoteDataSourceFakeResponse.getCustomPostList(apiService) as ApiResponse.Success

        return Resource.Success(
            apiResponse.data.map { customPostResponse ->
                Post.importFromCustomPostResponse(customPostResponse)
            }
        )
    }

    suspend fun getCommentListPerPost(
        apiService: ApiService,
        postId: Int
    ): Resource<List<Comment>> {
        val apiResponse =
            RemoteDataSourceFakeResponse.getCommentListPerPost(
                apiService,
                postId
            ) as ApiResponse.Success

        return Resource.Success(
            apiResponse.data.map { commentResponse ->
                Comment.importFromCommentResponse(commentResponse)
            }
        )
    }

    suspend fun getAlbumListPerUser(
        apiService: ApiService,
        userId: Int
    ): Resource<List<Album>> {
        val apiResponse =
            RemoteDataSourceFakeResponse.getCustomAlbumListPerUser(
                apiService,
                userId
            ) as ApiResponse.Success

        return Resource.Success(
            apiResponse.data.map { customAlbumResponse ->
                Album.importFromCustomAlbumResponse(customAlbumResponse)
            }
        )
    }

    suspend fun getPhotoListPerAlbum(
        apiService: ApiService,
        albumId: Int
    ): Resource<List<Photo>> {
        val apiResponse =
            RemoteDataSourceFakeResponse.getPhotoListPerAlbum(
                apiService,
                albumId
            ) as ApiResponse.Success

        return Resource.Success(
            apiResponse.data.map { photoResponse ->
                Photo.importFromPhotoResponse(photoResponse)
            }
        )
    }
}