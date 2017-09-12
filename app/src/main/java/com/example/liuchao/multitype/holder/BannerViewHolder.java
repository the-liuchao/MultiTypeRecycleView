package com.example.liuchao.multitype.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuchao.multitype.R;
import com.example.liuchao.multitype.adapter.MultiTypeAdapter;
import com.example.liuchao.multitype.model.BannerModel;

/**
 * Created by liuchao on 2017/6/14.
 */

public class BannerViewHolder extends BaseViewHolder<BannerModel> {

    private final TextView imageView;

    public BannerViewHolder(View itemView) {
        super(itemView);
        imageView = (TextView) itemView.findViewById(R.id.imageView);
    }

    @Override
    public void bindData(BannerModel model, int position, MultiTypeAdapter adapter) {
        //如果这里model是一个
        imageView.setBackgroundResource(R.mipmap.banner1);
        itemView.setBackgroundColor(Color.parseColor("#ff9900"));
    }
}
