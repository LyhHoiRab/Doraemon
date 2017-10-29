package com.wah.doraemon.commons.security.exception;

import java.text.MessageFormat;

/**
 * 持久层级别异常
 */
public class DataAccessException extends ApplicationException{

    public DataAccessException(){
    }

    public DataAccessException(Throwable cause){
        super(cause);
    }

    public DataAccessException(String message, Throwable cause){
        super(message, cause);
    }

    public DataAccessException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public DataAccessException(Enum clazz, Throwable cause, Object... args){
        super((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public DataAccessException(String message){
        super(message);
    }

    public DataAccessException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
