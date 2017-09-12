package com.example.liuchao.multitype.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.liuchao.multitype.adapter.MultiTypeAdapter;

/**
 * Created by yangrenije on 2017/6/14.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T model, int position, MultiTypeAdapter adapter);
}
