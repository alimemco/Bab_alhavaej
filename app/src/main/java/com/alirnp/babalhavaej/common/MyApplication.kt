package com.alirnp.babalhavaej.common

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import com.alirnp.babalhavaej.BuildConfig
import timber.log.Timber


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(MyTimberTree())
        }
    }


    /** A tree which logs important information for crash reporting.  */
    private class MyTimberTree : Timber.Tree() {
        @SuppressLint("LogNotTimber")
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            Log.i("LOG_ME", message)
        }
    }
}