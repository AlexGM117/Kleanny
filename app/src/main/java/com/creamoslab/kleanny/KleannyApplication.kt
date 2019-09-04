package com.creamoslab.kleanny

import android.app.Application
import android.content.Context

class KleannyApplication : Application() {
    init {
        application = this
    }

    companion object {
        private var application: Application? = null

        fun applicationContext(): Context {
            return application!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}