package com.example.ldk.mvpdemo.di.module;

import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.http.api.DownLoadApi;
import com.example.ldk.mvpdemo.data.LoginModel;
import com.example.ldk.mvpdemo.presenter.LoginPresenter;
import com.example.ldk.mvpdemo.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view) {
        this.mView = view;
    }

    @Provides
    public LoginContract.LoginView provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel privodeModel(ApiService apiService, DownLoadApi downLoadApi) {

        return new LoginModel(apiService,downLoadApi);
    }

    @Provides
    public LoginPresenter privodeLoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {

        return new LoginPresenter(iLoginModel,loginView);
    }

}
