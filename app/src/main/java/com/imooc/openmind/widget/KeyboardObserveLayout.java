package com.imooc.openmind.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by Yat3s on 10/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class KeyboardObserveLayout extends RelativeLayout {
    private static final String TAG = "KeyboardObserveLayout";
    public static final byte KEYBOARD_STATE_SHOW = -3;
    public static final byte KEYBOARD_STATE_HIDE = -2;
    public static final byte KEYBOARD_STATE_INIT = -1;
    private boolean mHasInit;
    private boolean mHasKeyboard;
    private int mHeight;
    private OnKeyboardChangeListener mListener;

    public KeyboardObserveLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public KeyboardObserveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardObserveLayout(Context context) {
        super(context);
    }

    /**
     * set keyboard state listener
     */
    public void setOnKeyboardStateListener(OnKeyboardChangeListener listener) {
        mListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout: " + mHeight);
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasInit) {
            mHasInit = true;
            mHeight = bottom;
            if (mListener != null) {
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_INIT);
            }
        } else {
            mHeight = mHeight < bottom ? bottom : mHeight;
        }
        if (mHasInit && mHeight > bottom) {
            mHasKeyboard = true;
            if (mListener != null) {
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_SHOW);
            }
        }
        if (mHasInit && mHasKeyboard && mHeight == bottom) {
            mHasKeyboard = false;
            if (mListener != null) {
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_HIDE);
            }
        }
    }

    public interface OnKeyboardChangeListener {
        void onKeyBoardStateChange(int state);
    }
}