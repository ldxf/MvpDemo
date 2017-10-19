package com.example.ldk.mvpdemo.di.module;

import android.content.Context;

import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.data.MainModel;
import com.example.ldk.mvpdemo.presenter.MainPresenter;
import com.example.ldk.mvpdemo.presenter.contract.MainContract;
import com.example.ldk.mvpdemo.ui.fragment.HomeFragment;
import com.example.ldk.mvpdemo.ui.fragment.ThreeFragment;
import com.example.ldk.mvpdemo.ui.fragment.TwoFragment;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ldk on 2017/10/17.
 */

@Module
public class MainModule {
    private MainContract.MainView mView;

    public MainModule(MainContract.MainView view) {
        this.mView = view;
    }

    @Provides
    public MainContract.MainView provideView() {
        return mView;
    }

    @Provides
    public Context provideContext() {
        return (Context) mView;
    }

    @Provides
    public MainContract.IMainModel privodeModel(ApiService apiService) {

        return new MainModel(apiService);
    }

    @Provides
    public MainPresenter privodeMainPresenter(MainContract.IMainModel iMainModel, MainContract.MainView mainView) {

        return new MainPresenter(iMainModel, mainView);
    }

    @Provides
    public HomeFragment privodeHomeFragment(Context mContext) {

        return new HomeFragment(mContext);
    }

    @Provides
    public TwoFragment privodeTwoFragment(Context mContext) {

        return new TwoFragment(mContext);
    }
    @Provides
    public ThreeFragment privodeThreeFragment(Context mContext) {

        return new ThreeFragment(mContext);
    }
}
