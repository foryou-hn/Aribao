package com.example.pc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.pc.myapplication.app.HomeMultipleAdapter;
import com.example.pc.myapplication.bean.NewsListBean;
import com.example.pc.myapplication.netapi.URLConstant;
import com.example.pc.myapplication.netutils.HttpMethods;
import com.example.pc.myapplication.netutils.OnSuccessAndFaultListener;
import com.example.pc.myapplication.netutils.OnSuccessAndFaultSub;
import com.example.pc.myapplication.utils.BeanUtils;
import com.example.pc.myapplication.utils.DateUtils;
import com.example.pc.myapplication.view.BannerLayout;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HomeMultipleAdapter homeMultipleAdapter;
    private Integer curDateInt;
    private String curDateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        curDateStr = DateUtils.formatDate(new Date(), DateUtils.DATE_FORMAT_CUSTOM1);
        curDateInt = Integer.valueOf(curDateStr);
        initData();


    }

    private void initData() {
        toolbar.setTitle("首页");
        navView.setItemIconTintList(null);
//        oldTitleList.add((String) toolbar.getTitle());
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        navView.setNavigationItemSelectedListener(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if (firstVisibleItemPosition == 0) {
                        toolbar.setTitle("首页");
                        return;
                    }
                    //设置动态的title
                    if (homeMultipleAdapter != null) {
                        for (int i = firstVisibleItemPosition; i > 0; i--) {
                            int itemViewType = homeMultipleAdapter.getItemViewType(i);
                            if (itemViewType == NewsListBean.StoriesBean.TEXT) {
                                toolbar.setTitle(homeMultipleAdapter.getData().get(i - 1).getTitle());
                                break;
                            }
                        }
                    }
                }
            }
        });
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        swipeLayout.setOnRefreshListener(this);

        lodLatestData();//加载最新消息
    }

    private void lodLatestData() {
        HttpMethods.getInstance().toSubscribe(HttpMethods.getHttpApi().latest(),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        NewsListBean newsListBean = gson.fromJson(result, NewsListBean.class);
                        List<NewsListBean.StoriesBean> stories = newsListBean.getStories();
                        if (!BeanUtils.isEmpty(stories)) {
                            NewsListBean.StoriesBean newsData = new NewsListBean.StoriesBean();
                            newsData.setTitle("今日热闻");
                            newsData.setType(NewsListBean.StoriesBean.TEXT);
                            stories.add(0, newsData);
                            showNewsList(stories, true);
                        }
                        //展示banner
                        showBanner(newsListBean.getTop_stories());
                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        swipeLayout.setRefreshing(false);
                    }
                }, MainActivity.this, true));
    }

    private void showBanner(final List<NewsListBean.TopStoriesBean> topStories) {
        View bannerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.banner_layout, null);
        BannerLayout banner = bannerView.findViewById(R.id.banner);
        banner.setImageLoader(new BannerLayout.ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView, int errImage) {
                Glide.with(MainActivity.this).load(path).into(imageView);
            }
        });
        banner.setViewUrls(topStories);
        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("id", topStories.get(position).getId());
                startActivity(intent);
            }
        });
        homeMultipleAdapter.removeAllHeaderView();
        homeMultipleAdapter.addHeaderView(bannerView);
    }

    private void lodMoreData() {
        HttpMethods.getInstance().toSubscribe(HttpMethods.getHttpApi().before(URLConstant.BEFORE_URL + curDateInt),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        NewsListBean newsListBean = gson.fromJson(result, NewsListBean.class);
                        List<NewsListBean.StoriesBean> stories = newsListBean.getStories();
                        if (!BeanUtils.isEmpty(stories)) {
                            NewsListBean.StoriesBean newsData = new NewsListBean.StoriesBean();
                            newsData.setTitle(DateUtils.getDateStr(String.valueOf(curDateInt), DateUtils.DATE_FORMAT_CUSTOM1, DateUtils.DATE_FORMAT_CUSTOM2)
                                    + " " + DateUtils.getWeek(String.valueOf(curDateInt), DateUtils.DATE_FORMAT_CUSTOM1));
                            newsData.setType(NewsListBean.StoriesBean.TEXT);
                            stories.add(0, newsData);
                            showNewsList(stories, false);
                        }
                        if (!BeanUtils.isEmpty(homeMultipleAdapter)) {
                            homeMultipleAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        if (!BeanUtils.isEmpty(homeMultipleAdapter)) {
                            homeMultipleAdapter.loadMoreFail();
                        }
                    }
                }));
    }

    private void showNewsList(List<NewsListBean.StoriesBean> storiesBeans, boolean isRefresh) {
//        if (adapter == null) {
//            adapter = new BaseQuickAdapter<NewsListBean.StoriesBean, BaseViewHolder>(R.layout.news_list_item, storiesBeans) {
//                @Override
//                protected void convert(BaseViewHolder helper, NewsListBean.StoriesBean item) {
//                    helper.setText(R.id.title, item.getTitle());
//                    ImageView image = helper.getView(R.id.image);
//                    Glide.with(MainActivity.this).load(item.getImages().get(0)).into(image);
//                }
//            };
//            recycleView.setAdapter(adapter);
//        }

        if (homeMultipleAdapter == null) {
            homeMultipleAdapter = new HomeMultipleAdapter(storiesBeans);
            homeMultipleAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
            homeMultipleAdapter.setOnLoadMoreListener(this, recycleView);
            homeMultipleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                    intent.putExtra("id", homeMultipleAdapter.getData().get(position).getId());
                    startActivity(intent);
                }
            });
            recycleView.setAdapter(homeMultipleAdapter);
        } else {
            if (isRefresh) {
                homeMultipleAdapter.setNewData(storiesBeans);
            } else {
                homeMultipleAdapter.addData(storiesBeans);
            }
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


    @Override
    public void onLoadMoreRequested() {
        lodMoreData();
        curDateInt--;
    }

    @Override
    public void onRefresh() {
        swipeLayout.setRefreshing(true);
        lodLatestData();
        curDateInt = Integer.valueOf(curDateStr);
    }
}
