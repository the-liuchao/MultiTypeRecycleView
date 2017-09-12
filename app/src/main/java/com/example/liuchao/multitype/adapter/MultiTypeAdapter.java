package com.example.liuchao.multitype.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuchao.multitype.factory.TypeFactory;
import com.example.liuchao.multitype.holder.BaseViewHolder;
import com.example.liuchao.multitype.model.Visitable;

import java.util.List;

/**
 * Created by liuchao on 2017/6/14.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private TypeFactory typeFactory;
    private List<Visitable> models;

    public MultiTypeAdapter(List<Visitable> models, TypeFactory typeFactory) {
        this.models = models;
        this.typeFactory = typeFactory;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), viewType, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(-1, -2);
        itemView.setLayoutParams(params);
        return typeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindData(models.get(position), position, this);
    }

    @Override
    public int getItemViewType(int position) {
        if (null == typeFactory) return -1;
        return models.get(position).type(typeFactory);
    }

    @Override
    public int getItemCount() {
        return null == models ? 0 : models.size();
    }
}
