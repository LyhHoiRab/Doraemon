package com.wah.doraemon.entity;

import java.util.Date;

public interface Delete{

    Boolean getIsDelete();

    void setIsDelete(Boolean isDelete);

    Date getDeleteTime();

    void setDeleteTime(Date deleteTime);
}
