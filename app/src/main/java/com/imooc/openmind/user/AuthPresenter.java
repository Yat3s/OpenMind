package com.imooc.openmind.user;

import com.imooc.openmind.Config;
import com.imooc.openmind.base.BasePresenter;
import com.imooc.openmind.common.BCryptUtil;

import rx.Observer;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AuthPresenter extends BasePresenter {

    private AuthView mAuthView;

    public AuthPresenter(AuthView authView) {
        mAuthView = authView;
    }

    public void retrieveVerifyCode(String phone) {
        String signSourceString = phone + Config.CLIENT_SECRET + String.valueOf(System.currentTimeMillis());
        AuthRequestBody body = new AuthRequestBody();
        body.phone = phone;
        body.clientId = Config.CLIENT_ID;
        body.timestamp = String.valueOf(System.currentTimeMillis());
        body.sign = BCryptUtil.hashpw(signSourceString, BCryptUtil.gensalt());
        mCompositeSubscription
                .add(mDataRepo.retrieveVerifyCode(body)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mAuthView.retrieveVerifyCodeFailure();
                    }

                    @Override
                    public void onNext(String s) {
                        mAuthView.retrieveVerifyCodeSuccess();
                    }
                }));
    }

    public void authWithPhone(String phone, String verifyCode) {
        AuthRequestBody body = new AuthRequestBody();
        body.phone = phone;
        body.verifyCode = verifyCode;

        mCompositeSubscription
                .add(mDataRepo.authWithPhone(body)
                        .subscribe(new Observer<UserModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                mAuthView.authFailure();
                            }

                            @Override
                            public void onNext(UserModel user) {
                                mAuthView.authSuccess(user.id);
                            }
                        }));
    }
}
