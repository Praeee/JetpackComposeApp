package com.praeee.jetpackcomposeapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

) : ViewModel() {

    val value = "value"
    init {
        Log.d(TAG,"init block of NewsViewModel")
        doSomething()
    }

    private fun doSomething() {
        print("doSomething")
    }

    companion object {
        const val TAG = "NewsViewModel"
    }

}