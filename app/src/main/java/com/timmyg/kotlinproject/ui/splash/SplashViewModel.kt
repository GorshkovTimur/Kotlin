package com.timmyg.kotlinproject.ui.splash

import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.errors.NoAuthException
import com.timmyg.kotlinproject.ui.base.BaseViewModel

class SplashViewModel(private val noteRepository: NoteRepository):BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser(){
        noteRepository.getCurrentUser().observeForever{
            viewStateLiveData.value = it?.let{
                SplashViewState(authenticated = true)
            } ?: let { SplashViewState(error = NoAuthException()) }

            }
        }


    }
