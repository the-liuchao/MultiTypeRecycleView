package com.example.liuchao.multitype.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by yangrenije on 2017/7/19.
 */

public class Loger {
    public static void e(String tag, String str) {
        if (TextUtils.isEmpty(str)) return;
        int length = str.length();
        int start = 0;
        int end = 888;
        while (end < length) {
            Log.e(tag, str.substring(start, end));
            end = start;
            start = end + 800;
        }
    }
}

