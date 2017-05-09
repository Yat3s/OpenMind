package com.imooc.openmind.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imooc.openmind.MainActivity;
import com.imooc.openmind.R;
import com.imooc.openmind.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AuthActivity extends BaseActivity<AuthPresenter> implements AuthView {
    @BindView(R.id.phone_et)
    EditText mPhoneEt;
    @BindView(R.id.verify_code_et)
    EditText mVerifyCodeEt;
    @BindView(R.id.get_verify_code_btn)
    Button mGetVerifyCodeBtn;
    @BindView(R.id.auth_btn)
    Button mAuthBtn;

    @OnClick({R.id.get_verify_code_btn, R.id.auth_btn})
    public void onClick(View view) {
        String phone = mPhoneEt.getText().toString();
        switch (view.getId()) {
            case R.id.get_verify_code_btn:
                if (TextUtils.isEmpty(phone) || phone.length() != 11) {
                    mPhoneEt.setError("请检查你的手机号是否输入正确！");
                } else {
                    mPresenter.retrieveVerifyCode(mPhoneEt.getText().toString());
                }
                break;
            case R.id.auth_btn:
                String verifyCode = mVerifyCodeEt.getText().toString();
                if (TextUtils.isEmpty(verifyCode)) {
                    mVerifyCodeEt.setError("你输入的验证码有误！");
                } else {
                    mPresenter.authWithPhone(phone, verifyCode);
                }
                break;
        }
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }

    @Override
    protected AuthPresenter providePresenter() {
        return new AuthPresenter(this);
    }

    @Override
    public void authSuccess(String id) {
        showToast("授权成功" + id);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void authFailure() {
        showToast("授权失败");
    }

    @Override
    public void retrieveVerifyCodeSuccess() {
        showToast("获取验证码成功");
    }

    @Override
    public void retrieveVerifyCodeFailure() {
        showToast("获取验证码失败");
    }

}
