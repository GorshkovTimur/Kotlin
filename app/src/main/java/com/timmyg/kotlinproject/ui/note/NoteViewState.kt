package com.timmyg.kotlinproject.ui.note

import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null): BaseViewState<Note?>(note, error)