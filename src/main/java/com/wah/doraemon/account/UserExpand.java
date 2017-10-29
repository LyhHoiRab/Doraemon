package com.wah.doraemon.account;

import com.wah.doraemon.commons.security.constants.UsingState;
import com.wah.doraemon.entity.Create;
import com.wah.doraemon.entity.Entity;
import com.wah.doraemon.entity.Update;

import java.util.Date;

public class UserExpand extends Entity implements Create, Update{

    //ID
    private String id;
    //用户ID
    private String userId;
    //名称
    private String name;
    //值
    private String value;
    //描述
    private String description;
    //状态
    private UsingState state;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public UserExpand(){

    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public void setId(String id){
        this.id = id;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public UsingState getState(){
        return state;
    }

    public void setState(UsingState state){
        this.state = state;
    }

    @Override
    public Date getCreateTime(){
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime(){
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
}
