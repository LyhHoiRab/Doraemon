package org.wah.doraemon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.wah.doraemon.consts.Sex;
import org.wah.doraemon.entity.base.Createable;
import org.wah.doraemon.entity.base.Entity;
import org.wah.doraemon.entity.base.Updateable;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
@Data
@NoArgsConstructor
public class User extends Entity implements Createable, Updateable{

    //账户ID
    private String accountId;
    //头像
    private String headImgUrl;
    //昵称
    private String nickname;
    //用户姓名
    private String name;
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
}
