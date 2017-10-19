package com.example.ldk.mvpdemo.common.http.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownLoadApi {

    public static final String BASE_URL = "http://10.7.7.37:8080/test/";
    @POST
    @Streaming
    Observable<ResponseBody> DownLoadFile(@Url String url);

}
