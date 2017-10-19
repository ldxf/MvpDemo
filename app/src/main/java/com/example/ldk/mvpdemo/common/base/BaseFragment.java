package com.example.ldk.mvpdemo.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ldk.mvpdemo.AppApplication;
import com.example.ldk.mvpdemo.di.component.AppComponent;
import com.example.ldk.mvpdemo.ui.fragment.HomeFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements BaseView {

    protected String TAG = "BaseFragment";
    private Unbinder mUnbinder;
    private View mRootView;

    @Inject
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupAcitivtyComponent(AppApplication.getAppComponent());

        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void dismissLoading() {
    }

    public abstract int setLayout();

    public abstract void setupAcitivtyComponent(AppComponent appComponent);


    public abstract void init();


}
