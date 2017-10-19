package com.example.ldk.mvpdemo.di.component;


import com.example.ldk.mvpdemo.di.FragmentScope;
import com.example.ldk.mvpdemo.di.module.LoginModule;
import com.example.ldk.mvpdemo.ui.activity.LoginActivity;

import dagger.Component;
import dagger.Subcomponent;

@FragmentScope
//很重要！这个Component应该是AppComponent的子Component，所以要使用这个注解
//不使用@Component注解的Dependents属性是因为希望能统一管理子Component
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
