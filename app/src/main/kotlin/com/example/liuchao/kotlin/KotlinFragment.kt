package com.example.liuchao.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.liuchao.multitype.R
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by yangrenije on 2017/7/5.
 */
class KotlinFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.test -> {
                toast("Hello World! ")
            }
            R.id.action0 -> {
                toast("action0 ")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_test, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        test.setOnClickListener { toast("Hello World!") }
        test?.setOnClickListener(this)
    }

    fun Fragment.toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()

    }
}