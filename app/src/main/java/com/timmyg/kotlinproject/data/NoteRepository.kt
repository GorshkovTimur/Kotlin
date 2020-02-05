package com.timmyg.kotlinproject.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.timmyg.kotlinproject.data.entity.Note
import java.util.*

object NoteRepository {

    private val notesLiveData  = MutableLiveData<List<Note>>()

     var notes: MutableList<Note> = mutableListOf(Note(UUID.randomUUID().toString(), "Заметка1", "Текст заметки 1", Note.Color.BLUE),
            Note(UUID.randomUUID().toString(),"Заметка2", "Текст заметки 2", Note.Color.RED),
            Note(UUID.randomUUID().toString(),"Заметка3", "Текст заметки 3", Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"Заметка4", "Текст заметки 4", Note.Color.PINK),
            Note(UUID.randomUUID().toString(),"Заметка5", "Текст заметки 5", Note.Color.YELLOW),
            Note(UUID.randomUUID().toString(),"Заметка6", "Текст заметки 6", Note.Color.VIOLET)
    )

    init {
        notesLiveData.value = notes
    }

    fun saveNote(note: Note){
        addOrReplace(note)
        notesLiveData.value = notes

    }

    private fun addOrReplace(note: Note){
        for (i in notes.indices){
            if (notes[i] == note){
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

}