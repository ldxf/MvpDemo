package com.example.ldk.mvpdemo.ui.activity;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.base.BaseActivity;
import com.example.ldk.mvpdemo.common.base.BaseFragment;
import com.example.ldk.mvpdemo.common.base.BasePresenter;
import com.example.ldk.mvpdemo.common.utils.RouterUrl;
import com.example.ldk.mvpdemo.di.component.AppComponent;
import com.example.ldk.mvpdemo.di.module.MainModule;
import com.example.ldk.mvpdemo.presenter.MainPresenter;
import com.example.ldk.mvpdemo.presenter.contract.MainContract;
import com.example.ldk.mvpdemo.ui.fragment.HomeFragment;
import com.example.ldk.mvpdemo.ui.fragment.ThreeFragment;
import com.example.ldk.mvpdemo.ui.fragment.TwoFragment;
import com.github.mzule.activityrouter.annotation.Router;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import butterknife.BindView;

@Router(RouterUrl.AppCommon_IMain)
public class MainActivity extends BaseActivity<MainPresenter> implements BottomNavigationView.OnNavigationItemSelectedListener, MainContract.MainView {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private FragmentManager manager;

    @Inject
    HomeFragment homeFragment;
    @Inject
    TwoFragment twoFragment;
    @Inject
    ThreeFragment threeFragment;
    List<BaseFragment> fragmentList;


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        appComponent.addSub(new MainModule(this)).inject(this);
    }

    @Override
    public void init() {
        navigation.setOnNavigationItemSelectedListener(this);
        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(twoFragment);
        manager = getSupportFragmentManager();
        initFragment(homeFragment);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                ConsoleFragment(homeFragment);
                return true;
            case R.id.navigation_dashboard:
                ConsoleFragment(twoFragment);
                return true;
            case R.id.navigation_notifications:
                ConsoleFragment(threeFragment);
                return true;
        }
        return false;
    }

    private void initFragment(BaseFragment b) {
        for (BaseFragment baseFragment : fragmentList) {
            manager.beginTransaction().add(R.id.content_fragment, baseFragment).hide(baseFragment).commit();
        }
        manager.beginTransaction().show(b).commit();
    }

    private void ConsoleFragment(BaseFragment fragment) {
        manager.beginTransaction().hide(homeFragment).hide(twoFragment).hide(threeFragment).show(fragment).commit();
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
    public void loading(String s) {

    }
}
