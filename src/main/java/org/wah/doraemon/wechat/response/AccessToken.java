package org.wah.doraemon.wechat.response;


import com.google.gson.annotations.SerializedName;

public class AccessToken implements Error{

    @SerializedName("errcode")
    public String errCode;
    @SerializedName("errmsg")
    public String errMsg;
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("expires_in")
    public Integer expiresIn;

    public AccessToken(){

    }

    public String getAccessToken(){
        return accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn(){
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn){
        this.expiresIn = expiresIn;
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
