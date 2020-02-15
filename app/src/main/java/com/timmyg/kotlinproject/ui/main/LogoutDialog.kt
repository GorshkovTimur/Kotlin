package com.timmyg.kotlinproject.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.timmyg.kotlinproject.R

class LogoutDialog: DialogFragment() {
    companion object{
        val TAG  = LogoutDialog::class.java.name + "TAG"
        fun createInstance() = LogoutDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = AlertDialog.Builder(context)
            .setTitle(getString(R.string.exit))
            .setMessage(getString(R.string.sure))
            .setPositiveButton("Да") {dialogInterface, which ->(activity as LogoutListener).onLogout() }
            .setNegativeButton("Нет") {dialogInterface, which -> dismiss()}
            .create()


    interface LogoutListener{
        fun onLogout()
    }
}