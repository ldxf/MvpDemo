package com.example.ldk.mvpdemo.ui.adapter.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;

import java.util.List;

/**
 * Created by ldk on 2017/10/19.
 */

public class MultipleItem implements MultiItemEntity {
    public static final int ICON = 1;
    public static final int CONTENT = 2;
    private int itemType;
    private HomeFragBean.DataBean dataBean;
    private List<HomeFragBean.IocnBean> iconTops;

    public List<HomeFragBean.IocnBean> getIconTops() {
        return iconTops;
    }

    public void setIconTops(List<HomeFragBean.IocnBean> iconTops) {
        this.iconTops = iconTops;
    }

    public HomeFragBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(HomeFragBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
