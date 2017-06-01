package com.imooc.openmind.topic.topicstage;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.imooc.openmind.R;
import com.imooc.openmind.base.BaseActivity;
import com.imooc.openmind.widget.AnimateLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yat3s on 31/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TopicStageActivity extends BaseActivity<TopicStagePresenter> implements TopicStageView {
    private static final int INDEX_HOST_VIEW = 0x010;
    private static final int INDEX_MIDDLE_LEFT = 0x11;
    private static final int INDEX_MIDLL_RIGHT = 0x12;
    private static final int INDEX_BOTTOM_LEFT = 0x13;
    private static final int INDEX_BOTTOM_RIGHT = 0x14;
    @BindView(R.id.host_layout)
    AnimateLinearLayout mHostLayout;
    @BindView(R.id.guest_middle_left_layout)
    AnimateLinearLayout mGuestMiddleLeftLayout;
    @BindView(R.id.guest_middle_right_layout)
    AnimateLinearLayout mGuestMiddleRightLayout;
    @BindView(R.id.guest_middle_layout)
    AnimateLinearLayout mGuestMiddleLayout;
    @BindView(R.id.guest_bottom_left_layout)
    AnimateLinearLayout mGuestBottomLeftLayout;
    @BindView(R.id.guest_bottom_right_layout)
    AnimateLinearLayout mGuestBottomRightLayout;
    @BindView(R.id.guest_bottom_layout)
    AnimateLinearLayout mGuestBottomLayout;
    @BindView(R.id.contain_layout)
    LinearLayout mContainLayout;

    private float mContainWidth, mContainHeight;

    private List<AnimateLinearLayout> mAnimateLinearLayouts = new ArrayList<>();

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_stage;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);

        mContainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mContainWidth = mContainLayout.getWidth();
                mContainHeight = mContainLayout.getHeight();

                // TODO verify SDK version
                mContainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        mAnimateLinearLayouts.add(mHostLayout);
        mAnimateLinearLayouts.add(mGuestMiddleLayout);
        mAnimateLinearLayouts.add(mGuestMiddleLeftLayout);
        mAnimateLinearLayouts.add(mGuestMiddleRightLayout);
        mAnimateLinearLayouts.add(mGuestBottomLayout);
        mAnimateLinearLayouts.add(mGuestBottomLeftLayout);
        mAnimateLinearLayouts.add(mGuestBottomRightLayout);
    }

    @Override
    protected TopicStagePresenter providePresenter() {
        return new TopicStagePresenter(this);
    }

    @Override
    public void onLoadStatementSuccess() {

    }

    private float highlightHeightPercent[] = {0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f};
    private float normalHeightPercent[] = {0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f};

    private float highlightWidthPercent[] = {1f, 1f, 0.7f, 0.7f, 1f, 0.6f, 0.6f};
    private float normalWidthPercent[] = {1f, 1f, 0.3f, 0.3f, 1f, 0.4f, 0.4f};

    @OnClick({R.id.host_layout, R.id.guest_middle_left_layout, R.id.guest_middle_right_layout, R.id
            .guest_bottom_left_layout, R.id.guest_bottom_right_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.host_layout:
                mHostLayout.animateHeight(highlightHeightPercent[0] * mContainHeight);

                mGuestMiddleLayout.animateHeight(normalHeightPercent[1] * mContainHeight);
                mGuestMiddleLeftLayout.animateHeight(normalHeightPercent[2] * mContainHeight);
                mGuestMiddleRightLayout.animateHeight(normalHeightPercent[3] * mContainHeight);
                mGuestBottomLayout.animateHeight(normalHeightPercent[4] * mContainHeight);
                mGuestBottomLeftLayout.animateHeight(normalHeightPercent[5] * mContainHeight);
                mGuestBottomRightLayout.animateHeight(normalHeightPercent[6] * mContainHeight);
                break;
            case R.id.guest_middle_left_layout:
                mGuestMiddleLeftLayout.animateHeight(highlightHeightPercent[2] * mContainHeight);
                mGuestMiddleLeftLayout.animateWidth(highlightWidthPercent[2] * mContainWidth);
                mGuestMiddleRightLayout.animateHeight(highlightHeightPercent[3] * mContainHeight);
                mGuestMiddleLayout.animateHeight(highlightHeightPercent[1] * mContainHeight);

                // Animate other layout .
                mGuestMiddleRightLayout.animateWidth(normalWidthPercent[3] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                mGuestBottomLayout.animateHeight(normalHeightPercent[4] * mContainHeight);
                break;
            case R.id.guest_middle_right_layout:
                mGuestMiddleRightLayout.animateHeight(highlightHeightPercent[3] * mContainHeight);
                mGuestMiddleRightLayout.animateWidth(highlightWidthPercent[3] * mContainWidth);
                mGuestMiddleLeftLayout.animateHeight(highlightHeightPercent[2] * mContainHeight);
                mGuestMiddleLayout.animateHeight(highlightHeightPercent[1] * mContainHeight);

                // Animate other layout .
                mGuestMiddleLeftLayout.animateWidth(normalWidthPercent[2] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                mGuestBottomLayout.animateHeight(normalHeightPercent[4] * mContainHeight);
                break;
            case R.id.guest_bottom_left_layout:
                mGuestBottomLeftLayout.animateHeight(highlightHeightPercent[5] * mContainHeight);
                mGuestBottomLeftLayout.animateWidth(highlightWidthPercent[5] * mContainWidth);
                mGuestBottomRightLayout.animateHeight(highlightHeightPercent[6] * mContainHeight);
                mGuestBottomLayout.animateHeight(highlightHeightPercent[4] * mContainHeight);

                // Animate other layout .
                mGuestMiddleLayout.animateHeight(normalHeightPercent[1] * mContainHeight);
                mGuestBottomRightLayout.animateWidth(normalWidthPercent[6] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                break;
            case R.id.guest_bottom_right_layout:
                mGuestBottomRightLayout.animateHeight(highlightHeightPercent[6] * mContainHeight);
                mGuestBottomRightLayout.animateWidth(highlightWidthPercent[6] * mContainWidth);
                mGuestBottomLeftLayout.animateHeight(highlightHeightPercent[5] * mContainHeight);
                mGuestBottomLayout.animateHeight(highlightHeightPercent[4] * mContainHeight);

                // Animate other layout .
                mGuestMiddleLayout.animateHeight(normalHeightPercent[1] * mContainHeight);
                mGuestBottomLeftLayout.animateWidth(normalWidthPercent[5] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                break;
        }
    }

    /**
     * Highlight statement view {@link AnimateLinearLayout#animateHeight(float)}
     *
     * @param statementViewIndex
     */
    private void highlightStatementView(int statementViewIndex) {
        switch (statementViewIndex) {
            case INDEX_HOST_VIEW:
                mHostLayout.animateHeight(highlightHeightPercent[0] * mContainHeight);

                mGuestMiddleLayout.animateHeight(normalHeightPercent[1] * mContainHeight);
                mGuestMiddleLeftLayout.animateHeight(normalHeightPercent[2] * mContainHeight);
                mGuestMiddleRightLayout.animateHeight(normalHeightPercent[3] * mContainHeight);
                mGuestBottomLayout.animateHeight(normalHeightPercent[4] * mContainHeight);
                mGuestBottomLeftLayout.animateHeight(normalHeightPercent[5] * mContainHeight);
                mGuestBottomRightLayout.animateHeight(normalHeightPercent[6] * mContainHeight);
                break;
            case INDEX_MIDDLE_LEFT:
                mGuestMiddleLeftLayout.animateHeight(highlightHeightPercent[2] * mContainHeight);
                mGuestMiddleLeftLayout.animateWidth(highlightWidthPercent[2] * mContainWidth);
                mGuestMiddleRightLayout.animateHeight(highlightHeightPercent[3] * mContainHeight);
                mGuestMiddleLayout.animateHeight(highlightHeightPercent[1] * mContainHeight);

                // Animate other layout .
                mGuestMiddleRightLayout.animateWidth(normalWidthPercent[3] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                mGuestBottomLayout.animateHeight(normalHeightPercent[4] * mContainHeight);
                break;
            case INDEX_MIDLL_RIGHT:
                mGuestMiddleRightLayout.animateHeight(highlightHeightPercent[3] * mContainHeight);
                mGuestMiddleRightLayout.animateWidth(highlightWidthPercent[3] * mContainWidth);
                mGuestMiddleLeftLayout.animateHeight(highlightHeightPercent[2] * mContainHeight);
                mGuestMiddleLayout.animateHeight(highlightHeightPercent[1] * mContainHeight);

                // Animate other layout .
                mGuestMiddleLeftLayout.animateWidth(normalWidthPercent[2] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                mGuestBottomLayout.animateHeight(normalHeightPercent[4] * mContainHeight);
                break;
            case INDEX_BOTTOM_LEFT:
                mGuestBottomLeftLayout.animateHeight(highlightHeightPercent[5] * mContainHeight);
                mGuestBottomLeftLayout.animateWidth(highlightWidthPercent[5] * mContainWidth);
                mGuestBottomRightLayout.animateHeight(highlightHeightPercent[6] * mContainHeight);
                mGuestBottomLayout.animateHeight(highlightHeightPercent[4] * mContainHeight);

                // Animate other layout .
                mGuestMiddleLayout.animateHeight(normalHeightPercent[1] * mContainHeight);
                mGuestBottomRightLayout.animateWidth(normalWidthPercent[6] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                break;
            case INDEX_BOTTOM_RIGHT:
                mGuestBottomRightLayout.animateHeight(highlightHeightPercent[6] * mContainHeight);
                mGuestBottomRightLayout.animateWidth(highlightWidthPercent[6] * mContainWidth);
                mGuestBottomLeftLayout.animateHeight(highlightHeightPercent[5] * mContainHeight);
                mGuestBottomLayout.animateHeight(highlightHeightPercent[4] * mContainHeight);

                // Animate other layout .
                mGuestMiddleLayout.animateHeight(normalHeightPercent[1] * mContainHeight);
                mGuestBottomLeftLayout.animateWidth(normalWidthPercent[5] * mContainWidth);
                mHostLayout.animateHeight(normalHeightPercent[0] * mContainHeight);
                break;
        }
    }
}
