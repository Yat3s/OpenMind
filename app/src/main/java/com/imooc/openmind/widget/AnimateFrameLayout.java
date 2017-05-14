package com.imooc.openmind.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AnimateFrameLayout extends FrameLayout {
    private static final int ANIMATE_DURATION = 1000;
    private static final String PROPERTY_CUSTOM_WIDTH = "customWidth";
    private static final String PROPERTY_CUSTOM_HEIGHT = "customHeight";

    public AnimateFrameLayout(Context context) {
        this(context, null);
    }

    public AnimateFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimateFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getCustomWidth() {
        return getLayoutParams().width;
    }

    public int getCustomHeight() {
        return getLayoutParams().height;
    }

    public void setCustomWidth(int width) {
        getLayoutParams().width = width;
        requestLayout();
    }

    public void setCustomHeight(int height) {
        getLayoutParams().height = height;
        requestLayout();
    }

    public void animateHeight(int... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, PROPERTY_CUSTOM_HEIGHT, values);
        objectAnimator.setDuration(ANIMATE_DURATION);
        objectAnimator.setInterpolator(new OvershootInterpolator(1.5f));
        objectAnimator.start();
    }

    public void animateWidth(int... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, PROPERTY_CUSTOM_WIDTH, values);
        objectAnimator.setDuration(ANIMATE_DURATION);
        objectAnimator.setInterpolator(new OvershootInterpolator(1.5f));
        objectAnimator.start();
    }

}
