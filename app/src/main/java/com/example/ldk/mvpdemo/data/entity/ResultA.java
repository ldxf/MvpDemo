package com.example.ldk.mvpdemo.data.entity;

import java.util.List;

/**
 * Created by ldk on 2017/10/12.
 */

public class ResultA {

    /**
     * error_code : 1
     * msg : 未登录
     * data : [{"name":"小明","sex":"男","age":"20"},{"name":"小红","sex":"女","age":"18"}]
     */

    private int error_code;
    private String msg;
    private List<DataBean> data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 小明
         * sex : 男
         * age : 20
         */

        private String name;
        private String sex;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
