package com.example.ldk.mvpdemo.data.entity;

import java.util.List;

/**
 * Created by ldk on 2017/10/17.
 */

public class HomeFragBean {

    /**
     * iocn : [{"img":"","describe":"","title":""},{"img":"","describe":"","title":""}]
     * data : [{"describe":"","img":"","msg":"","title":"我的商店0","value":""},{"describe":"","img":"","msg":"","title":"我的商店1","value":""},{"describe":"","img":"","msg":"","title":"我的商店2","value":""},{"describe":"","img":"","msg":"","title":"我的商店3","value":""}]
     * page : 0
     * size : 4
     */

    private int page;
    private int size;
    private List<IocnBean> iocn;
    private List<DataBean> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<IocnBean> getIocn() {
        return iocn;
    }

    public void setIocn(List<IocnBean> iocn) {
        this.iocn = iocn;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class IocnBean {
        /**
         * img :
         * describe :
         * title :
         */

        private String img;
        private String describe;
        private String title;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class DataBean {
        /**
         * describe :
         * img :
         * msg :
         * title : 我的商店0
         * value :
         */

        private String describe;
        private String img;
        private String msg;
        private String title;
        private String value;

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
