package org.wah.doraemon.security.exception;


import java.text.MessageFormat;

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
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public DataAccessException(String message){
        super(message);
    }

    public DataAccessException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
