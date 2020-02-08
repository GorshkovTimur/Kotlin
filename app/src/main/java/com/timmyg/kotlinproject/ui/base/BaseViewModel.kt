package com.timmyg.kotlinproject.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewMode<T, S: BaseViewState<T>> : ViewModel() {

    open val viewStateLiveData = MutableLiveData<S>()

    open fun getViewState(): LiveData<S> = viewStateLiveData
}