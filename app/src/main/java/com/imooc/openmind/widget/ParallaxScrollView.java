package com.imooc.openmind.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.imooc.openmind.R;

import java.util.ArrayList;

public class ParallaxScrollView extends ScrollView {

    private static final int DEFAULT_PARALLAX_VIEWS = 1;
    private static final float DEFAULT_INNER_PARALLAX_FACTOR = 1.9F;
    private static final float DEFAULT_PARALLAX_FACTOR = 1.9F;
    private static final float DEFAULT_ALPHA_FACTOR = -1F;
    private int numOfParallaxViews = DEFAULT_PARALLAX_VIEWS;
    private float innerParallaxFactor = DEFAULT_PARALLAX_FACTOR;
    private float parallaxFactor = DEFAULT_PARALLAX_FACTOR;
    private float alphaFactor = DEFAULT_ALPHA_FACTOR;
    private int downX;
    private int downY;
    private int mTouchSlop;
    boolean disallowParentInterceptTouch = true;
    private OnScrollChangeListener mOnScrollChangeListener;

    private ArrayList<ParallaxView> parallaxViews = new ArrayList<>();

    public ParallaxScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ParallaxScrollView(Context context) {
        super(context);
    }

    protected void init(Context context, AttributeSet attrs) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.ParallaxScroll);
        this.parallaxFactor = typeArray.getFloat(R.styleable.ParallaxScroll_parallax_factor, DEFAULT_PARALLAX_FACTOR);
        this.alphaFactor = typeArray.getFloat(R.styleable.ParallaxScroll_alpha_factor, DEFAULT_ALPHA_FACTOR);
        this.innerParallaxFactor = typeArray.getFloat(R.styleable.ParallaxScroll_inner_parallax_factor,
                DEFAULT_INNER_PARALLAX_FACTOR);
        this.numOfParallaxViews = typeArray.getInt(R.styleable.ParallaxScroll_parallax_views_num, DEFAULT_PARALLAX_VIEWS);
        typeArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        makeViewsParallax();
    }

    private void makeViewsParallax() {
        if (getChildCount() > 0 && getChildAt(0) instanceof ViewGroup) {
            ViewGroup viewsHolder = (ViewGroup) getChildAt(0);
            int numOfParallaxViews = Math.min(this.numOfParallaxViews, viewsHolder.getChildCount());
            for (int i = 0; i < numOfParallaxViews; i++) {
                ParallaxView parallaxView = new ScrollViewParallaxItem(viewsHolder.getChildAt(i));
                parallaxViews.add(parallaxView);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float parallax = parallaxFactor;
        float alpha = alphaFactor;
        for (ParallaxView parallaxView : parallaxViews) {
            parallaxView.setOffset((float) t / parallax);
            parallax *= innerParallaxFactor;
            if (alpha != DEFAULT_ALPHA_FACTOR) {
                float fixedAlpha = (t <= 0) ? 1 : (100 / ((float) t * alpha));
                parallaxView.setAlpha(fixedAlpha);
                alpha /= alphaFactor;
            }
            parallaxView.animateNow();
        }

        if (null != mOnScrollChangeListener) {
            mOnScrollChangeListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY() - downY;
                if (Math.abs(moveY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                disallowParentInterceptTouch = true;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getRawX() - downX;
                int moveY = (int) ev.getRawY() - downY;
                if (moveY >= 0 & getScrollY() <= 0 || Math.abs(moveX) > Math.abs(moveY)) {
                    disallowParentInterceptTouch = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
                break;
        }
        getParent().requestDisallowInterceptTouchEvent(disallowParentInterceptTouch);
        return super.dispatchTouchEvent(ev);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener listener) {
        mOnScrollChangeListener = listener;
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    protected class ScrollViewParallaxItem extends ParallaxView {

        public ScrollViewParallaxItem(View view) {
            super(view);
        }

        @Override
        protected void translatePreICS(View view, float offset) {
            view.offsetTopAndBottom((int) offset - lastOffset);
            lastOffset = (int) offset;
        }
    }
}
