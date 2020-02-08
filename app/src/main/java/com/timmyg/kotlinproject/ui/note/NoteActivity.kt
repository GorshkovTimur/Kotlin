package com.timmyg.kotlinproject.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.timmyg.kotlinproject.R
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity: BaseActivity<Note?, NoteViewState>() {
    companion object{
        private val EXTRA_NOTE = NoteActivity::class.java.name + "EXTRA NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String ?= null){
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            context.startActivity(intent)
        }
    }

    private var note:Note? = null
    override val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_note

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

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.new_note_title)
                }
        }


    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = this.note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note!!.lastChange)
        } ?: let {
            getString(R.string.new_note_title)
        }
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

        note = note?.copy(
                title = et_title.text.toString(),
                text = et_body.text.toString()
        ) ?: Note(UUID.randomUUID().toString(),
                et_title.text.toString(),
                et_body.text.toString(),
                Note.Color.YELLOW,
                Date())

        note?.let { viewModel.save(it) }

    }

    }
