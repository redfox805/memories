package com.zaydhisyam.memories.data.source.remote

sealed class Resource<out R> {

    object Loading: Resource<Nothing>()

    data class Success<out T>(val data: T): Resource<T>()

    data class Error(val errorMessage: String): Resource<Nothing>()
}