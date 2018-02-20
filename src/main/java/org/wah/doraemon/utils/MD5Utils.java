package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import java.security.MessageDigest;

/**
 * MD5摘要算法
 */
public class MD5Utils{

    private static final String ALGORITHM = "MD5";

    public MD5Utils(){

    }

    public static String encode(String str, boolean upperCase){
        if(StringUtils.isBlank(str)){
            throw new UtilsException("摘要内容不能为空");
        }

        try{
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(str.getBytes());

            return HexUtils.toHex(digest.digest(), upperCase);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }
}
