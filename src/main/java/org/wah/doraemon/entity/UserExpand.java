package org.wah.doraemon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.wah.doraemon.entity.base.Createable;
import org.wah.doraemon.entity.base.Entity;
import org.wah.doraemon.entity.base.Updateable;

import java.util.Date;

/**
 * 用户信息扩展
 */
@Data
@NoArgsConstructor
public class UserExpand extends Entity implements Createable, Updateable{

    //用户ID
    private String userId;
    //属性名
    private String name;
    //属性值
    private String value;
    //描述
    private String description;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
