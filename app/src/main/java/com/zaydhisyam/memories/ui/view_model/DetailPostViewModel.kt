package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailPostViewModel(
    context: Application,
    private val useCase: MyUseCase
) : AndroidViewModel(context) {

    private val _commentListPerPost = MutableLiveData<Resource<List<Comment>>>()
    val commentListPerPost: LiveData<Resource<List<Comment>>>
        get() = _commentListPerPost

    fun setCommentListPerPostLiveData(postId: Int) =
        viewModelScope.launch {
            useCase.getCommentListPerPost(postId).collect {
                _commentListPerPost.value = it
            }
        }
}