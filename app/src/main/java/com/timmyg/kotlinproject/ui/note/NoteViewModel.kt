package com.timmyg.kotlinproject.ui.note

import androidx.lifecycle.ViewModel
import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.Note

class NoteViewModel:ViewModel() {
    private var pendintNote:Note ?= null

    fun save(note: Note){
        pendintNote = note
    }

    override fun onCleared() {
        pendintNote?.let {
            NoteRepository.saveNote(it)  }
        }

}