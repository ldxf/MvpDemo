package com.example.ldk.mvpdemo.data;


import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.CeShi;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.data.entity.ResultA;
import com.example.ldk.mvpdemo.presenter.contract.LoginContract;
import com.example.ldk.mvpdemo.presenter.contract.MainContract;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;

public class MainModel implements MainContract.IMainModel {

    private ApiService mApiService;

    public MainModel(ApiService apiService){
        this.mApiService = apiService;
    }

    @Override
    public Call<String> getdata() {
        return null;
    }
}
