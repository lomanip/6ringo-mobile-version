package com.example.android_6ringo

import android.app.Application

class Ringo6Application:Application() {
    lateinit var container: Container

    override fun onCreate() {
        super.onCreate()
        container = Container().setupApplicationService()
    }
}