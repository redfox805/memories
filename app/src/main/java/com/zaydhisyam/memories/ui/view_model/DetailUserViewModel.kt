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
import com.zaydhisyam.memories.domain.model.Album
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserViewModel(
    context: Application,
    private val useCase: MyUseCase,
    private val myDispatchers: MyDispatcherProvider = MyDefaultDispatcherProvider()
) : AndroidViewModel(context) {

    private val _albumListPerUser = MutableLiveData<Resource<List<Album>>>()
    val albumListPerUser: LiveData<Resource<List<Album>>>
        get() = _albumListPerUser

    fun setAlbumListPerUserLiveData(userId: Int) {
        if (_albumListPerUser.value == null)
            viewModelScope.launch {
//                Log.d("TAG", "setAlbumListPerUserLiveData: called")
                withContext(myDispatchers.IO) {
                    useCase.getAlbumListPerUser(userId).collect {
                        _albumListPerUser.postValue(it)
                    }
                }
            }
    }
}