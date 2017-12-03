package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class ApplicationException extends RuntimeException{

    public ApplicationException(){

    }

    public ApplicationException(Throwable cause){
        super(cause);
    }

    public ApplicationException(String message, Throwable cause){
        super(message, cause);
    }

    public ApplicationException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public ApplicationException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
