package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class NotLoginStateException extends ApplicationException{

    public NotLoginStateException(){

    }

    public NotLoginStateException(Throwable cause){
        super(cause);
    }

    public NotLoginStateException(String message, Throwable cause){
        super(message, cause);
    }

    public NotLoginStateException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public NotLoginStateException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public NotLoginStateException(String message){
        super(message);
    }

    public NotLoginStateException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
