package com.example.liuchao.multitype.model;

import com.example.liuchao.multitype.factory.TypeFactory;

import java.util.List;

/**
 * Created by yangrenije on 2017/6/14.
 */

public class BannerModel implements Visitable {
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    public List<String> mPath;
}
