package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zaydhisyam.memories.coroutines.MyDefaultDispatcherProvider
import com.zaydhisyam.memories.coroutines.MyDispatcherProvider
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Photo
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoListViewModel(
    context: Application,
    private val useCase: MyUseCase,
    private val myDispatchers: MyDispatcherProvider = MyDefaultDispatcherProvider()
) : AndroidViewModel(context) {

    private val _photoList = MutableLiveData<Resource<List<Photo>>>()
    val photoList: LiveData<Resource<List<Photo>>> get() = _photoList

    fun setPhotoListPerAlbumLiveData(albumId: Int) {
        if (_photoList.value == null)
            viewModelScope.launch {
//                Log.d("TAG", "setPhotoListPerAlbumLiveData: called")
                withContext(myDispatchers.IO) {
                    useCase.getPhotoListPerAlbum(albumId).collect {
                        _photoList.postValue(it)
                    }
                }
            }
    }
}