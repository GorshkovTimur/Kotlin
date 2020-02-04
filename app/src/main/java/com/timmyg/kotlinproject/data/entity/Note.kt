package com.timmyg.kotlinproject.data.entity

data class Note (
        val id:String,
        val title:String,
        val text:String,
        val color:Color)

{
    override fun equals(other: Any?): Boolean {
        if (this===other) return true
        if(javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        return true
    }

    enum class Color {
        WHITE,
        YELLOW,
        GREEN,
        BLUE,
        RED,
        VIOLET,
        PINK
    }
}