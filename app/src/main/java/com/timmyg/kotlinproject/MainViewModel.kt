package com.timmyg.kotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timmyg.kotlinproject.data.NoteRepository

class MainViewModel(): ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(NoteRepository.notes)
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}