package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import java.security.MessageDigest;

/**
 * SHA摘要算法
 */
public class SHAUtils{

    private static final String ALGORITHM_SHA512 = "SHA-512";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_SHA1 = "SHA1";
    private static final String ALGORITHM_SHA = "SHA";

    public SHAUtils(){

    }

    private static String encode(String str, String algorithm, boolean upperCase){
        if(StringUtils.isBlank(str)){
            throw new UtilsException("摘要内容不能为空");
        }

        try{
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(str.getBytes());

            return HexUtils.toHex(digest.digest(), upperCase);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static String bySHA512(String str, boolean upperCase){
        return encode(str, ALGORITHM_SHA512, upperCase);
    }

    public static String bySHA256(String str, boolean upperCase){
        return encode(str, ALGORITHM_SHA256, upperCase);
    }

    public static String bySHA1(String str, boolean upperCase){
        return encode(str, ALGORITHM_SHA1, upperCase);
    }

    public static String bySHA(String str, boolean upperCase){
        return encode(str, ALGORITHM_SHA, upperCase);
    }
}
