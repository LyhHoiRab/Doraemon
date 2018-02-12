package org.wah.doraemon.wechat.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessToken implements Error{

    @SerializedName("errcode")
    private String errCode;
    @SerializedName("errmsg")
    private String errMsg;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Integer expiresIn;
}
