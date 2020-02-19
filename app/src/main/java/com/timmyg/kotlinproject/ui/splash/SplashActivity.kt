package com.timmyg.kotlinproject.ui.splash

import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.timmyg.kotlinproject.ui.base.BaseActivity
import com.timmyg.kotlinproject.ui.main.MainActivity

class SplashActivity: BaseActivity<Boolean?, SplashViewState>() {
    override val model by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override val layoutRes: Int? = null

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({model.requestUser()}, 1000)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}