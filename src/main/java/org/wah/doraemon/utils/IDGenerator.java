package org.wah.doraemon.utils;

import java.math.BigInteger;
import java.util.UUID;

/**
 * ID生成器
 */
public class IDGenerator{

    public IDGenerator(){

    }

    /**
     * JDK UUID 16位
     */
    public static String uuid16(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        BigInteger first = new BigInteger(uuid.substring(0, uuid.length() / 2), 16);
        BigInteger last = new BigInteger(uuid.substring(uuid.length() / 2, uuid.length()), 16);

        return first.add(last).toString(16);
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

    /**
     * 32位UUID转36进制
     */
    public static String uuidTo36Ary(){
        return new BigInteger(UUID.randomUUID().toString().replaceAll("-", ""), 16).toString(36);
    }

    /**
     * 32位UUID转36进制
     */
    public static String uuidTo36Ary(String uuid){
        return new BigInteger(uuid.replaceAll("-", ""), 16).toString(36);
    }
}
