package com.praeee.jetpackcomposeapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetpackComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }

    companion object {
        const val TAG = "JetpackComposeApplication"
    }

}