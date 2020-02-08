package com.timmyg.kotlinproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.timmyg.kotlinproject.data.entity.Note
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity: AppCompatActivity() {
    companion object{
        private val EXTRA_NOTE = NoteActivity::class.java.name + "EXTRA NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
        private const val SAVE_DELAY = 2000L

        fun start(context: Context, note: Note ?= null){
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            context.startActivity(intent)
        }
    }

    private var note:Note? = null
    lateinit var viewModel:NoteViewModel

    val textChangeListener = object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            saveNote()
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(toolbar)


        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChange)
        } ?: getString(R.string.new_note_title)

        initView()

    }

    fun initView() {
        note?.let {
            et_title.setText(note?.title ?:"")
            et_body.setText(note?.text ?:"")
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    fun saveNote(){
        if (et_title != null && et_body.text!!.length < 3 ) return

        Handler().postDelayed({
        note = note?.copy(
                title = et_title.text.toString(),
                text = et_body.text.toString()
        ) ?: createNewNote()
            note?.let { viewModel.save(it) }
        }, SAVE_DELAY)
    }

    private fun createNewNote(): Note = Note(UUID.randomUUID().toString(),
            et_title.text.toString(),
            et_body.text.toString(),
            Note.Color.YELLOW,
            Date())

    }
