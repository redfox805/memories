package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.usecase.MyUseCase

class DetailPostViewModel(
    context: Application,
    private val useCase: MyUseCase
) : AndroidViewModel(context) {

    fun getCommentListPerPost(postId: Int): LiveData<Resource<List<Comment>>> =
        useCase.getCommentListPerPost(postId).asLiveData()
}