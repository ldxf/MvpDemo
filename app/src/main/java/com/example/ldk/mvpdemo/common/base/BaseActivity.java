package com.example.ldk.mvpdemo.common.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.example.ldk.mvpdemo.AppApplication;
import com.example.ldk.mvpdemo.di.component.AppComponent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity {

    private Unbinder mUnbinder;

    protected String TAG = this.getClass().getName();
    @Inject
    protected T mPresenter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        mUnbinder = ButterKnife.bind(this);

        setupAcitivtyComponent(AppApplication.getAppComponent());

        init();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mUnbinder !=Unbinder.EMPTY){

            mUnbinder.unbind();
        }
    }

//    protected  void  startActivity(Class c){
//
//        this.startActivity(new Intent(this,c));
//    }
//
//


    public abstract int setLayout();

    public abstract  void setupAcitivtyComponent(AppComponent appComponent);


    public abstract void  init();

}
