package com.imooc.openmind;


import com.imooc.openmind.network.RestClient;
import com.imooc.openmind.user.AuthRequestBody;
import com.imooc.openmind.user.UserModel;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class DataRepo {

    public Observable<String> retrieveVerifyCode(AuthRequestBody body) {
        return RestClient.getInstance()
                .getAPIService()
                .retrieveVerifyCode(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserModel> authWithPhone(AuthRequestBody body) {
        return RestClient.getInstance()
                .getAPIService()
                .authWithPhone(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
