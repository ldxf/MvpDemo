package com.example.ldk.mvpdemo.di.module;

import android.content.Context;

import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.data.HomeModel;
import com.example.ldk.mvpdemo.presenter.HomePresenter;
import com.example.ldk.mvpdemo.presenter.contract.fragment.HomeContrat;
import com.example.ldk.mvpdemo.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ldk on 2017/10/16.
 */

@Module
public class HomeModule {
    private HomeContrat.HomeView mView;

    public HomeModule(HomeContrat.HomeView mView) {
        this.mView = mView;
    }

    @Provides
    public HomeContrat.HomeView provideView() {
        return mView;
    }

    @Provides
    public HomeContrat.IHomeModel privodeModel(ApiService apiService) {
        return new HomeModel(apiService);
    }
    @Provides
    public HomePresenter privodeHomePresenter(HomeContrat.IHomeModel iHomeModel, HomeContrat.HomeView homeView) {
        return new HomePresenter(iHomeModel,homeView);
    }



}
