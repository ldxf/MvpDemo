package com.example.ldk.mvpdemo.di.component;

import android.app.Application;

import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.di.module.AppModule;
import com.example.ldk.mvpdemo.di.module.HomeModule;
import com.example.ldk.mvpdemo.di.module.HttpModule;
import com.example.ldk.mvpdemo.di.module.LoginModule;
import com.example.ldk.mvpdemo.di.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

     ApiService getApiService();

     Application getApplication();
     //SubComponent 继承当前Component
     LoginComponent addSub(LoginModule loginModule);

     MainComponent addSub(MainModule mainModule);

     HomeComponent addSub(HomeModule homeModule);

}
