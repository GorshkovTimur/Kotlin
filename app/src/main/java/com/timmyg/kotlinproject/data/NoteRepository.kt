package com.timmyg.kotlinproject.data

import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.provider.RemoteDataProvider

class NoteRepository(val remoteProvider: RemoteDataProvider) {
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
    fun deleteNote(id: String) = remoteProvider.deleteNote(id)
}