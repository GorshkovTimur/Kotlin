package com.timmyg.kotlinproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.ui.base.BaseViewModel

class MainViewModel(): BaseViewModel<List<Note>?, MainViewState>() {

    init {

        NoteRepository.notesLiveData.observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it) ?: MainViewState(it)
        }


    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}