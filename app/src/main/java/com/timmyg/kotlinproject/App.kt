package com.timmyg.kotlinproject

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}