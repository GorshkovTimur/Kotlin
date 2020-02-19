package com.timmyg.kotlinproject.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import com.timmyg.kotlinproject.ui.note.NoteActivity
import com.timmyg.kotlinproject.R
import com.timmyg.kotlinproject.data.entity.Note
import com.timmyg.kotlinproject.ui.base.BaseActivity
import com.timmyg.kotlinproject.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>? , MainViewState>(){

    companion object{
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val model: MainViewModel by viewModel()

    override val layoutRes = R.layout.activity_main
    lateinit var adapter: NotesRVAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler_view.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter { note ->
            NoteActivity.start(this, note.id)
        }
        recycler_view.adapter = adapter

        fab.setOnClickListener{
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?)=
            MenuInflater(this).inflate(R.menu.main, menu)
            .let { true }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.logout ->  showLogoutDialog()?.let{true}
        else -> false
    }

    private fun showLogoutDialog() {
        alert {
            titleResource = R.string.exit
            messageResource = R.string.sure
            positiveButton("Да") { onLogout() }
            negativeButton("Нет") { dialog -> dialog.dismiss() }
        }.show()
    }

    private fun onLogout(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener{
                    startActivity(Intent(this, SplashActivity::class.java))
                    finish()
                }
    }
}
