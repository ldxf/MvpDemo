package com.example.ldk.mvpdemo.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.base.BaseFragment;
import com.example.ldk.mvpdemo.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by ldk on 2017/10/16.
 */

@SuppressLint("ValidFragment")
public class TwoFragment extends BaseFragment {
    @BindView(R.id.frag_recycleview)
    RecyclerView fragRecycleview;

    private Context mContext;

    @SuppressLint("StaticFieldLeak")
    private static TwoFragment homeFragment;

    public static TwoFragment newInstance(Context context) {

        if (homeFragment == null) {
            homeFragment = new TwoFragment(context);
        }
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    public TwoFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
//        DaggerTwoFragComponent.builder().appComponent(appComponent).twoFragModule(new TwoFragModule(this)).build().inject(this);
    }

    @Override
    public void init() {
    }


}
