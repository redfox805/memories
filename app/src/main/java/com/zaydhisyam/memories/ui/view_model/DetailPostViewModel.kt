package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zaydhisyam.memories.coroutines.MyDefaultDispatcherProvider
import com.zaydhisyam.memories.coroutines.MyDispatcherProvider
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Comment
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPostViewModel(
    context: Application,
    private val useCase: MyUseCase,
    private val myDispatchers: MyDispatcherProvider = MyDefaultDispatcherProvider()
) : AndroidViewModel(context) {

    private val _commentListPerPost = MutableLiveData<Resource<List<Comment>>>()
    val commentListPerPost: LiveData<Resource<List<Comment>>>
        get() = _commentListPerPost

    fun setCommentListPerPostLiveData(postId: Int) {
        if (_commentListPerPost.value == null)
            viewModelScope.launch {
//                Log.d("TAG", "setCommentListPerPostLiveData: called")
                withContext(myDispatchers.IO) {
                    useCase.getCommentListPerPost(postId).collect {
                        _commentListPerPost.postValue(it)
                    }
                }
            }
    }
}