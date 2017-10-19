package com.example.ldk.mvpdemo.data;


import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.http.api.DownLoadApi;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.CeShi;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.data.entity.ResultA;
import com.example.ldk.mvpdemo.presenter.contract.LoginContract;



import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;
    private DownLoadApi downLoadApi;

    public LoginModel(ApiService apiService,DownLoadApi downLoadApi) {

        this.mApiService = apiService;
        this.downLoadApi=downLoadApi;
    }

    @Override
    public Call<String> login() {
        return mApiService.login();
    }

    @Override
    public Observable<ResultA> login2() {
        return mApiService.login2();
    }

    @Override
    public Observable<BaseEntity<CeShi>> login3() {
        return mApiService.login3();
    }

    @Override
    public Observable<BaseEntity<String>> uploadFile(MultipartBody multipartBody) {
        return mApiService.uploadFile(multipartBody);
    }

    @Override
    public Observable<BaseEntity<DataBean>> SuploadFile(MultipartBody multipartBody) {
        return mApiService.SuploadFile(multipartBody);
    }

    @Override
    public Call<BaseEntity<String>> uploadFileCall(MultipartBody multipartBody) {
        return mApiService.uploadFileCall(multipartBody);
    }

    @Override
    public Observable<ResponseBody> DownLoad(String url) {
        return downLoadApi.DownLoadFile(url);
    }

//    @Override
//    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
//
//        LoginRequestBean param = new LoginRequestBean();
//        param.setEmail(phone);
//        param.setPassword(pwd);
//
//        return mApiService.login(param);
//    }
}
