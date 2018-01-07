package org.wah.doraemon.wechat;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;
import org.wah.doraemon.utils.SHAUtils;
import org.wah.doraemon.utils.SortUtils;

import java.util.ArrayList;
import java.util.List;

public class SignUtils{

    public SignUtils(){

    }

    /**
     * 验证
     */
    public static boolean validate(String signature, String token, String timestamp, String nonce){
        if(StringUtils.isBlank(signature)){
            throw new UtilsException("签名不能为空");
        }

        if(StringUtils.isBlank(token)){
            throw new UtilsException("微信token不能为空");
        }

        if(StringUtils.isBlank(timestamp)){
            throw new UtilsException("时间戳不能为空");
        }

        if(StringUtils.isBlank(nonce)){
            throw new UtilsException("随机字符串不能为空");
        }

        List<String> list = new ArrayList<String>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        //生成签名
        String sign = SHAUtils.bySHA1(SortUtils.toString(list, null), false);
        //验证
        return signature.equalsIgnoreCase(sign);
    }
}
