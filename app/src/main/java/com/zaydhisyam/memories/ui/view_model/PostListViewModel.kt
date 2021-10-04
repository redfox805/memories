package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.zaydhisyam.memories.domain.usecase.MyUseCase

class PostListViewModel(
    context: Application,
    useCase: MyUseCase
) : AndroidViewModel(context) {

    val postList = useCase.getPostList().asLiveData()
}