package com.example.ldk.mvpdemo;

import android.app.Application;
import android.content.Context;

import com.example.ldk.mvpdemo.di.component.AppComponent;
import com.example.ldk.mvpdemo.di.component.DaggerAppComponent;
import com.example.ldk.mvpdemo.di.module.AppModule;
import com.example.ldk.mvpdemo.di.module.HttpModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ldk on 2017/10/12.
 */

public class AppApplication extends Application {

    private static AppComponent mAppComponent;


    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    public static AppComponent getAppComponent() {

        return mAppComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
