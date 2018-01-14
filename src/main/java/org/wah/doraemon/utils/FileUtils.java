package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils{

    private static final String FILE_SUFFIX_SEPARATOR = ".";
    private static final int STREAM_BUFFER_LENGTH = 4096;
    private static final String ALGORITHM_MD5 = "MD5";
    private static final String ALGORITHM_SHA1 = "SHA1";
    private static final String ALGORITHM_SHA256 = "SHA-256";

    public FileUtils(){

    }

    /**
     * 查询文件MD5值
     */
    public static String getMD5(File file, boolean upperCase){
        if(file == null){
            throw new UtilsException("查询的文件不能为空");
        }

        try(FileInputStream fis = new FileInputStream(file)){
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_MD5);

            byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
            int read = fis.read(buffer, 0, STREAM_BUFFER_LENGTH);

            while(read > -1){
                digest.update(buffer, 0, read);
                read = fis.read(buffer, 0 , STREAM_BUFFER_LENGTH);
            }

            return HexUtils.toHex(digest.digest(), upperCase);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 查询文件SHA1值
     */
    public static String getSHA1(File file, boolean upperCase){
        if(file == null){
            throw new UtilsException("查询的文件不能为空");
        }

        try(FileInputStream fis = new FileInputStream(file)){
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_SHA1);

            byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
            int read = fis.read(buffer, 0, STREAM_BUFFER_LENGTH);

            while(read > -1){
                digest.update(buffer, 0, read);
                read = fis.read(buffer, 0 , STREAM_BUFFER_LENGTH);
            }

            return HexUtils.toHex(digest.digest(), upperCase);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 查询文件SHA-256值
     */
    public static String getSHA256(File file, boolean upperCase){
        if(file == null){
            throw new UtilsException("查询的文件不能为空");
        }

        try(FileInputStream fis = new FileInputStream(file)){
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_SHA256);

            byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
            int read = fis.read(buffer, 0, STREAM_BUFFER_LENGTH);

            while(read > -1){
                digest.update(buffer, 0, read);
                read = fis.read(buffer, 0 , STREAM_BUFFER_LENGTH);
            }

            return HexUtils.toHex(digest.digest(), upperCase);
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 判断文件MD5值是否相同
     */
    public static boolean isSameMD5(File first, File second){
        String first_MD5 = getMD5(first, true);
        String second_MD5 = getMD5(second, true);

        return first_MD5.equals(second_MD5);
    }

    /**
     * 判断文件SHA1值是否相同
     */
    public static boolean isSameSHA1(File first, File second){
        String first_SHA1 = getSHA1(first, true);
        String second_SHA1 = getSHA1(second, true);

        return first_SHA1.equals(second_SHA1);
    }

    /**
     * 判断文件SHA-256值是否相同
     */
    public static boolean isSameSHA256(File first, File second){
        String first_SHA256 = getSHA256(first, true);
        String second_SHA256 = getSHA256(second, true);

        return first_SHA256.equals(second_SHA256);
    }

    /**
     * 获取文件后缀
     */
    public static String getSuffix(String fullName){
        if(StringUtils.isBlank(fullName)){
            throw new UtilsException("文件名称不能为空");
        }

        int suffixSeparator = fullName.lastIndexOf(FILE_SUFFIX_SEPARATOR);

        if(suffixSeparator > -1 && suffixSeparator + 1 < fullName.length()){
            return fullName.substring(suffixSeparator + 1, fullName.length());
        }

        return "";
    }

    /**
     * 获取文件名称
     */
    public static String getName(String fullName){
        if(StringUtils.isBlank(fullName)){
            throw new UtilsException("文件名称不能为空");
        }

        int suffixSeparator = fullName.lastIndexOf(FILE_SUFFIX_SEPARATOR);

        if(suffixSeparator > -1){
            return fullName.substring(0, suffixSeparator);
        }

        return fullName;
    }
}
