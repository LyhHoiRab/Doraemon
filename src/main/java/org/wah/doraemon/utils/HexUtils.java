package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

/**
 * Hex - byte转换工具
 */
public class HexUtils{

    public HexUtils(){

    }

    public static String toHex(byte bytes[], boolean upperCase){
        if(bytes == null || bytes.length == 0){
            throw new UtilsException("字节数组不能为空");
        }

        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < bytes.length; i++){
            String hex = Integer.toHexString(bytes[i] & 0xFF);

            if(hex.length() == 1){
                hex = '0' + hex;
            }
            sb.append(hex);
        }

        return upperCase ? sb.toString().toUpperCase() : sb.toString().toLowerCase();
    }

    public static byte[] toByte(String hex){
        if(StringUtils.isBlank(hex)){
            throw new UtilsException("Hex字符串不能为空");
        }

        int length = hex.length() / 2;
        byte[] result = new byte[length];

        for(int i = 0; i < length; i++){
            int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }
}
