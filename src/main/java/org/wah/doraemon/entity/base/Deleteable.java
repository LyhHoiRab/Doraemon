package org.wah.doraemon.entity.base;

import java.util.Date;

/**
 * 表示实体能被持久化但只能被逻辑删除
 */
public interface Deleteable{

    Boolean getIsDelete();

    void setIsDelete(Boolean isDelete);

    Date getDeleteTime();

    void setDeleteTime(Date deleteTime);
}
