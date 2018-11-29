package com.example.pc.myapplication.bean;

/**
 * 文 件 名：SendUtil
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/15
 * 版    权：v1.0
 */

public class Bean {


    /**
     * success : true
     * msg : 操作成功
     * data : true
     * code : 200
     */

    private boolean success;
    private String msg;
    private boolean data;
    private String code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
