package com.timmyg.kotlinproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.NoteResult
import com.timmyg.kotlinproject.ui.base.BaseViewModel

class MainViewModel(): BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult>{
        override fun onChanged(t: NoteResult?) {
            t ?: return
            when (t){
                is NoteResult.Succes<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = MainViewState(error =  t.error)
                }
            }
        }

    }

    private val repositoryNotes = NoteRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)

        }
    fun viewState(): LiveData<MainViewState> = viewStateLiveData

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
        super.onCleared()
    }
}