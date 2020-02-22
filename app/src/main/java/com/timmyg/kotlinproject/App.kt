package com.timmyg.kotlinproject

import android.app.Application
import com.timmyg.kotlinproject.di.appModule
import com.timmyg.kotlinproject.di.mainModule
import com.timmyg.kotlinproject.di.noteModule
import com.timmyg.kotlinproject.di.splashModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}