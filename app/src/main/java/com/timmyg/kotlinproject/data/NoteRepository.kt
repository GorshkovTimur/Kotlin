package com.timmyg.kotlinproject.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.NoteResult
import com.timmyg.kotlinproject.data.entity.provider.RemoteDataProvider

object NoteRepository {

private val remoteProvider: RemoteDataProvider = object : RemoteDataProvider {
    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        return MutableLiveData<NoteResult>()
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        return MutableLiveData<NoteResult>()
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        return MutableLiveData<NoteResult>()
    }

}

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
}