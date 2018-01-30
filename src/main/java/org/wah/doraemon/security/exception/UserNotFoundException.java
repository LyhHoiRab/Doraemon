package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends ApplicationException{

    public UserNotFoundException(){

    }

    public UserNotFoundException(Throwable cause){
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public UserNotFoundException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public UserNotFoundException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
