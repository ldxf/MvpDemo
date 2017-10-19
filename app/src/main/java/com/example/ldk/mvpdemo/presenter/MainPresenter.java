package com.example.ldk.mvpdemo.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.base.BaseObserver;
import com.example.ldk.mvpdemo.common.base.BasePresenter;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.common.utils.FileUtils;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.presenter.contract.LoginContract;
import com.example.ldk.mvpdemo.presenter.contract.MainContract;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainPresenter extends BasePresenter<MainContract.IMainModel, MainContract.MainView> {


    @Inject
    public MainPresenter(MainContract.IMainModel iMainModel, MainContract.MainView mainView) {
        super(iMainModel, mainView);
    }

    public void login(String phone, String pwd) {
    }


}
