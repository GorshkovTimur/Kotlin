package com.timmyg.kotlinproject.data

import android.graphics.Color
import com.timmyg.kotlinproject.data.entity.Note

object NoteRepository {
    private val notes: List<Note>

    init{
        notes = listOf(Note("Заметка1", "Текст заметки 1", Color.BLUE),
                Note("Заметка2", "Текст заметки 2", Color.RED),
                Note("Заметка3", "Текст заметки 3", Color.GREEN),
                Note("Заметка4", "Текст заметки 4", Color.YELLOW),
                Note("Заметка5", "Текст заметки 5", Color.GRAY),
                Note("Заметка6", "Текст заметки 6", Color.MAGENTA)
                )
    }

}