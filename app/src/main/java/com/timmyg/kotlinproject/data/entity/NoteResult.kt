package com.timmyg.kotlinproject.data.entity

sealed class NoteResult {
    data class Succes<out T>(val data : T) : NoteResult()
    data class Error(val error:Throwable) : NoteResult()
}