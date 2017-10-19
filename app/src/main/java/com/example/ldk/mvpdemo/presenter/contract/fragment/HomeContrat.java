package com.example.ldk.mvpdemo.presenter.contract.fragment;

import com.example.ldk.mvpdemo.common.base.BaseView;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;

import io.reactivex.Observable;

/**
 * Created by ldk on 2017/10/16.
 */

public interface HomeContrat {
    public interface IHomeModel {
        Observable<BaseEntity<HomeFragBean>> getHomeData(int page, int size);
    }


    public interface HomeView extends BaseView {
        void load(HomeFragBean homeFragBean);
        void emptyData();
    }
}
