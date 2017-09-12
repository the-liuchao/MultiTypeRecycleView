package com.example.liuchao.kotlin.util

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by yangrenije on 2017/6/16.
 */

class ToastUtil private constructor() {
    private object Holder {
        val INSTANCE = ToastUtil()
    }

    companion object {
        val instance: ToastUtil by lazy { Holder.INSTANCE }
//        @JvmStatic fun toast(ctx: Context, msg: String) {
//            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
//        }

        @JvmStatic fun Activity.toast(msg: String) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        @JvmStatic fun Fragment.toast(msg: String) {
            Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun toast(ctx: Context?, msg: String, duration: Int) {
        Toast.makeText(ctx, msg, duration).show()
    }

    /* fun toast(ctx: Context, msg: String) {
         Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
     }*/
}