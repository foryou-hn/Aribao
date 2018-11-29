package com.example.pc.myapplication.netutils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 文 件 名：RequestBodyUtils
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/20
 * 版    权：v1.0
 */
public class RequestBodyUtils {

    public static final MediaType MediaType_JSON = MediaType.parse("application/json;charset =UTF-8");
    public static final MediaType MediaType_XML = MediaType.parse("application/xml;charset=UTF-8");

    public static RequestBody getUserExist(JSONObject params) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("params", params);
        return RequestBody.create(MediaType_JSON, String.valueOf(JSONObject.parseObject(JSON.toJSONString(map))));
    }
    public static RequestBody latest() {
//        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
//        map.put("params", params);
        return RequestBody.create(MediaType_JSON, "");
    }

}
