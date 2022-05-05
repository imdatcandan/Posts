package com.imdatcandan.posts

import android.app.Application
import com.imdatcandan.posts.di.appModule
import com.imdatcandan.posts.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class PostsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PostsApp)
            modules(arrayListOf(appModule, networkModule))
        }
    }
}