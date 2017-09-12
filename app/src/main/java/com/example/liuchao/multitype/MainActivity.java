package com.example.liuchao.multitype;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.liuchao.multitype.adapter.MultiTypeAdapter;
import com.example.liuchao.multitype.factory.HomeTypeFactory;
import com.example.liuchao.multitype.model.BannerModel;
import com.example.liuchao.multitype.model.HomeListModel;
import com.example.liuchao.multitype.model.Visitable;
import com.example.liuchao.multitype.persenter.UploadPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    private List<Visitable> mModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mModels = new ArrayList<>();
        //=========================test data ===========================
        BannerModel bannerModel = new BannerModel();
        List<String> bannerPaths = new ArrayList<>();
        bannerPaths.add("http://.....");
        bannerModel.mPath = bannerPaths;
        mModels.add(bannerModel);

        HomeListModel model = new HomeListModel();
        model.username = "张三";
        model.birthday = "10月20日";
        model.occupation = "经理";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "李四";
        model.birthday = "02月22日";
        model.occupation = "副经理";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "王五";
        model.birthday = "09月01日";
        model.occupation = "副经理";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "赵六";
        model.birthday = "06月16日";
        model.occupation = "移动";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "田七";
        model.birthday = "09月28日";
        model.occupation = "前端";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "宋八";
        model.birthday = "05月08日";
        model.occupation = "后台";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "赵九";
        model.birthday = "07月09日";
        model.occupation = "前端";
        mModels.add(model);
        model = new HomeListModel();
        model.username = "吴十";
        model.birthday = "07月20日";
        model.occupation = "后台";
        mModels.add(model);
        //=========================test data ===========================
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MultiTypeAdapter(mModels, new HomeTypeFactory());
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mAdapter);
    }
}
