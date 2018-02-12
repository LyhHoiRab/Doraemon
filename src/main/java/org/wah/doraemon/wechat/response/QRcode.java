package org.wah.doraemon.wechat.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QRcode implements Error{

    @SerializedName("errcode")
    private String errCode;
    @SerializedName("errmsg")
    private String errMsg;
    private String ticket;
    @SerializedName("expire_seconds")
    private Integer expireSeconds;
    private String url;
}
