package com.wah.doraemon.commons.security.exception;

import java.text.MessageFormat;

public class SecurityException extends ApplicationException{

    public SecurityException(){
    }

    public SecurityException(Throwable cause){
        super(cause);
    }

    public SecurityException(String message, Throwable cause){
        super(message, cause);
    }

    public SecurityException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public SecurityException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public SecurityException(String message){
        super(message);
    }

    public SecurityException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
