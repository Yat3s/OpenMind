package com.imooc.openmind.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yat3s on 10/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class UserModel {
    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("refresh_token")
    public String refreshToken;

    @SerializedName("expires_on")
    public String expiresOn;

    public String id;
}
