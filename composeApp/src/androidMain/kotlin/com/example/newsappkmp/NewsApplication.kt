package com.example.newsappkmp

import android.app.Application
import com.example.newsappkmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(androidContext = this@NewsApplication)
        }
    }
}