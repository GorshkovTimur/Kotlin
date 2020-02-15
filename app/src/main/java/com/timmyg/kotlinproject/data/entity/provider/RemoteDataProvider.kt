package com.timmyg.kotlinproject.data.entity.provider

import androidx.lifecycle.LiveData
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.data.entity.NoteResult
import com.timmyg.kotlinproject.data.entity.User

interface RemoteDataProvider {
    fun subscribeToAllNotes() : LiveData<NoteResult>
    fun getNoteById(id: String) : LiveData<NoteResult>
    fun saveNote (note: Note) : LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}