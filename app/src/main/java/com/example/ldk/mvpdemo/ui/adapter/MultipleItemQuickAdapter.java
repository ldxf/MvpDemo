package com.example.ldk.mvpdemo.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ldk.mvpdemo.R;
import com.example.ldk.mvpdemo.common.http.api.ApiService;
import com.example.ldk.mvpdemo.common.utils.GlideImageLoader;
import com.example.ldk.mvpdemo.data.entity.HomeFragBean;
import com.example.ldk.mvpdemo.ui.adapter.bean.MultipleItem;
import com.example.ldk.mvpdemo.ui.fragment.HomeFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldk on 2017/10/19.
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ICON, R.layout.item_viewpager);
        addItemType(MultipleItem.CONTENT, R.layout.item_content);
    }

    private Banner banner;

    @Override
    protected void convert(BaseViewHolder helper, final MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.ICON:
                List<String> imgs = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                for (HomeFragBean.IocnBean iocnBean : item.getIconTops()) {
                    imgs.add(ApiService.BASE_URL + iocnBean.getImg());
                    titles.add(iocnBean.getDescribe());
                }
                if (banner == null) {
                    banner = helper.getView(R.id.banner);
                }
                //设置banner样式
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(imgs);
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                banner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置轮播时间
                banner.setDelayTime(1500);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.RIGHT);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(mContext, item.getIconTops().get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case MultipleItem.CONTENT:
                helper.setText(R.id.tv_title, item.getDataBean().getTitle());
                break;
        }
    }

    public Banner getBanner() {
        return banner;
    }
}
