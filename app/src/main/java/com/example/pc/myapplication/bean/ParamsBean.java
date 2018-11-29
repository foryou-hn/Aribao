package com.example.pc.myapplication.bean;

/**
 * 文 件 名：ParamsBean
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/19
 * 版    权：v1.0
 */
public class ParamsBean{
    /**
     * username : 420528199603074432
     * usertype : 0
     * client_id : c2e580fd36a147bdbb6519e206985a52
     */
    private String username;
    private String usertype;
    private String client_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append("\"" + "username" + "\"" + ":" + "\"" + username + "\",");
        buffer.append("\"" + "usertype" + "\"" + ":" + "\"" + usertype + "\",");
        buffer.append("\"" + "client_id" + "\"" + ":" + "\"" + client_id + "\"");
        buffer.append("}");
        String paramsJson = buffer.toString();
        return paramsJson;
    }
}
