package com.timmyg.kotlinproject.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.provider.FireStoreProvider
import com.timmyg.kotlinproject.data.entity.provider.RemoteDataProvider
import com.timmyg.kotlinproject.ui.main.MainViewModel
import com.timmyg.kotlinproject.ui.note.NoteViewModel
import com.timmyg.kotlinproject.ui.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single {FirebaseFirestore.getInstance()}
    single { FireStoreProvider(get(), get())} bind RemoteDataProvider::class
    single { NoteRepository(get()) }
}

val splashModule = module{
    viewModel {SplashViewModel(get())}
    }

val mainModule = module{
    viewModel { MainViewModel(get()) }
}

val noteModule = module{
    viewModel { NoteViewModel(get()) }
}