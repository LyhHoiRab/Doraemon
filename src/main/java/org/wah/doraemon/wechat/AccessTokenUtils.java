package org.wah.doraemon.wechat;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.wah.doraemon.security.exception.UtilsException;
import org.wah.doraemon.utils.HttpClientUtils;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.wechat.response.AccessToken;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenUtils{

    private static final String API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public AccessTokenUtils(){

    }

    public static AccessToken get(String appId, String secret){
        if(StringUtils.isBlank(appId)){
            throw new UtilsException("微信appId不能为空");
        }

        if(StringUtils.isBlank(secret)){
            throw new UtilsException("微信appSecret不能为空");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appid", appId);
        params.put("secret", secret);

        CloseableHttpClient client = HttpClientUtils.createHttpsClient();
        String result = HttpClientUtils.get(client, API, params, CHARSET);

        if(!StringUtils.isBlank(result)){
            AccessToken accessToken = ObjectUtils.deserialize(result, AccessToken.class);

            if(accessToken.errCode == null){
                return accessToken;
            }

            throw new UtilsException("accessToken查询失败[{0}:{1}]", accessToken.getErrCode(), accessToken.getErrMsg());
        }

        throw new UtilsException("accessToken查询失败");
    }
}
