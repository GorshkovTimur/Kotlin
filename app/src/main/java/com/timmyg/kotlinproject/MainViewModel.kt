package com.timmyg.kotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timmyg.kotlinproject.data.NoteRepository

class MainViewModel(): ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {

        NoteRepository.notesLiveData.observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it) ?: MainViewState(it)
        }


    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}