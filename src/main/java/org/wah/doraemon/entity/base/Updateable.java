package org.wah.doraemon.entity.base;

import java.util.Date;

/**
 * 表示实体可被持久化且能更新
 */
public interface Updateable{

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);
}
