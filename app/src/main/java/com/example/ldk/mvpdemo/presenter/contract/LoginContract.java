package com.example.ldk.mvpdemo.presenter.contract;

import com.example.ldk.mvpdemo.common.base.BaseView;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.CeShi;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.data.entity.ResultA;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public interface LoginContract {
    public interface ILoginModel {
//        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
        Call<String> login();
        Observable<ResultA> login2();
        Observable<BaseEntity<CeShi>> login3();
        Observable<BaseEntity<String>> uploadFile(MultipartBody multipartBody);
        Observable<BaseEntity<DataBean>> SuploadFile(MultipartBody multipartBody);
        Call<BaseEntity<String>> uploadFileCall(MultipartBody multipartBody);
        Observable<ResponseBody> DownLoad(String url);
    }


    public interface LoginView extends BaseView {
        void loginSuccess(String s);
    }
}
