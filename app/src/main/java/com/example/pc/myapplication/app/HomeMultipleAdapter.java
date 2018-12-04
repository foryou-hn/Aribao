package com.example.pc.myapplication.app;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc.myapplication.R;
import com.example.pc.myapplication.bean.NewsListBean;

import java.util.List;

/**
 * 文 件 名：HomeMultipleAdapter
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/29
 * 版    权：v1.0
 */
public class HomeMultipleAdapter extends BaseMultiItemQuickAdapter<NewsListBean.StoriesBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeMultipleAdapter(List<NewsListBean.StoriesBean> data) {
        super(data);
        addItemType(NewsListBean.StoriesBean.TEXT, R.layout.news_list_text_item);
        addItemType(NewsListBean.StoriesBean.IMG_TEXT, R.layout.news_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsListBean.StoriesBean item) {
        switch (helper.getItemViewType()) {
            case NewsListBean.StoriesBean.TEXT:
                helper.setText(R.id.title_txt, item.getTitle());
                break;
            case NewsListBean.StoriesBean.IMG_TEXT:
                helper.setText(R.id.title, item.getTitle());
                ImageView image = helper.getView(R.id.image);
                Glide.with(mContext).load(item.getImages().get(0)).into(image);
                break;
        }
    }
}
