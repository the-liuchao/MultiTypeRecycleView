package com.example.liuchao.multitype.persenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.liuchao.multitype.api.ServerAPI;
import com.example.liuchao.multitype.api.StringConvertFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by yangrenije on 2017/7/7.
 */

public class UploadPresenter {

    private Retrofit mRetrofit;

    public void upload(String path) {
        if (TextUtils.isEmpty(path)) return;
        File file = new File(path);
        if (!file.exists()) return;
        //1.创建Retrofit对象
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://47.89.18.26:8292/") // 定义访问的主机地址
                .addConverterFactory(StringConvertFactory.create())
                .build();
        ServerAPI.UploadService service = mRetrofit.create(ServerAPI.UploadService.class);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型
                .addFormDataPart("image", new File(path).getName());
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(path));
        builder.addFormDataPart("uploadFiles", new File(path).getName(), imageBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        Call<String> call = service.uploadImage(parts);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("print", "response:" + response.message());
                Log.e("print", "response:" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("print", "failure:" + t.getMessage());
            }
        });
    }
}
