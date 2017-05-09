package com.imooc.openmind.user;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public interface AuthView {

    void authSuccess(String id);

    void authFailure();

    void retrieveVerifyCodeSuccess();

    void retrieveVerifyCodeFailure();
}
