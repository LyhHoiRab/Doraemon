package org.wah.doraemon.security.exception;

import java.text.MessageFormat;

public class AccountNotFoundException extends ApplicationException{

    public AccountNotFoundException(){

    }

    public AccountNotFoundException(Throwable cause){
        super(cause);
    }

    public AccountNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public AccountNotFoundException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public AccountNotFoundException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
