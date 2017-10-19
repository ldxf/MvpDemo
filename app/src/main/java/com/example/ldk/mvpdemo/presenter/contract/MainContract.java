package com.example.ldk.mvpdemo.presenter.contract;


import com.example.ldk.mvpdemo.common.base.BaseView;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.CeShi;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.data.entity.ResultA;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;

public interface MainContract {
    public interface IMainModel {
        Call<String> getdata();
    }


    public interface MainView extends BaseView {
        void loading(String s);
    }
}
