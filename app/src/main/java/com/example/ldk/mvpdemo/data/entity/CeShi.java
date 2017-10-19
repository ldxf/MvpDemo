package com.example.ldk.mvpdemo.data.entity;

import java.util.List;

/**
 * Created by ldk on 2017/10/13.
 */

public class CeShi {


    /**
     * m : 0000
     * data : [{"name":"小明","sex":"男","age":"20"},{"name":"小红","sex":"女","age":"18"}]
     */

    private String m;
    private List<DataBean> data;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
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
