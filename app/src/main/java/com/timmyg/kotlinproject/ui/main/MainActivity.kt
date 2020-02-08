package com.timmyg.kotlinproject.ui.main

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.timmyg.kotlinproject.ui.note.NoteActivity
import com.timmyg.kotlinproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recycler_view.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter { note ->
            NoteActivity.start(this, note)
        }
        recycler_view.adapter = adapter

        viewModel.viewState().observe(this, Observer{
            it?.let {adapter.notes = it.notes}
        })

        fab.setOnClickListener{
            NoteActivity.start(this)
        }
    }
}
