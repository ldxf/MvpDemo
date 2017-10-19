package com.example.ldk.mvpdemo.common.base;

import android.accounts.NetworkErrorException;
import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ldk on 2017/10/13.
 */

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    private Context mContext;

    private ProgressDialog dialog;
    private static final String TAG = "BaseObserver";

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        onRequestEnd();
        if (tBaseEntity.isSuccessed()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                onCodeError(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
//        Log.w(TAG, "onError: "+e.toString());//这里可以打印错误信息
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseEntity<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseEntity<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
        if(mContext!=null)
        showProgressDialog();
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
        dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.show();
//        ProgressDialog.show(mContext, false, "请稍后");
    }

    public void closeProgressDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog.cancel();
            dialog = null;
        }
//        ProgressDialog.cancle();
    }

}
