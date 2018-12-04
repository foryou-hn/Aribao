package com.example.pc.myapplication;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pc.myapplication.bean.NewsBean;
import com.example.pc.myapplication.netapi.URLConstant;
import com.example.pc.myapplication.netutils.HttpMethods;
import com.example.pc.myapplication.netutils.OnSuccessAndFaultListener;
import com.example.pc.myapplication.netutils.OnSuccessAndFaultSub;
import com.example.pc.myapplication.utils.BeanUtils;
import com.example.pc.myapplication.utils.ScreenUtils;
import com.google.gson.Gson;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.top_image)
    ImageView topImage;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.fl_titleBar)
    FrameLayout flTitleBar;
    @BindView(R.id.nScrollView)
    NestedScrollView nScrollView;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra("id", -1);
        initData();
        loadNews();
    }

    private void initData() {
        nScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY - scrollY > 3) {
                    flTitleBar.setAlpha(1f);
                    ivBack.setEnabled(true);
                }
                if (oldScrollY - scrollY < 0){
                    flTitleBar.setAlpha(0f);
                    ivBack.setEnabled(false);
                }
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScrollRange = appBarLayout.getTotalScrollRange() - ScreenUtils.dp2px(NewsActivity.this, 56);
                float alphaValue = (float) ((maxScrollRange + verticalOffset) < 0 ? 0 : maxScrollRange + verticalOffset) / (float) maxScrollRange;
                flTitleBar.setAlpha(alphaValue);
                if (alphaValue == 0) {
                    ivBack.setEnabled(false);
                } else {
                    ivBack.setEnabled(true);
                }
            }
        });
    }

    private void loadNews() {
        HttpMethods.getInstance().toSubscribe(HttpMethods.getHttpApi().getNews(URLConstant.BASE_URL + id),
                new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("test", "result: " + result);
                        Gson gson = new Gson();
                        NewsBean newsBean = gson.fromJson(result, NewsBean.class);
                        Glide.with(NewsActivity.this).load(newsBean.getImage()).into(topImage);
                        topTitle.setText(newsBean.getTitle());
                        String body = newsBean.getBody();
                        if (!BeanUtils.isEmptyStr(body)) {
                            webView.loadDataWithBaseURL(null, getNewData(body), "text/html", "utf-8", null);
                            WebSettings settings = webView.getSettings();
                            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                            settings.setUseWideViewPort(true);
//                            settings.setLoadWithOverviewMode(true);
                            // 设置支持Javascript
                            settings.setJavaScriptEnabled(true);
                            // 1. 设置缓存路径
                            String cacheDirPath = getFilesDir().getAbsolutePath()+"cache/";
                            settings.setAppCachePath(cacheDirPath);
                            // 2. 设置缓存大小
                            settings.setAppCacheMaxSize(20*1024*1024);
                            // 3. 开启Application Cache存储机制
                            settings.setAppCacheEnabled(true);
                        }
                    }

                    @Override
                    public void onFault(String errorMsg) {
                        Log.e("test", "errorMsg: " + errorMsg);
                    }
                }));
    }

    /**
     * 设置img标签下的width为手机屏幕宽度，height自适应
     *
     * @param data html字符串
     * @return 更新宽高属性后的html字符串
     */
    private String getNewData(String data) {
        Document document = Jsoup.parse(data);

        Elements pElements = document.select("p:has(img)");
        for (Element pElement : pElements) {
            pElement.attr("style", "text-align:center");
            pElement.attr("max-width", String.valueOf(ScreenUtils.getScreenWidth(NewsActivity.this) + "px"))
                    .attr("height", "auto");
        }
        Elements imgElements = document.select("img");
        for (Element imgElement : imgElements) {
            //重新设置宽高
            imgElement.attr("max-width", "100%")
                    .attr("height", "auto");
            imgElement.attr("style", "max-width:100%;height:auto");
        }
        Elements spanElements = document.select("span");
        for (Element spanElement : spanElements) {
            //重新设置宽高
            spanElement.attr("max-width", "100%")
                    .attr("height", "auto");
            spanElement.attr("style", "max-width:100%;height:auto");
        }
        Log.i("newData:", document.toString());
        return document.toString();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
