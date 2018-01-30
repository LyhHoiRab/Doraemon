package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class ResourceNotFoundException extends ApplicationException{

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public ResourceNotFoundException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
