package com.wah.doraemon.commons.security.exception;

import java.text.MessageFormat;

/**
 * 未知账户异常
 */
public class UnknownAccountException extends SecurityException{

    public UnknownAccountException(){
    }

    public UnknownAccountException(Throwable cause){
        super(cause);
    }

    public UnknownAccountException(String message, Throwable cause){
        super(message, cause);
    }

    public UnknownAccountException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public UnknownAccountException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public UnknownAccountException(String message){
        super(message);
    }

    public UnknownAccountException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
