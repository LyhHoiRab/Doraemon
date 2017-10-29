package com.wah.doraemon.commons.security.exception;

import java.text.MessageFormat;

/**
 * 账户认证异常
 */
public class AuthenticationException extends SecurityException{

    public AuthenticationException(){
    }

    public AuthenticationException(Throwable cause){
        super(cause);
    }

    public AuthenticationException(String message, Throwable cause){
        super(message, cause);
    }

    public AuthenticationException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public AuthenticationException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public AuthenticationException(String message){
        super(message);
    }

    public AuthenticationException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
