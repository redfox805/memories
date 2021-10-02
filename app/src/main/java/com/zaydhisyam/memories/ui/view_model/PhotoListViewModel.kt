package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Photo
import com.zaydhisyam.memories.domain.usecase.MyUseCase

class PhotoListViewModel(
    context: Application,
    private val useCase: MyUseCase
) : AndroidViewModel(context) {

    fun getPhotoListPerAlbum(albumId: Int): LiveData<Resource<List<Photo>>> =
        useCase.getPhotoListPerAlbum(albumId).asLiveData()
}