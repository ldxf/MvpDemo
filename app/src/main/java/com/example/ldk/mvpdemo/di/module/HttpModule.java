package com.example.ldk.mvpdemo.di.module;

import android.app.Application;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ldk.mvpdemo.BuildConfig;
import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.http.api.DownLoadApi;
import com.example.ldk.mvpdemo.common.http.call.ProgressResponseBody;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class HttpModule {

    @Provides
    @Singleton
    @Named("ApiService")
    public OkHttpClient provideOkHttpClient(Application application, Gson gson) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // log用拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("-okHttp-", message);
                }
            });
            // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        // 连接超时时间设置
        builder.connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        return builder.build();

    }

    @Provides
    @Singleton
    @Named("DownLoadApi")
    public OkHttpClient provideOkHttpDown() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Response orginalResponse = chain.proceed(chain.request());
                        return chain.proceed(chain.request()).newBuilder().body(new ProgressResponseBody(orginalResponse.body(), new ProgressResponseBody.ProgressListener() {
                            @Override
                            public void onProgress(long progress, long total, boolean done) {
                                Log.e("onProgress", "onProgress: " + "total ---->" + total + "done ---->" + progress);
                            }
                        })).build();
                    }
                });

        if (BuildConfig.DEBUG) {
            // log用拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("-okHttp-", message);
                }
            });
            // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        // 连接超时时间设置
        builder.connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        return builder.build();

    }

    @Provides
    @Singleton
    @Named("DownLoadApi")
    public Retrofit provideDownRetrofit(@Named("DownLoadApi") OkHttpClient okHttpClient) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();

    }

    @Provides
    @Singleton
    @Named("ApiService")
    public Retrofit provideRetrofit(@Named("ApiService") OkHttpClient okHttpClient) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();

    }


    @Provides
    @Singleton
    public ApiService provideApiService(@Named("ApiService") Retrofit retrofit) {

        return retrofit.create(ApiService.class);

    }

    @Provides
    @Singleton
    public DownLoadApi provideDownLoadApi(@Named("DownLoadApi") Retrofit retrofit) {

        return retrofit.create(DownLoadApi.class);

    }
}
