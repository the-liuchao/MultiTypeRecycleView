package com.example.liuchao.multitype.factory;

import android.view.View;

import com.example.liuchao.multitype.holder.BaseViewHolder;
import com.example.liuchao.multitype.model.Visitable;

/**
 * Created by yangrenije on 2017/6/14.
 */

public interface TypeFactory {
    int type(Visitable visitable);

    BaseViewHolder createViewHolder(int type, View itemView);
}
