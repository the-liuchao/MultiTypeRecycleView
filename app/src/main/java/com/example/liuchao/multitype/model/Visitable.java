package com.example.liuchao.multitype.model;

import com.example.liuchao.multitype.factory.TypeFactory;

/**
 * Created by yangrenije on 2017/6/14.
 */

public interface Visitable {
    int type(TypeFactory typeFactory);
}
