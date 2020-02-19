package com.timmyg.kotlinproject

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.timmyg.kotlinproject.di.appModule
import com.timmyg.kotlinproject.di.noteModule
import com.timmyg.kotlinproject.di.splashModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin(this, listOf(appModule, splashModule, noteModule))
    }
}