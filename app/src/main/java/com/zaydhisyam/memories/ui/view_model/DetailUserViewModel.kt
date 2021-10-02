package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.usecase.MyUseCase

class DetailUserViewModel(
    context: Application,
    private val useCase: MyUseCase
): AndroidViewModel(context) {

    fun getAlbumListPerUser(userId: Int): LiveData<Resource<List<Album>>> =
        useCase.getAlbumListPerUser(userId).asLiveData()
}