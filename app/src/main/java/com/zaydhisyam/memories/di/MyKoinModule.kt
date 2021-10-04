package com.zaydhisyam.memories.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zaydhisyam.memories.data.MyRepository
import com.zaydhisyam.memories.data.source.remote.RemoteDataSource
import com.zaydhisyam.memories.data.source.remote.api.ApiService
import com.zaydhisyam.memories.domain.repository.IMyRepository
import com.zaydhisyam.memories.domain.usecase.MyInteractor
import com.zaydhisyam.memories.domain.usecase.MyUseCase
import com.zaydhisyam.memories.ui.view_model.DetailPostViewModel
import com.zaydhisyam.memories.ui.view_model.DetailUserViewModel
import com.zaydhisyam.memories.ui.view_model.PhotoListViewModel
import com.zaydhisyam.memories.ui.view_model.PostListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiServiceModule = module {
    single {
        MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        ) as Converter.Factory
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IMyRepository> {
        MyRepository(get())
    }
}

val useCaseModule = module {
    factory<MyUseCase> { MyInteractor(get()) }
}

val viewModelModule = module {
    viewModel { PostListViewModel(androidContext() as Application, get()) }
    viewModel { DetailPostViewModel(androidContext() as Application, get()) }
    viewModel { DetailUserViewModel(androidContext() as Application, get()) }
    viewModel { PhotoListViewModel(androidContext() as Application, get()) }
}