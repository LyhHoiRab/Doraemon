package org.wah.doraemon.wechat.response;


import com.google.gson.annotations.SerializedName;

public class AccessToken extends Error{

    @SerializedName("errcode")
    public Integer errCode;
    @SerializedName("errmsg")
    public String errMsg;
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("expires_id")
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
}
