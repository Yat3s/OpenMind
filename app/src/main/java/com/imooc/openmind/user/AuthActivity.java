package com.imooc.openmind.user;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imooc.openmind.MainActivity;
import com.imooc.openmind.R;
import com.imooc.openmind.base.BaseActivity;
import com.imooc.openmind.common.DisplayUtil;
import com.imooc.openmind.widget.AnimateFrameLayout;
import com.imooc.openmind.widget.KeyboardObserveLayout;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AuthActivity extends BaseActivity<AuthPresenter> implements AuthView {

    private static final String TAG = "AuthActivity";

    private static final int LINE_WIDTH = 4; // DP
    @BindView(R.id.phone_et)
    EditText mPhoneEt;
    @BindView(R.id.verify_code_et)
    EditText mVerifyCodeEt;
    @BindView(R.id.get_verify_code_btn)
    TextView mGetVerifyCodeBtn;
    @BindView(R.id.auth_btn)
    Button mAuthBtn;
    @BindView(R.id.auth_phone_layout)
    AnimateFrameLayout mAuthPhoneLayout;
    @BindView(R.id.auth_sns_layout)
    AnimateFrameLayout mAuthSnsLayout;
    @BindView(R.id.highlight_phone_layout)
    LinearLayout mHighlightPhoneLayout;
    @BindView(R.id.normal_phone_layout)
    IconTextView mNormalPhoneLayout;
    @BindView(R.id.highlight_sns_layout)
    LinearLayout mHighlightSnsLayout;
    @BindView(R.id.normal_sns_layout)
    LinearLayout mNormalSnsLayout;
    @BindView(R.id.logo_layout)
    LinearLayout mLogoLayout;
    @BindView(R.id.input_layout)
    LinearLayout mInputLayout;
    @BindView(R.id.root_layout)
    KeyboardObserveLayout mRootLayout;

    private CountDownTimer mCountDownTimer;
    private boolean mKeyboardExpand;

    private int mScreenWidth, mMinSize, mMaxHeight, mMaxWidth;

    @OnClick({R.id.get_verify_code_btn, R.id.auth_btn, R.id.auth_phone_layout, R.id.auth_sns_layout, R.id.skip_btn})
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

            case R.id.auth_phone_layout:
                mAuthPhoneLayout.animateHeight(mMaxHeight);
                mAuthPhoneLayout.animateWidth(mMaxWidth);
                mAuthSnsLayout.animateHeight(mMinSize);
                mAuthSnsLayout.animateWidth(mMinSize);

                mHighlightPhoneLayout.setVisibility(View.VISIBLE);
                mNormalPhoneLayout.setVisibility(View.GONE);
                mHighlightSnsLayout.setVisibility(View.GONE);
                mNormalSnsLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.auth_sns_layout:
                mAuthPhoneLayout.animateHeight(mMinSize);
                mAuthPhoneLayout.animateWidth(mMinSize);
                mAuthSnsLayout.animateHeight(mMaxHeight);
                mAuthSnsLayout.animateWidth(mMaxWidth);

                mHighlightPhoneLayout.setVisibility(View.GONE);
                mNormalPhoneLayout.setVisibility(View.VISIBLE);
                mHighlightSnsLayout.setVisibility(View.VISIBLE);
                mNormalSnsLayout.setVisibility(View.GONE);
                break;

            case R.id.skip_btn:
                // TODO: 14/05/2017  Add skip
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

        mScreenWidth = DisplayUtil.getScreenWidth(this);
        mMinSize = mScreenWidth / 4;
        mMaxHeight = DisplayUtil.getWindowHeight(this) - mMinSize + DisplayUtil.dip2px(this, LINE_WIDTH);
        mMaxWidth = mScreenWidth - mMinSize + DisplayUtil.dip2px(this, LINE_WIDTH);

        // Init auth UI.
        FrameLayout.LayoutParams phoneParams = (FrameLayout.LayoutParams) mAuthPhoneLayout.getLayoutParams();
        phoneParams.height = mMaxHeight;
        phoneParams.width = mMaxWidth;
        mAuthPhoneLayout.setLayoutParams(phoneParams);
        FrameLayout.LayoutParams snsParams = (FrameLayout.LayoutParams) mAuthSnsLayout.getLayoutParams();
        snsParams.height = mMinSize;
        snsParams.width = mMinSize;
        mAuthSnsLayout.setLayoutParams(snsParams);

        // Observe keyboard.
        mRootLayout.setOnKeyboardStateListener(new KeyboardObserveLayout.OnKeyboardChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyboardObserveLayout.KEYBOARD_STATE_HIDE:
                        if (!mKeyboardExpand) {
                            return;
                        }
                        mLogoLayout.animate().translationX(0);
                        mLogoLayout.animate().translationY(0);
                        mLogoLayout.animate().scaleX(1.0f);
                        mLogoLayout.animate().scaleY(1.0f);
                        mInputLayout.animate().translationY(0);
                        mKeyboardExpand = false;
                        break;
                    case KeyboardObserveLayout.KEYBOARD_STATE_SHOW:
                        if (mKeyboardExpand) {
                            return;
                        }
                        mLogoLayout.animate().translationX(-mLogoLayout.getX());
                        mLogoLayout.animate().translationY(-mLogoLayout.getY());
                        mLogoLayout.animate().scaleX(0.5f);
                        mLogoLayout.animate().scaleY(0.5f);
                        mInputLayout.animate().translationY(-mLogoLayout.getY());
                        mKeyboardExpand = true;
                        break;
                }
            }
        });


        // Configure verify code resend.
        mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mGetVerifyCodeBtn.setText(millisUntilFinished / 1000 + "秒");
                mGetVerifyCodeBtn.setEnabled(false);
            }

            @Override
            public void onFinish() {
                mGetVerifyCodeBtn.setText("重新获取");
                mGetVerifyCodeBtn.setEnabled(true);
            }
        };

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
        mCountDownTimer.start();
    }

    @Override
    public void retrieveVerifyCodeFailure() {
        showToast("获取验证码失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
        }
    }
}
