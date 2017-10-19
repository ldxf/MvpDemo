package com.example.ldk.mvpdemo.di.component;


import com.example.ldk.mvpdemo.di.FragmentScope;
import com.example.ldk.mvpdemo.di.module.HomeModule;
import com.example.ldk.mvpdemo.ui.fragment.HomeFragment;

import dagger.Subcomponent;

/**
 * Created by ldk on 2017/10/16.
 */

@FragmentScope
@Subcomponent (modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}
