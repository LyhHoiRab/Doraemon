package org.wah.doraemon.wechat;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.wah.doraemon.security.exception.UtilsException;
import org.wah.doraemon.utils.HttpClientUtils;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.wechat.response.QRcode;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class QRcodeUtils{

    private static final String QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";
    private static final String API = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public QRcodeUtils(){

    }

    /**
     * 临时字符串参数二维码
     */
    public static QRcode getByStr(String accessToken, String str, Integer expireSeconds){
        if(StringUtils.isBlank(accessToken)){
            throw new UtilsException("accessToken不能为空");
        }

        if(StringUtils.isBlank(str)){
            throw new UtilsException("临时二维码字符串参数不能为空");
        }

        if(expireSeconds == null){
            expireSeconds = 2592000;
        }

        //参数
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_str", str);

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        actionInfo.put("scene", scene);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_STR_SCENE");
        params.put("action_info", actionInfo);

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        String result = HttpClientUtils.post(client, MessageFormat.format(API, accessToken), (Object) params, CHARSET);

        if(!StringUtils.isBlank(result)){
            QRcode qrcode = ObjectUtils.deserialize(result, QRcode.class);

            if(qrcode.errCode == null){
                return qrcode;
            }

            throw new UtilsException("临时字符串参数二维码生成失败[{0}:{1}]", qrcode.getErrCode(), qrcode.getErrMsg());
        }

        throw new UtilsException("临时字符串参数二维码生成失败");
    }

    /**
     * 临时整型参数二维码
     */
    public static QRcode getByInt(String accessToken, Integer id, Integer expireSeconds){
        if(StringUtils.isBlank(accessToken)){
            throw new UtilsException("accessToken不能为空");
        }

        if(id == null){
            throw new UtilsException("临时二维码整型参数不能为空");
        }

        if(expireSeconds == null){
            expireSeconds = 2592000;
        }

        //参数
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_id", id);

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        actionInfo.put("scene", scene);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_SCENE");
        params.put("action_info", actionInfo);

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        String result = HttpClientUtils.post(client, MessageFormat.format(API, accessToken), (Object) params, CHARSET);

        if(!StringUtils.isBlank(result)){
            QRcode qrcode = ObjectUtils.deserialize(result, QRcode.class);

            if(qrcode.errCode == null){
                return qrcode;
            }

            throw new UtilsException("临时整型参数二维码生成失败[{0}:{1}]", qrcode.getErrCode(), qrcode.getErrMsg());
        }

        throw new UtilsException("临时整型参数二维码生成失败");
    }

    /**
     * 永久字符串参数二维码
     */
    public static QRcode getLimitByStr(String accessToken, String str){
        if(StringUtils.isBlank(accessToken)){
            throw new UtilsException("accessToken不能为空");
        }

        if(StringUtils.isBlank(str)){
            throw new UtilsException("永久二维码字符串参数不能为空");
        }

        //参数
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_str", str);

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        actionInfo.put("scene", scene);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action_name", "QR_LIMIT_STR_SCENE");
        params.put("action_info", actionInfo);

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        String result = HttpClientUtils.post(client, MessageFormat.format(API, accessToken), (Object) params, CHARSET);

        if(!StringUtils.isBlank(result)){
            QRcode qrcode = ObjectUtils.deserialize(result, QRcode.class);

            if(qrcode.errCode == null){
                return qrcode;
            }

            throw new UtilsException("永久字符串参数二维码生成失败[{0}:{1}]", qrcode.getErrCode(), qrcode.getErrMsg());
        }

        throw new UtilsException("永久字符串参数二维码生成失败");
    }

    /**
     * 永久整型参数二维码
     */
    public static QRcode getLimitByInt(String accessToken, Integer id){
        if(StringUtils.isBlank(accessToken)){
            throw new UtilsException("accessToken不能为空");
        }

        if(id == null){
            throw new UtilsException("永久二维码整型参数不能为空");
        }

        //参数
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_id", id);

        Map<String, Object> actionInfo = new HashMap<String, Object>();
        actionInfo.put("scene", scene);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action_name", "QR_LIMIT_SCENE");
        params.put("action_info", actionInfo);

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        String result = HttpClientUtils.post(client, MessageFormat.format(API, accessToken), (Object) params, CHARSET);

        if(!StringUtils.isBlank(result)){
            QRcode qrcode = ObjectUtils.deserialize(result, QRcode.class);

            if(qrcode.errCode == null){
                return qrcode;
            }

            throw new UtilsException("永久整型参数二维码生成失败[{0}:{1}]", qrcode.getErrCode(), qrcode.getErrMsg());
        }

        throw new UtilsException("永久整型参数二维码生成失败");
    }

    /**
     * 查询二维码图片链接
     */
    public static String getUrl(String ticket){
        if(StringUtils.isBlank(ticket)){
            throw new UtilsException("二维码ticket不能为空");
        }

        return MessageFormat.format(QRCODE_URL, ticket);
    }
}
