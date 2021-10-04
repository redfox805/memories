package com.zaydhisyam.memories.ui.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zaydhisyam.memories.data.source.remote.Resource
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailUserViewModel(
    context: Application,
    private val useCase: MyUseCase
) : AndroidViewModel(context) {

    private val _albumListPerUser = MutableLiveData<Resource<List<Album>>>()
    val albumListPerUser: LiveData<Resource<List<Album>>>
        get() = _albumListPerUser

    fun setAlbumListPerUserLiveData(userId: Int) =
        viewModelScope.launch {
            useCase.getAlbumListPerUser(userId).collect {
                _albumListPerUser.value = it
            }
        }
}