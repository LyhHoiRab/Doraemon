package org.wah.doraemon.wechat;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.wah.doraemon.security.exception.UtilsException;
import org.wah.doraemon.utils.HttpClientUtils;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.wechat.response.UserInfo;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class UserInfoUtils{

    private static final String INFO_API = "https://api.weixin.qq.com/cgi-bin/user/info";
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final String DEFAULT_LANG = "zh_CN";

    public UserInfoUtils(){

    }

    public static UserInfo get(String accessToken, String openId, String lang){
        if(StringUtils.isBlank(accessToken)){
            throw new UtilsException("accessToken不能为空");
        }

        if(StringUtils.isBlank(openId)){
            throw new UtilsException("微信用户openId不能为空");
        }

        if(StringUtils.isBlank(lang)){
            lang = DEFAULT_LANG;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        params.put("lang", lang);

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        String result = HttpClientUtils.get(client, INFO_API, params, CHARSET);

        if(!StringUtils.isBlank(result)){
            UserInfo userInfo = ObjectUtils.deserialize(result, UserInfo.class);

            if(StringUtils.isBlank(userInfo.getErrCode())){
                return userInfo;
            }

            throw new UtilsException("微信用户信息查询失败[{0}:{1}]", userInfo.getErrCode(), userInfo.getErrMsg());
        }

        throw new UtilsException("微信用户信息查询失败");
    }
}
