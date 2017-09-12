package com.example.liuchao.kotlin

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.liuchao.multitype.R
import kotlinx.android.synthetic.main.activity_kotlin.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.DisplayMetrics
import android.view.*
import android.widget.Toast
import com.example.liuchao.kotlin.util.ToastUtil
import com.example.liuchao.kotlin.util.ToastUtil.Companion.toast
import kotlinx.android.synthetic.main.line_group.view.*
import kotlinx.android.synthetic.main.view_delete_layout.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar


class KotlinActivity : AppCompatActivity() {

    companion object {
        @JvmStatic fun startActivity(act: Activity): Unit {
            act.startActivity(Intent(act, KotlinActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_kotlin)
        contentView?.backgroundColor = ContextCompat.getColor(this@KotlinActivity, R.color.colorPrimary)
        verticalLayout() {
            lparams(width = matchParent, height = matchParent) {
                horizontalGravity = Gravity.CENTER_HORIZONTAL
            }
            textView() {
                var statusBarHeight = -1
                val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
                if (resourceId > 0) {
                    statusBarHeight = resources.getDimensionPixelSize(resourceId)
                }
                backgroundColor = ContextCompat.getColor(this@KotlinActivity, R.color.colorAccent)
                lparams(width = matchParent, height = statusBarHeight)
            }
            setToolbar()
            textView() {
                text = "Kotlin Anko Hello"
                backgroundColor = ContextCompat.getColor(this@KotlinActivity, R.color.colorPrimary)
                textColor = resources.getColor(R.color.white)
                padding = dip(15)
                onClick { toast("anko 测试用例") }
                lparams(width = wrapContent, height = wrapContent) {
                    topMargin = dip(15)
                }
            }
        }
        /* val window = getWindow()
         //设置透明状态栏,这样才能让 ContentView 向上
         window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
         //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
         window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
         //设置状态栏颜色
         window.setStatusBarColor(R.color.colorPrimaryDark)
         val mChildView = (findViewById(Window.ID_ANDROID_CONTENT) as ViewGroup).getChildAt(0)
         if (mChildView != null) {
             //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
             ViewCompat.setFitsSystemWindows(mChildView, true)
             (mChildView as ViewGroup).clipToPadding = true
             mChildView.setBackgroundResource(R.color.colorAccent)
         }*/
//        test.text = "hello kotlin!"
        toast("Kotlin 你好！")
//        supportFragmentManager.beginTransaction().add(R.id.framelayout, KotlinFragment()).commit()
    }

    private fun _LinearLayout.setToolbar() {
        toolbar(R.style.Theme_AppCompat_Light_NoActionBar) {
            backgroundColor = ContextCompat.getColor(this@KotlinActivity, R.color.colorAccent)
            popupTheme = R.style.Base_ThemeOverlay_AppCompat_Light
            setNavigationIcon(android.R.drawable.ic_media_previous)
            setNavigationOnClickListener {
                finish()
            }
            textView {
                text = "登录"
                textSize = 18 * 1.0f
                gravity = Gravity.CENTER
                textColor = ContextCompat.getColor(this@KotlinActivity, android.R.color.white)
                lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }
            }
            imageView() {
                image = ContextCompat.getDrawable(this@KotlinActivity, R.mipmap.ic_launcher)
                onClick { toast("右边菜单1") }
                lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.RIGHT
                }
            }
            imageView() {
                image = ContextCompat.getDrawable(this@KotlinActivity, R.mipmap.ic_launcher)
                onClick { toast("右边菜单2") }
                lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.RIGHT
                }
            }
        }
    }

//    fun Activity.toast(msg: String, duration: Int) {
//        Toast.makeText(this, msg, duration).show()
//    }
}
