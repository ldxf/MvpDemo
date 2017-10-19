package com.example.ldk.mvpdemo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.base.BaseActivity;
import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.utils.RouterUrl;
import com.example.ldk.mvpdemo.di.component.AppComponent;
import com.example.ldk.mvpdemo.di.module.LoginModule;
import com.example.ldk.mvpdemo.presenter.LoginPresenter;
import com.example.ldk.mvpdemo.presenter.contract.LoginContract;
import com.github.mzule.activityrouter.router.Routers;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.sign)
    Button btn;
    @BindView(R.id.img)
    ImageView img;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        appComponent.addSub(new LoginModule(this)).inject(this);
//        DaggerLoginComponent.builder().appComponent(appComponent).loginModule(new LoginModule(this)).build().inject(this);
    }

//    @OnClick(value = R.id.sign)
//    public void btn(View v) {
//        switch (v.getId()) {
//            case R.id.sign:
//                mPresenter.login(email.getText().toString(), password.getText().toString());
//                break;
//        }
//    }

    @Override
    public void init() {
        //rxbinding的使用
        RxView.clicks(btn)
                .throttleFirst(2, TimeUnit.SECONDS)//两秒钟之内只取一个点击事件，防抖操作
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mPresenter.login(email.getText().toString(), password.getText().toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
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

    @Override
    public void loginSuccess(String url) {
        Glide.with(this).load(ApiService.BASE_URL + url).into(img);
//        img.setImageBitmap(BitmapFactory.decodeFile(s));
//        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
//        Log.e(TAG, "loginSuccess: "+s);
////        startActivity(new Intent(this, MainActivity.class));
        Routers.open(this, RouterUrl.getRouterUrl(RouterUrl.AppCommon_IMain));
    }


}

