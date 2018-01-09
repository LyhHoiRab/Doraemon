package org.wah.doraemon.wechat.response;

public interface Error{

    String getErrCode();

    void setErrCode(String errCode);

    String getErrMsg();

    void setErrMsg(String errMsg);
}
