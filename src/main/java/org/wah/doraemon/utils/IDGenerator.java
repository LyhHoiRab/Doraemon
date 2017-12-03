package org.wah.doraemon.utils;

import java.util.UUID;

/**
 * ID生成器
 */
public class IDGenerator{

    public IDGenerator(){

    }

    /**
     * JDK UUID 32位
     */
    public static String uuid32(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * JDK UUID 36位
     */
    public static String uuid36(){
        return UUID.randomUUID().toString();
    }
}
