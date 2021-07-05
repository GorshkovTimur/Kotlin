package com.timmyg.kotlinproject.ui.splash

import com.timmyg.kotlinproject.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean?= null, error: Throwable?=null):BaseViewState<Boolean?>(authenticated, error)