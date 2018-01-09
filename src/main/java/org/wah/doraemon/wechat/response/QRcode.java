package org.wah.doraemon.wechat.response;

import com.google.gson.annotations.SerializedName;

public class QRcode implements Error{

    @SerializedName("errcode")
    public String errCode;
    @SerializedName("errmsg")
    public String errMsg;
    public String ticket;
    @SerializedName("expire_seconds")
    public Integer expireSeconds;
    public String url;

    public QRcode(){

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

    @Override
    public String getErrCode(){
        return errCode;
    }

    @Override
    public void setErrCode(String errCode){
        this.errCode = errCode;
    }

    @Override
    public String getErrMsg(){
        return errMsg;
    }

    @Override
    public void setErrMsg(String errMsg){
        this.errMsg = errMsg;
    }
}
