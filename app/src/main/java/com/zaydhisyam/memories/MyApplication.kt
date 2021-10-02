package com.zaydhisyam.memories

import android.app.Application
import com.zaydhisyam.memories.di.apiServiceModule
import com.zaydhisyam.memories.di.repositoryModule
import com.zaydhisyam.memories.di.useCaseModule
import com.zaydhisyam.memories.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    apiServiceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}