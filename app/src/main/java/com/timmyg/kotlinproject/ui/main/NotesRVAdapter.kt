package com.timmyg.kotlinproject.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timmyg.kotlinproject.R
import com.timmyg.kotlinproject.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter(val onItemViewClick : ((note:Note)->Unit)?=null) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )


    override fun getItemCount(): Int = notes.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(notes[position])
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text

            when(note.color){
                Note.Color.WHITE -> setBackgroundColor(Color.WHITE)
                Note.Color.YELLOW -> setBackgroundColor(Color.YELLOW)
                Note.Color.GREEN -> setBackgroundColor(Color.GREEN)
                Note.Color.BLUE -> setBackgroundColor(Color.BLUE)
                Note.Color.RED -> setBackgroundColor(Color.RED)
                Note.Color.VIOLET -> setBackgroundColor(Color.CYAN)
                Note.Color.PINK -> setBackgroundColor(Color.MAGENTA)
            }





        }

    }
}