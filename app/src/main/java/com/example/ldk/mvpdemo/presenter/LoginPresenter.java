package com.example.ldk.mvpdemo.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.base.BaseObserver;
import com.example.ldk.mvpdemo.common.base.BasePresenter;
import com.example.ldk.mvpdemo.common.http.entity.BaseEntity;
import com.example.ldk.mvpdemo.common.utils.FileUtils;
import com.example.ldk.mvpdemo.data.entity.DataBean;
import com.example.ldk.mvpdemo.presenter.contract.LoginContract;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static android.content.ContentValues.TAG;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {


    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }

    public void login(String phone, String pwd) {


        Log.d("LoginPresenter", "phone=" + phone);
        Log.d("LoginPresenter", "pwd=" + pwd);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher_round);
        List<File> files = new ArrayList<>();
        Map<String, String> maps = new HashMap<>();
        for (int i = 0; i < 1; i++) {
            File file = FileUtils.saveFileToSDCard(bitmap);
            files.add(file);
            maps.put("text" + i, "json" + i);
        }

        mModel.SuploadFile(filesToMultipartBody(files, maps))
                .compose(this.<BaseEntity<DataBean>>setActivityRxLifecycle(ActivityEvent.DESTROY,false))
                .compose(this.<BaseEntity<DataBean>>setThread())
                .subscribe(new BaseObserver<DataBean>() {
                    @Override
                    protected void onSuccees(BaseEntity<DataBean> t) throws Exception {
                        mView.loginSuccess(t.getData().getFiles().get(0).getPath());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        mView.loginSuccess(e.toString());
                    }
                });

//        mModel.DownLoad("http://10.7.7.37:8080/test/apk/patch.patch").compose(this.<ResponseBody>setThread())
//                .compose(this.<ResponseBody>setActivityRxLifecycle(ActivityEvent.DESTROY,false))
//                .subscribe(new Observer<ResponseBody>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody response) {
//                        try {
//                            InputStream is = response.byteStream();
//                            File file = new File(Environment.getExternalStorageDirectory(), "patch.patch");
//                            FileOutputStream fos = new FileOutputStream(file);
//                            BufferedInputStream bis = new BufferedInputStream(is);
//                            byte[] buffer = new byte[1024];
//                            int len;
//                            while ((len = bis.read(buffer)) != -1) {
//                                fos.write(buffer, 0, len);
//                                fos.flush();
//                            }
//                            fos.close();
//                            bis.close();
//                            is.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Log.e(TAG,"success");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        mModel.uploadFile(filesToMultipartBody(files, maps)).compose(this.<BaseEntity<String>>setThread())
//                .subscribe(new BaseObserver<String>() {
//                    @Override
//                    protected void onSuccees(BaseEntity<String> t) throws Exception {
//                        mView.loginSuccess(t.toString() + "");
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        mView.loginSuccess(e.toString() + "");
//                    }
//                });

//        //多文件上传
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher_round);
//        List<File> files=new ArrayList<>();
//        for(int i=0;i<5;i++){
//            File file = FileUtils.saveFileToSDCard(bitmap);
//            files.add(file);
//        }
//        mModel.uploadFileCall(filesToMultipartBody(files,maps)).enqueue(new Callback<BaseEntity<String>>() {
//            @Override
//            public void onResponse(Call<BaseEntity<String>> call, Response<BaseEntity<String>> response) {
//                mView.loginSuccess(response.body().toString()+"");
//            }
//
//            @Override
//            public void onFailure(Call<BaseEntity<String>> call, Throwable t) {
//                mView.loginSuccess(t.toString()+"");
//            }
//        });


        //retrofit
//        mModel.login().enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                mView.loginSuccess(response.body().toString()+"");
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                mView.loginSuccess(""+t.toString());
//            }
//        });


//        rxjava 中的封装
//        mModel.login3().compose(this.<BaseEntity<CeShi>>setThread())
//                .subscribe(new BaseObserver<CeShi>(mContext) {
//
//                    @Override
//                    protected void onSuccees(BaseEntity<CeShi> t) throws Exception {
//                        mView.loginSuccess(t.getData().getM() + "");
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        mView.loginSuccess(e.toString() + "");
//                    }
//                });

        //rxjava
//        mModel.login2()
//                .compose(this.<ResultA>setThread())
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResultA>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResultA resultA) {
//                        mView.loginSuccess(resultA.getData()+"");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        mView.loginSuccess();
//                    }
//
//                    @Override
//                    public void onComplete() {
////                        mView.loginSuccess();
//                    }
//                });
    }

    public static MultipartBody filesToMultipartBody(final List<File> files, Map<String, String> maps) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (final File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType_IMAGE, file);
//            ProgressRequestBody body = new ProgressRequestBody(requestBody, new ProgressRequestBody.UploadProgressListener() {
//                @Override
//                public void onProgress(long currentBytesCount, long totalBytesCount) {
////                    if(currentBytesCount>=totalBytesCount){
////                        DisProDialog();
////                    }else {
//                        ShowProDialog(mContext,totalBytesCount,currentBytesCount,currentBytesCount+"/"+totalBytesCount);
////                    }
//                    Log.e("TAG", (file.getName()) + "onProgress: " + currentBytesCount + ";-----" + totalBytesCount);
//                }
//            });

            builder.addFormDataPart("File", file.getName(), requestBody);
        }
        for (Map.Entry<String, String> map : maps.entrySet()) {
//            RequestBody requestBody = RequestBody.create(MediaType_MULTIPART, map.getValue());
//            builder.addFormDataPart("form", map.getKey(), requestBody);
            builder.addFormDataPart(map.getKey(), map.getValue());
//            builder.addPart(requestBody);
        }

        builder.setType(MultipartBody.FORM);
        return builder.build();
    }


}
