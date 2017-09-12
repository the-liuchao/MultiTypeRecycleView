package com.example.liuchao.kotlin

import android.app.Application

/**
 * Created by yangrenije on 2017/6/16.
 */
class KotlinApplication : Application() {
    var field: KotlinApplication? = this@KotlinApplication

    companion object {
        fun get() {
            synchronized(KotlinApplication::class.java) {
            }
        }
    }


    override fun onCreate() {
        super.onCreate()
    }
}

