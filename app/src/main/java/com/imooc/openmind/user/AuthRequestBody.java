package com.imooc.openmind.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AuthRequestBody {
    public String phone;

    @SerializedName("client_id")
    public String clientId;

    public String sign;

    public String timestamp;

    public String verifyCode;
}
