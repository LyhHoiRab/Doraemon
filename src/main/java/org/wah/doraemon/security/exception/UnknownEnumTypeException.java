package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class UnknownEnumTypeException extends ApplicationException{

    public UnknownEnumTypeException(){

    }

    public UnknownEnumTypeException(Throwable cause){
        super(cause);
    }

    public UnknownEnumTypeException(String message, Throwable cause){
        super(message, cause);
    }

    public UnknownEnumTypeException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public UnknownEnumTypeException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public UnknownEnumTypeException(String message){
        super(message);
    }

    public UnknownEnumTypeException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
