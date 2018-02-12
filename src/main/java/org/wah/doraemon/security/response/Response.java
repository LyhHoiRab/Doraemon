package org.wah.doraemon.security.response;

import lombok.Data;
import org.apache.http.HttpStatus;

/**
 * 系统响应
 */
@Data
public class Response<T>{

    //响应状态码
    private Integer code;
    //信息
    private String msg;
    //业务结果
    private Boolean success;
    //响应数据
    private T result;

    public Response(){

    }

    public Response(String msg, T result){
        this.code = HttpStatus.SC_OK;
        this.success = true;
        this.msg = msg;
        this.result = result;
    }
}
