package com.example.liuchao.multitype.holder;

import android.view.View;

import com.allen.library.SuperTextView;
import com.example.liuchao.multitype.R;
import com.example.liuchao.multitype.adapter.MultiTypeAdapter;
import com.example.liuchao.multitype.model.BannerModel;
import com.example.liuchao.multitype.model.HomeListModel;

/**
 * Created by yangrenije on 2017/6/14.
 */

public class HomeListViewHolder extends BaseViewHolder<HomeListModel> {
    private SuperTextView mTextView;

    public HomeListViewHolder(View itemView) {
        super(itemView);
        mTextView = (SuperTextView) itemView.findViewById(R.id.super_tv);
    }

    @Override
    public void bindData(HomeListModel model, int position, MultiTypeAdapter adapter) {
        mTextView.setLeftTopString(model.username);
        mTextView.setRightTopString(model.occupation);
        mTextView.setRightBottomString(model.birthday);
    }


}
