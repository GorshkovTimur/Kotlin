package com.timmyg.kotlinproject.ui.note


import androidx.lifecycle.Observer
import com.timmyg.kotlinproject.data.NoteRepository
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.NoteResult
import com.timmyg.kotlinproject.ui.base.BaseViewModel


class NoteViewModel(private val noteRepository: NoteRepository): BaseViewModel<NoteViewState.Data, NoteViewState>() {

    private val pendingNote:Note?
    get() = viewStateLiveData?.value?.data?.note

    fun save(note: Note){
        viewStateLiveData.value =  NoteViewState(NoteViewState.Data(note = note))
    }

    fun loadNote(noteId: String) {
        noteRepository.getNoteById(noteId).observeForever { result ->
            result.let {
                viewStateLiveData.value = when (result) {
                    is NoteResult.Success<*> -> NoteViewState(NoteViewState.Data(note = result.data as Note))
                    is NoteResult.Error -> NoteViewState(error = result.error)
                }
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            noteRepository.saveNote(it)  }
        }

    fun deleteNote() {
        pendingNote?.let {
            noteRepository.deleteNote(it.id).observeForever{result->
                viewStateLiveData.value = when (result) {
                    is NoteResult.Success<*> -> NoteViewState(NoteViewState.Data(isDeleted = true))
                    is NoteResult.Error -> NoteViewState(error = result.error)
                }

            }
        }
    }

}