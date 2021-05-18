package com.example.composetest01.ui.theme

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class ApplicationActivity : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}