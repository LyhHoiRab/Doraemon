package utils;

import org.wah.doraemon.wechat.AccessTokenUtils;
import org.wah.doraemon.wechat.response.AccessToken;

public class AccessTokenUtilsTest {

    public static void main(String[] args){
        String appId = "wxdc53c1ccfdacf7777";
        String secret = "68d44d73ba0b3067f18cc34346733afa";

        AccessToken token = AccessTokenUtils.get(appId, secret);
        System.out.println(token.getAccessToken());
        System.out.println(token.getErrCode());
        System.out.println(token.getErrMsg());
    }
}
