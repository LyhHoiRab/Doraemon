package com.wah.doraemon.commons.security.exception;

import java.text.MessageFormat;

/**
 * 视图异常
 */
public class ModelAndViewException extends ApplicationException{

    public ModelAndViewException(){
    }

    public ModelAndViewException(Throwable cause){
        super(cause);
    }

    public ModelAndViewException(String message, Throwable cause){
        super(message, cause);
    }

    public ModelAndViewException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public ModelAndViewException(Enum clazz, Throwable cause, Object... args){
        super((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public ModelAndViewException(String message){
        super(message);
    }

    public ModelAndViewException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
