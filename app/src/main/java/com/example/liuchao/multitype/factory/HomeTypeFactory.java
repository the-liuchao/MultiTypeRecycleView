package com.example.liuchao.multitype.factory;

import android.view.View;

import com.example.liuchao.multitype.R;
import com.example.liuchao.multitype.holder.BannerViewHolder;
import com.example.liuchao.multitype.holder.BaseViewHolder;
import com.example.liuchao.multitype.holder.HomeListViewHolder;
import com.example.liuchao.multitype.model.BannerModel;
import com.example.liuchao.multitype.model.HomeListModel;
import com.example.liuchao.multitype.model.Visitable;

/**
 * Created by yangrenije on 2017/6/14.
 */

public class HomeTypeFactory implements TypeFactory {
    public static final int TYPE_RESOURCE_ONE = R.layout.home_banner_item;
    public static final int TYPE_RESOURCE_TWO = R.layout.home_list_item;

    @Override
    public int type(Visitable visitable) {
        if (visitable instanceof BannerModel) {
            return TYPE_RESOURCE_ONE;
        } else if (visitable instanceof HomeListModel) {
            return TYPE_RESOURCE_TWO;
        } else {
            return 0;
        }
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        if (type == TYPE_RESOURCE_ONE) {
            return new BannerViewHolder(itemView);
        } else if (type == TYPE_RESOURCE_TWO) {
            return new HomeListViewHolder(itemView);
        }
        return null;
    }
}
