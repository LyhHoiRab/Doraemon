package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AESUtils{

    private static final String ALGORITHM = "AES";
    private static final int ALGORITHM_LENGTH_128 = 128;
    private static final int ALGORITHM_LENGTH_192 = 192;
    private static final int ALGORITHM_LENGTH_256 = 256;

    public AESUtils(){

    }

    private static String encode(String str, String password, int length, boolean upperCase){
        if(StringUtils.isBlank(str)){
            throw new UtilsException("加密内容不能为空");
        }

        if(StringUtils.isBlank(password)){
            throw new UtilsException("密钥不能为空");
        }

        try{
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());

            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            kgen.init(length, random);

            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] bytes = str.getBytes();

            return HexUtils.toHex(cipher.doFinal(bytes), upperCase);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    private static String decode(String str, String password, int length){
        if(StringUtils.isBlank(str)){
            throw new UtilsException("解密内容不能为空");
        }

        if(StringUtils.isBlank(password)){
            throw new UtilsException("密钥不能为空");
        }

        try{
            byte[] bytes = HexUtils.toByte(str);

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());

            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            kgen.init(length, random);

            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(bytes));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static String encodeBy128(String str, String password, boolean upperCase){
        return encode(str, password, ALGORITHM_LENGTH_128, upperCase);
    }

    public static String decodeBy128(String str, String password){
        return decode(str, password, ALGORITHM_LENGTH_128);
    }

    public static String encodeBy192(String str, String password, boolean upperCase){
        return encode(str, password, ALGORITHM_LENGTH_192, upperCase);
    }

    public static String decodeBy192(String str, String password){
        return decode(str, password, ALGORITHM_LENGTH_192);
    }

    public static String encodeBy256(String str, String password, boolean upperCase){
        return encode(str, password, ALGORITHM_LENGTH_256, upperCase);
    }

    public static String decodeBy256(String str, String password){
        return decode(str, password, ALGORITHM_LENGTH_256);
    }
}
