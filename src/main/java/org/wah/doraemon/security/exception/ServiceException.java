package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class ServiceException extends ApplicationException{

    public ServiceException(){

    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public ServiceException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
