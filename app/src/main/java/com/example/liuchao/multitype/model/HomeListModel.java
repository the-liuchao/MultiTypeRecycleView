package com.example.liuchao.multitype.model;

import com.example.liuchao.multitype.factory.TypeFactory;

/**
 * Created by yangrenije on 2017/9/12.
 */

public class HomeListModel implements Visitable {
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    public String username;
    public String birthday;
    public String telnumber;
    public String occupation;
}
