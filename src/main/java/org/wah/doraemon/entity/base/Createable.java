package org.wah.doraemon.entity.base;

import java.util.Date;

/**
 * 表示实体可被创建且能持久化
 */
public interface Createable{

    Date getCreateTime();

    void setCreateTime(Date createTime);
}
