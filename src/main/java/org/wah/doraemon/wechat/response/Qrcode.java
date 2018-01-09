package org.wah.doraemon.wechat.response;

import com.google.gson.annotations.SerializedName;

public class Qrcode extends Error{

    @SerializedName("errcode")
    public Integer errCode;
    @SerializedName("errmsg")
    public String errMsg;
    public String ticket;
    @SerializedName("expire_seconds")
    public Integer expireSeconds;
    public String url;

    public Qrcode(){

    }

    public String getTicket(){
        return ticket;
    }

    public void setTicket(String ticket){
        this.ticket = ticket;
    }

    public Integer getExpireSeconds(){
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds){
        this.expireSeconds = expireSeconds;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
