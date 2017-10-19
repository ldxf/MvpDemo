package com.example.ldk.mvpdemo.presenter;

import com.example.ldk.mvpdemo.common.base.BaseObserver;
import com.example.ldk.mvpdemo.common.base.BasePresenter;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;
import com.example.ldk.mvpdemo.presenter.contract.fragment.HomeContrat;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

/**
 * Created by ldk on 2017/10/16.
 */

public class HomePresenter extends BasePresenter<HomeContrat.IHomeModel, HomeContrat.HomeView> {

    @Inject
    public HomePresenter(HomeContrat.IHomeModel iHomeModel, HomeContrat.HomeView homeView) {
        super(iHomeModel, homeView);
    }

    public void getData(int page, int size) {
        mModel.getHomeData(page, size)
                .compose(this.<BaseEntity<HomeFragBean>>setFragmentRxLifecycle(FragmentEvent.DESTROY, false))
                .compose(this.<BaseEntity<HomeFragBean>>setThread())
                .subscribe(new BaseObserver<HomeFragBean>() {
                    @Override
                    protected void onSuccees(BaseEntity<HomeFragBean> databean) throws Exception {
                        if(databean.getData().getData().isEmpty() && databean.getData().getIocn().isEmpty()){
                            mView.emptyData();
                        }else {
                            mView.load(databean.getData());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        mView.emptyData();
                    }
                });
    }
}
