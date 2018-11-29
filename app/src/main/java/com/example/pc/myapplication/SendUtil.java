package com.example.pc.myapplication;



import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 文 件 名：SendUtil
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/15
 * 版    权：v1.0
 */
public class SendUtil {
    /**
     * 注册用户
     * 1、获取授权码、凭证
     * 2、封装用户信息进行注册
     */
    public static void registerUser() {

        String rUrl="https://login.jxzwfww.gov.cn/user/updateIdentification.do";

        try {
            JSONObject jsonr=new JSONObject();
            jsonr.put("username", "chenhao666");
            jsonr.put("idcard", "420116199207120014");
            jsonr.put("realname", "陈浩");
            jsonr.put("client_id", "c2e580fd36a147bdbb6519e206985a52");
            //发起请求
            String sr = SendUtil.sendPost(rUrl, "params="+ jsonr);
            Log.e("test", sr);
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost() {
        //访问准备
        URL url = null;
        String response = null;
        try {
            url = new URL("https://login.jxzwfww.gov.cn/user/updateIdentification.do");

        //post参数
        Map<String,Object> params = new LinkedHashMap<>();
            JSONObject jsonr=new JSONObject();
            jsonr.put("username", "chenhao666");
            jsonr.put("idcard", "420116199207120014");
            jsonr.put("realname", "陈浩");
            jsonr.put("client_id", "c2e580fd36a147bdbb6519e206985a52");
        params.put("params", jsonr);

        //开始访问
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        response = sb.toString();
        System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
