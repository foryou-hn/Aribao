package com.example.pc.myapplication.netapi;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 文 件 名：HttpApi
 * 描    述：存放所有的Api
 * 作    者：chenhao
 * 时    间：2018/11/15
 * 版    权：v1.0
 */

public interface HttpApi {
//    //请填写自己的接口名
//    @POST("getUserExist.do")
//    @Headers("Content-Type:application/x-www-form-urlencoded")
//    Observable<ResponseBody> getUserExist(@Body RequestBody body);
//    //请填写自己的接口名
//    @GET("latest")
//    @Headers("Content-Type:application/json;charset=UTF-8")
//    Observable<ResponseBody> latest(@QueryMap Map<String, Object> map);
    /**
     * 通过地址下载一个文件
     */
    @GET()
    @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    @GET("latest")
    Observable<ResponseBody> latest ();

//    @FormUrlEncoded
//    @POST("top250")
//    Call<MovieSubject> getTop250 (@Field("start") int start , @Field("count") int count);

}
