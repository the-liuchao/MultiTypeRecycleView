package com.example.liuchao.multitype.api;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by yangrenije on 2017/7/7.
 */

public class ServerAPI {

    private ServerAPI() {
    }

    private final static class Holder {
        private final static ServerAPI INSTANCE = new ServerAPI();
    }

    public ServerAPI getInstance() {
        return Holder.INSTANCE;
    }

    public interface UploadService {
        /**
         * 上传一张图片
         *
         * @param
         * @return
         */
        @Multipart
//        @POST("upload/image")
        @POST("web/fileUpload/picUpload")
        Call<String> uploadImage(@Part List<MultipartBody.Part> parts);

    }
}
