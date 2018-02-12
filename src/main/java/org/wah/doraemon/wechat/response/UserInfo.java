package org.wah.doraemon.wechat.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wah.doraemon.consts.Sex;

@Data
@NoArgsConstructor
public class UserInfo implements Error{

    @SerializedName("errcode")
    private String errCode;
    @SerializedName("errmsg")
    private String errMsg;
    private Integer subscribe;
    @SerializedName("openid")
    private String openId;
    private String nickname;
    private Sex sex;
    private String language;
    private String city;
    private String province;
    private String country;
    @SerializedName("headimgurl")
    private String headImgUrl;
    @SerializedName("subscribe_time")
    private Long subscribeTime;
    @SerializedName("unionid")
    private String unionId;
    private String remark;
}
