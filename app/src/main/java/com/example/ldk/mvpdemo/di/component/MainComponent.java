package com.example.ldk.mvpdemo.di.component;

import com.example.ldk.mvpdemo.di.FragmentScope;
import com.example.ldk.mvpdemo.di.module.MainModule;
import com.example.ldk.mvpdemo.ui.activity.MainActivity;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

     void inject(MainActivity activity);

}
