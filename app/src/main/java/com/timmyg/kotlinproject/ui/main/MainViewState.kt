package com.timmyg.kotlinproject.ui.main

import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.ui.base.BaseViewState

class MainViewState(val notes: List<Note>, error: Throwable? = null) :
        BaseViewState<List<Note>?>(notes, error)