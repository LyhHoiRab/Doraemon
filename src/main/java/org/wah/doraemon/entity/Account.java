package org.wah.doraemon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.wah.doraemon.consts.AccountState;
import org.wah.doraemon.entity.base.Createable;
import org.wah.doraemon.entity.base.Deleteable;
import org.wah.doraemon.entity.base.Entity;
import org.wah.doraemon.entity.base.Updateable;

import java.util.Date;

/**
 * 账户
 */
@Data
@NoArgsConstructor
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
    private Boolean isDelete;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除时间
    private Date deleteTime;
}
