package com.example.ldk.mvpdemo.data;

import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;
import com.example.ldk.mvpdemo.presenter.contract.fragment.HomeContrat;

import io.reactivex.Observable;

/**
 * Created by ldk on 2017/10/16.
 */

public class HomeModel implements HomeContrat.IHomeModel{
    private ApiService mApiService;

    public HomeModel(ApiService apiService){
        this.mApiService = apiService;
    }

    public Observable<BaseEntity<HomeFragBean>> getHomeData(int page, int size) {
        return mApiService.requestData(page,size);
    }

}
