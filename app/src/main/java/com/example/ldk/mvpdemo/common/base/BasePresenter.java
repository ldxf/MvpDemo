package com.example.ldk.mvpdemo.common.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;

public class BasePresenter<M, V extends BaseView> {
    protected M mModel;
    protected V mView;
    protected Context mContext;

    protected static final MediaType MediaType_MTEXT = MediaType.parse("text/plain; charset=utf-8");
    protected static final MediaType MediaType_STREAM = MediaType.parse("application/octet-stream");
    protected static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");
    protected static final MediaType MediaType_IMAGE = MediaType.parse("image/png; charset=utf-8");
    protected static final MediaType MediaType_MULTIPART =MediaType.parse("multipart/form-data");

    public BasePresenter(M m, V v) {
        this.mModel = m;
        this.mView = v;
        initContext();
    }

    private void initContext() {
        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }

    /***
     * 将线程切换提成一个方法
     * @param <T>
     * @return
     */
    protected <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /***
     * Activity
     * rxjava生命周期绑定，防止内存泄漏
     * @param <T> b是否指定，取消绑定在哪个生命周期
     * @return
     */
    protected <T> LifecycleTransformer<T> setActivityRxLifecycle(ActivityEvent event, boolean b) {
        if (mView instanceof BaseActivity) {
            BaseActivity act=(BaseActivity) mView;
            if (b) {
                return act.bindUntilEvent(event);
            } else {
                return act.bindToLifecycle();
            }
        } else {
            return ((BaseFragment) mView).bindToLifecycle();
        }
    }
    /***
     * Fragment
     * rxjava生命周期绑定，防止内存泄漏
     * @param <T> b是否指定，取消绑定在哪个生命周期
     * @return
     */
    protected <T> LifecycleTransformer<T> setFragmentRxLifecycle(FragmentEvent event, boolean b) {
        if (mView instanceof BaseFragment) {
            BaseFragment frag=(BaseFragment) mView;
            if (b) {
                return frag.bindUntilEvent(event);
            } else {
                return frag.bindToLifecycle();
            }
        } else {
            return ((BaseActivity) mView).bindToLifecycle();
        }
    }


    private static ProgressDialog dialog;

    protected static void ShowProDialog(Context m, long max, long index, String msg) {
        if (dialog == null) {
            dialog = new ProgressDialog(m);
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
        dialog.setProgress((int) index);
        dialog.setMax((int) max);
        dialog.setMessage(msg);
        dialog.show();
    }

    protected static void DisProDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog.cancel();
        }
        dialog = null;
    }


}
