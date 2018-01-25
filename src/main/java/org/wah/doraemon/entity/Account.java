package org.wah.doraemon.entity;

import org.wah.doraemon.consts.AccountState;
import org.wah.doraemon.entity.base.Createable;
import org.wah.doraemon.entity.base.Deleteable;
import org.wah.doraemon.entity.base.Entity;
import org.wah.doraemon.entity.base.Updateable;

import java.util.Date;

/**
 * 账户
 */
public class Account extends Entity implements Createable, Updateable, Deleteable{

    //登录名称
    private String username;
    //密码
    private String password;
    //电子邮箱
    private String email;
    //手机
    private String phone;
    //状态
    private AccountState state;
    //删除状态
    private boolean isDelete;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除时间
    private Date deleteTime;

    public Account(){

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public AccountState getState(){
        return state;
    }

    public void setState(AccountState state){
        this.state = state;
    }

    @Override
    public boolean getIsDelete(){
        return isDelete;
    }

    public void setIsDelete(boolean isDelete){
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
