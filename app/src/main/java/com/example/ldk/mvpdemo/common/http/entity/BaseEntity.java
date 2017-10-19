package com.example.ldk.mvpdemo.common.http.entity;

import java.util.List;

/**
 * Created by ldk on 2017/10/13.
 */

public class BaseEntity<T> {

    /**
     * error_code : 1
     * msg : 未登录
     * data : [{"name":"小明","sex":"男","age":"20"}]
     */

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public boolean isSuccessed() {
        return code==0;
    }
}
