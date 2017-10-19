package com.example.ldk.mvpdemo.data.entity;

import java.util.List;

/**
 * Created by ldk on 2017/10/16.
 */

public class DataBean {
    private List<FilesBean> files;
    private List<KeyBean> key;

    public List<FilesBean> getFiles() {
        return files;
    }

    public void setFiles(List<FilesBean> files) {
        this.files = files;
    }

    public List<KeyBean> getKey() {
        return key;
    }

    public void setKey(List<KeyBean> key) {
        this.key = key;
    }

    public static class FilesBean {
        /**
         * name : 1508125481046.jpg
         * path : /Files/
         */

        private String name;
        private String path;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    public static class KeyBean {
        /**
         * key : text0
         * value : json0
         */

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
