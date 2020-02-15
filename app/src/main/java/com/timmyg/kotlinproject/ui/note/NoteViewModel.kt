package com.timmyg.kotlinproject.ui.note


import androidx.lifecycle.Observer
import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.NoteResult
import com.timmyg.kotlinproject.ui.base.BaseViewModel


class NoteViewModel: BaseViewModel <Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote:Note? = null

    fun save(note: Note){
        pendingNote = note
    }

    fun loadNote(noteId: String){
        NoteRepository.getNoteById(noteId).observeForever(object : Observer<NoteResult>{
            override fun onChanged(t: NoteResult?) {
                t ?: return
                when (t){
                    is NoteResult.Success<*> -> {
                        viewStateLiveData.value = NoteViewState(note = t.data as? Note)
                    }
                    is NoteResult.Error -> {
                        viewStateLiveData.value = NoteViewState(error =  t.error)
                    }
                }
            }

        })
    }

    override fun onCleared() {
        pendingNote?.let {
            NoteRepository.saveNote(it)  }
        }

}