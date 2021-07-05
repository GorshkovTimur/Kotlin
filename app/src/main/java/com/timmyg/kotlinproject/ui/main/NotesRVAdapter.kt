package com.timmyg.kotlinproject.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.timmyg.kotlinproject.R
import com.timmyg.kotlinproject.common.getColorInt
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text
            (this as CardView).setCardBackgroundColor(note.color.getColorInt(context))
            itemView.setOnClickListener {
                onItemViewClick?.invoke(note)
            }

        }
    }
}