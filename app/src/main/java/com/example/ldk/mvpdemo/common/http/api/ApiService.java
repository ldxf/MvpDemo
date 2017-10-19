package com.example.ldk.mvpdemo.common.http.api;


import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.CeShi;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;
import com.example.ldk.mvpdemo.data.entity.ResultA;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiService {

    public static final String BASE_URL = "http://10.7.7.37:8080/test/";

//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);
    @POST("Login")
    Call<String> login();

    @POST("Login")
    Observable<ResultA> login2();

    @POST("Login")
    Observable<BaseEntity<CeShi>> login3();

//    @Multipart
    @POST("UploadFile")
    Observable<BaseEntity<String>> uploadFile(@Body MultipartBody multipartBody);

    @POST("UploadFile")
    Call<BaseEntity<String>> uploadFileCall(@Body MultipartBody multipartBody);

    @POST("UploadFile")
    Observable<BaseEntity<DataBean>> SuploadFile(@Body MultipartBody multipartBody);

    @FormUrlEncoded
    @POST("HomeFragment")
    Observable<BaseEntity<HomeFragBean>> requestData(@Field("page") int page,@Field("size") int size);
//    @GET("featured2")
//    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);


}
