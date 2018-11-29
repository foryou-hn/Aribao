package com.example.pc.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc.myapplication.bean.NewsBean;
import com.example.pc.myapplication.netutils.HttpMethods;
import com.example.pc.myapplication.netutils.OnSuccessAndFaultListener;
import com.example.pc.myapplication.netutils.OnSuccessAndFaultSub;
import com.example.pc.myapplication.view.BannerLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private BaseQuickAdapter<NewsBean.StoriesBean, BaseViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();


    }

    private void initData() {
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        navView.setNavigationItemSelectedListener(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));


        lodData();
    }

    private void lodData() {
        HttpMethods.getInstance().toSubscribe(HttpMethods.getHttpApi().latest(),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("test", "result: " + result);
                        Gson gson = new Gson();
                        NewsBean newsBean = gson.fromJson(result, NewsBean.class);
                        showNewsList(newsBean.getStories());
                        showBanner(newsBean.getTop_stories());
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        Log.e("test", "errorMsg: " + errorMsg);
                    }
                }));
    }

    private void showBanner(List<NewsBean.TopStoriesBean> topStories) {
        View bannerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.banner_layout, null);
        BannerLayout banner = bannerView.findViewById(R.id.banner);
        banner.setImageLoader(new BannerLayout.ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView, int errImage) {
                Glide.with(MainActivity.this).load(path).into(imageView);
            }
        });
        banner.setViewUrls(topStories);
        adapter.addHeaderView(bannerView);
    }

    private void showNewsList(List<NewsBean.StoriesBean> storiesBeans) {
        if (storiesBeans == null) return;
        if (adapter == null) {
            adapter = new BaseQuickAdapter<NewsBean.StoriesBean, BaseViewHolder>(R.layout.news_list_item, storiesBeans) {
                @Override
                protected void convert(BaseViewHolder helper, NewsBean.StoriesBean item) {
                    helper.setText(R.id.title, item.getTitle());
                    ImageView image = helper.getView(R.id.image);
                    Glide.with(MainActivity.this).load(item.getImages().get(0)).into(image);
                }
            };
            recycleView.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
