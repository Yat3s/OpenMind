package com.imooc.openmind.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

/**
 * Created by Yat3s on 31/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AnimateLinearLayout extends LinearLayout {
    private static final int ANIMATE_DURATION = 1000;
    private static final String PROPERTY_CUSTOM_WIDTH = "customWidth";
    private static final String PROPERTY_CUSTOM_HEIGHT = "customHeight";

    public AnimateLinearLayout(Context context) {
        this(context, null);
    }

    public AnimateLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimateLinearLayout(Context context, AttributeSet attrs, int defStyle) {
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

    public void animateHeight(float values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, PROPERTY_CUSTOM_HEIGHT, (int) values);
        objectAnimator.setDuration(ANIMATE_DURATION);
        objectAnimator.setInterpolator(new OvershootInterpolator(1.5f));
        objectAnimator.start();
    }

    public void animateWidth(float values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, PROPERTY_CUSTOM_WIDTH, (int) values);
        objectAnimator.setDuration(ANIMATE_DURATION);
        objectAnimator.setInterpolator(new OvershootInterpolator(1.5f));
        objectAnimator.start();
    }
}
