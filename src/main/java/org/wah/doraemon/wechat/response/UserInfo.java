package org.wah.doraemon.wechat.response;

import com.google.gson.annotations.SerializedName;
import org.wah.doraemon.consts.Sex;

public class UserInfo implements Error{

    @SerializedName("errcode")
    public String errCode;
    @SerializedName("errmsg")
    public String errMsg;
    public Integer subscribe;
    @SerializedName("openid")
    public String openId;
    public Sex sex;
    public String language;
    public String city;
    public String province;
    public String country;
    @SerializedName("headimgurl")
    public String headImgUrl;
    @SerializedName("subscribe_time")
    public String subscribeTime;
    @SerializedName("unionid")
    public String unionId;
    public String remark;

    public UserInfo(){

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

    public Integer getSubscribe(){
        return subscribe;
    }

    public void setSubscribe(Integer subscribe){
        this.subscribe = subscribe;
    }

    public String getOpenId(){
        return openId;
    }

    public void setOpenId(String openId){
        this.openId = openId;
    }

    public Sex getSex(){
        return sex;
    }

    public void setSex(Sex sex){
        this.sex = sex;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getProvince(){
        return province;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getHeadImgUrl(){
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl){
        this.headImgUrl = headImgUrl;
    }

    public String getSubscribeTime(){
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime){
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId(){
        return unionId;
    }

    public void setUnionId(String unionId){
        this.unionId = unionId;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }
}
