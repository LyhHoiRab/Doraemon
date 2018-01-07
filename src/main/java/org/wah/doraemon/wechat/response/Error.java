package org.wah.doraemon.wechat.response;

public abstract class Error{

    private Integer errCode;
    private String errMsg;

    public Error(){

    }

    public Integer getErrCode(){
        return errCode;
    }

    public void setErrCode(Integer errCode){
        this.errCode = errCode;
    }

    public String getErrMsg(){
        return errMsg;
    }

    public void setErrMsg(String errMsg){
        this.errMsg = errMsg;
    }
}
