package com.wah.doraemon.account;

import com.wah.doraemon.commons.security.constants.UsingState;
import com.wah.doraemon.entity.Create;
import com.wah.doraemon.entity.Delete;
import com.wah.doraemon.entity.Entity;
import com.wah.doraemon.entity.Update;

import java.util.Date;

/**
 * 账户
 */
public class Account extends Entity implements Create, Update, Delete{

    //ID
    private String id;
    //登录名称
    private String username;
    //手机号码
    private String phone;
    //电子邮件
    private String email;
    //登录密码
    private String password;
    //状态
    private UsingState state;
    //是否删除
    private Boolean isDelete;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除时间
    private Date deleteTime;

    public Account(){

    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public void setId(String id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public UsingState getState(){
        return state;
    }

    public void setState(UsingState state){
        this.state = state;
    }

    @Override
    public Boolean getIsDelete(){
        return isDelete;
    }

    @Override
    public void setIsDelete(Boolean isDelete){
        this.isDelete = isDelete;
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

    @Override
    public Date getDeleteTime(){
        return deleteTime;
    }

    @Override
    public void setDeleteTime(Date deleteTime){
        this.deleteTime = deleteTime;
    }
}
