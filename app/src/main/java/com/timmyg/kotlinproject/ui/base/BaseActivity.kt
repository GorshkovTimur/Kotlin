package com.timmyg.kotlinproject.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.timmyg.kotlinproject.R
import com.timmyg.kotlinproject.data.entity.errors.NoAuthException

abstract class BaseActivity<T, S: BaseViewState<T>> : AppCompatActivity() {

    companion object{
        const val RC_SING_IN = 4242
    }

    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this, object : Observer<S>{
            override fun onChanged(t: S?) {
                    t ?:  return
                t.error?.let {
                    renderError(it)
                    return
                }
            }

        })
    }

    abstract fun renderData(data: T)

    private fun renderError(error: Throwable) {
        when(error){
            is NoAuthException-> startLogin()
            else -> error.message?.let { showError(it) }
        }

    }

    private fun startLogin() {
        val providers = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
                AuthUI.getInstance().
                        createSignInIntentBuilder()
                        .setLogo(R.drawable.ic_android_black_24dp)
                        .setTheme(R.style.LoginStyle)
                        .setAvailableProviders(providers)
                        .build()
        , RC_SING_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SING_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
    }

    protected fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


}