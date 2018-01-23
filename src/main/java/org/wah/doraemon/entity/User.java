package org.wah.doraemon.entity;

import org.wah.doraemon.consts.Sex;
import org.wah.doraemon.entity.base.Createable;
import org.wah.doraemon.entity.base.Entity;
import org.wah.doraemon.entity.base.Updateable;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
public class User extends Entity implements Createable, Updateable{

    //ID
    private String id;
    //账户ID
    private String accountId;
    //头像
    private String headImgUrl;
    //昵称
    private String nickname;
    //年龄
    private Integer age;
    //出生
    private Date birthday;
    //性别
    private Sex sex;
    //签名
    private String autograph;
    //扩展属性
    private List<UserExpand> expands;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

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

    public String getHeadImgUrl(){
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl){
        this.headImgUrl = headImgUrl;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public Sex getSex(){
        return sex;
    }

    public void setSex(Sex sex){
        this.sex = sex;
    }

    public String getAutograph(){
        return autograph;
    }

    public void setAutograph(String autograph){
        this.autograph = autograph;
    }

    public List<UserExpand> getExpands(){
        return expands;
    }

    public void setExpands(List<UserExpand> expands){
        this.expands = expands;
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
