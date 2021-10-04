package com.zaydhisyam.memories.domain.usecase

import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.model.Photo
import com.zaydhisyam.memories.domain.model.Post
import com.zaydhisyam.memories.domain.repository.IMyRepository
import kotlinx.coroutines.flow.Flow

class MyInteractor(private val myRepository: IMyRepository) : MyUseCase {

    override fun getPostList(): Flow<Resource<List<Post>>> =
        myRepository.getPostList()

    override fun getCommentListPerPost(postId: Int): Flow<Resource<List<Comment>>> =
        myRepository.getCommentListPerPost(postId)

    override fun getAlbumListPerUser(userId: Int): Flow<Resource<List<Album>>> =
        myRepository.getAlbumListPerUser(userId)

    override fun getPhotoListPerAlbum(albumId: Int): Flow<Resource<List<Photo>>> =
        myRepository.getPhotoListPerAlbum(albumId)
}