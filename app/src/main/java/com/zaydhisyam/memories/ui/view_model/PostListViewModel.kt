package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.data.source.remote.response_classes.custom.CustomPostResponse
import com.zaydhisyam.memories.domain.model.*
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import kotlinx.coroutines.flow.Flow

class PostListViewModel(
    context: Application,
    private val useCase: MyUseCase
) : AndroidViewModel(context) {

    fun getPostList(): LiveData<Resource<List<Post>>> =
        useCase.getPostList().asLiveData()
}