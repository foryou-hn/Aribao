package com.example.pc.myapplication.netutils;

/**
 * 文 件 名：OnSuccessAndFaultListener
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/15
 * 版    权：v1.0
 */
public interface OnSuccessAndFaultListener {
    void onSuccess(String result);

    void onFault(String errorMsg);
}
