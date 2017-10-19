package com.example.ldk.mvpdemo.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.base.BaseFragment;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;
import com.example.ldk.mvpdemo.di.component.AppComponent;
import com.example.ldk.mvpdemo.di.module.HomeModule;
import com.example.ldk.mvpdemo.presenter.HomePresenter;
import com.example.ldk.mvpdemo.presenter.contract.fragment.HomeContrat;
import com.example.ldk.mvpdemo.ui.adapter.MultipleItemQuickAdapter;
import com.example.ldk.mvpdemo.ui.adapter.bean.MultipleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ldk on 2017/10/16.
 */

@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContrat.HomeView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.frag_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private Context mContext;
//    private List<HomeFragBean.DataBean> data;
//    private MyAdapter myAdapter;


    private MultipleItemQuickAdapter adapter;
    private List<MultipleItem> items;

    private int pager = 0;
    private int size = 5;
    @SuppressLint("StaticFieldLeak")
    private static HomeFragment homeFragment;

    public static HomeFragment newInstance(Context context) {

        if (homeFragment == null) {
            homeFragment = new HomeFragment(context);
        }
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    public HomeFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        appComponent.addSub(new HomeModule(this)).inject(this);
    }

    @Override
    public void init() {
//        data = new ArrayList<>();
        items = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        myAdapter = new MyAdapter(data);
//        // 默认提供5种方法（渐显、缩放、从下到上，从左到右、从右到左）
//        myAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//        myAdapter.setDuration(500);//设置动画时间
//        myAdapter.setOnLoadMoreListener(this, mRecyclerView);
        adapter = new MultipleItemQuickAdapter(items);
        // 默认提供5种方法（渐显、缩放、从下到上，从左到右、从右到左）
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.setDuration(500);//设置动画时间
        adapter.setOnLoadMoreListener(this, mRecyclerView);
        refreshLayout.setOnRefreshListener(this);
        //设置刷新时动画的颜色，可以设置4个
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mRecyclerView.setAdapter(adapter);
        mPresenter.getData(pager, size);
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);//去除上拉加载过程中，可以触发下拉刷新事件
        mPresenter.getData(pager, size);
    }

    @Override
    public void onRefresh() {
        pager = 0;
//        data.clear();
        items.clear();
//        myAdapter.notifyDataSetChanged();
//        myAdapter.setEnableLoadMore(false);//去除下拉刷新过程中，可以触发上拉加载事件
        adapter.notifyDataSetChanged();
        adapter.setEnableLoadMore(false);//去除下拉刷新过程中，可以触发上拉加载事件
        mPresenter.getData(pager, size);

    }





    @Override
    public void load(HomeFragBean homeFragBean) {
//       WebView webView= new WebView(mContext);
//       webView.loadUrl("http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0417/4154.html");
//        myAdapter.setEmptyView(webView);
        if (pager == 0) {
//            data.addAll(homeFragBean.getData());
            if (homeFragBean.getIocn() != null && homeFragBean.getIocn().size()>0) {
                MultipleItem multipleItemTop = new MultipleItem(MultipleItem.ICON);
                multipleItemTop.setIconTops(homeFragBean.getIocn());
                items.add(multipleItemTop);
            }
            for (HomeFragBean.DataBean dataBean : homeFragBean.getData()) {
                MultipleItem multipleItemContent = new MultipleItem(MultipleItem.CONTENT);
                multipleItemContent.setDataBean(dataBean);
                items.add(multipleItemContent);
            }
            adapter.notifyDataSetChanged();
            if (refreshLayout.isRefreshing()) {
                refreshLayout.setRefreshing(false);
                adapter.loadMoreEnd(false);
                adapter.setEnableLoadMore(true);
            }
        } else {
            if (pager * size >= 50) {
                adapter.loadMoreEnd(true);
            } else {
                // reqData
                items.clear();
                if (homeFragBean.getIocn() != null && homeFragBean.getIocn().size()>0) {
                    MultipleItem multipleItemTop = new MultipleItem(MultipleItem.ICON);
                    multipleItemTop.setIconTops(homeFragBean.getIocn());
                    items.add(multipleItemTop);
                }
                for (HomeFragBean.DataBean dataBean : homeFragBean.getData()) {
                    MultipleItem multipleItemContent = new MultipleItem(MultipleItem.CONTENT);
                    multipleItemContent.setDataBean(dataBean);
                    items.add(multipleItemContent);
                }
//                data.clear();
//                data.addAll(homeFragBean.getData());
                adapter.notifyDataSetChanged();
                adapter.loadMoreComplete();
            }
            refreshLayout.setEnabled(true);
        }
        pager++;

    }


    @Override
    public void emptyData() {
        adapter.setEmptyView(R.layout.item_emptylayout);
        adapter.setEnableLoadMore(false);
        refreshLayout.setEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(adapter.getBanner()!=null){
            adapter.getBanner().stopAutoPlay();//结束轮播
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter.getBanner()!=null){
            adapter.getBanner().startAutoPlay();//开始轮播
        }
    }
}
