package com.wah.doraemon.account;

import com.wah.doraemon.commons.security.constants.Sex;
import com.wah.doraemon.commons.security.constants.UsingState;
import com.wah.doraemon.entity.Create;
import com.wah.doraemon.entity.Delete;
import com.wah.doraemon.entity.Entity;
import com.wah.doraemon.entity.Update;

import java.util.Date;
import java.util.List;

public class User extends Entity implements Create, Update, Delete{

    //ID
    private String id;
    //账户ID
    private String accountId;
    //用户编码
    private Integer code;
    //昵称
    private String nickname;
    //真实姓名
    private String name;
    //身份证
    private String idCard;
    //性别
    private Sex sex;
    //出生日期
    private Date birthday;
    //状态
    private UsingState state;
    //头像
    private String headImgUrl;
    //扩展属性
    private List<UserExpand> expands;
    //是否删除
    private Boolean isDelete;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除时间
    private Date deleteTime;

    public User(){

    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public void setId(String id){
        this.id = id;
    }

    public String getAccountId(){
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getIdCard(){
        return idCard;
    }

    public void setIdCard(String idCard){
        this.idCard = idCard;
    }

    public Sex getSex(){
        return sex;
    }

    public void setSex(Sex sex){
        this.sex = sex;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public UsingState getState(){
        return state;
    }

    public void setState(UsingState state){
        this.state = state;
    }

    public String getHeadImgUrl(){
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl){
        this.headImgUrl = headImgUrl;
    }

    public List<UserExpand> getExpands(){
        return expands;
    }

    public void setExpands(List<UserExpand> expands){
        this.expands = expands;
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
