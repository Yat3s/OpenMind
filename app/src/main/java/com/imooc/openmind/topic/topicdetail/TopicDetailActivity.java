package com.imooc.openmind.topic.topicdetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.imooc.openmind.R;
import com.imooc.openmind.base.BaseActivity;
import com.imooc.openmind.widget.ParallaxScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 30/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TopicDetailActivity extends BaseActivity<TopicDetailPresenter> implements TopicDetailView {
    public static final String EXTRA_TOPIC_ID = "topic_id";
    @BindView(R.id.tag_tv)
    TextView mTagTv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.host_nickname_tv)
    TextView mHostNicknameTv;
    @BindView(R.id.abstract_tv)
    TextView mAbstractTv;
    @BindView(R.id.guest_rv)
    RecyclerView mGuestRv;
    @BindView(R.id.bookmark_rv)
    RecyclerView mBookmarkRv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.scroll_view)
    ParallaxScrollView mScrollView;
    @BindView(R.id.shadow_toolbar)
    View mShadowView;
    private boolean isToolbarTranparent;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        String topicId = getIntent().getStringExtra(EXTRA_TOPIC_ID);
        mPresenter.loadTopicDetail(topicId);

        mGuestRv.setLayoutManager(new LinearLayoutManager(this));

        final float headerHeight = getResources().getDimension(R.dimen.topic_detail_header_height);
        mScrollView.setOnScrollChangeListener(new ParallaxScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (t > headerHeight) {
                    mToolbar.setBackgroundResource(R.color.md_blue_100);
                    mScrollView.setVisibility(View.VISIBLE);
                } else {
                    mToolbar.setBackgroundResource(R.color.transparent);
                    mShadowView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected TopicDetailPresenter providePresenter() {
        return new TopicDetailPresenter(this);
    }

    @Override
    public void onLoadTopicDetailSuccess(TopicDetailModel topicModel) {
        // Configure base topic detail info.
        mTitleTv.setText(topicModel.title);
        mHostNicknameTv.setText(String.format("主持人：%s", topicModel.host.nickname));
        mTagTv.setText(topicModel.getTag());
        mAbstractTv.setText(topicModel.abstractStr);

        mGuestRv.setAdapter(new TopicGuestAdapter(this, topicModel.guests));
    }
}
