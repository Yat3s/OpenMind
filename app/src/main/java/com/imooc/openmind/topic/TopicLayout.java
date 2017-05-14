package com.imooc.openmind.topic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imooc.openmind.R;
import com.imooc.openmind.common.CropCircleTransformation;
import com.imooc.openmind.user.UserModel;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TopicLayout extends FrameLayout {

    @BindView(R.id.image_iv)
    ImageView mImageIv;
    @BindView(R.id.tag_tv)
    TextView mTagTv;
    @BindView(R.id.status_tv)
    TextView mStatusTv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.host_nickname_tv)
    TextView mHostNicknameTv;
    @BindView(R.id.host_intro_tv)
    TextView mHostIntroTv;
    @BindView(R.id.host_layout)
    LinearLayout mHostLayout;
    @BindView(R.id.abstract_prompt_tv)
    TextView mAbstractPromptTv;
    @BindView(R.id.abstract_tv)
    TextView mAbstractTv;
    @BindView(R.id.action_left_icon)
    IconTextView mActionLeftIcon;
    @BindView(R.id.action_left_tv)
    TextView mActionLeftTv;
    @BindView(R.id.action_left_layout)
    LinearLayout mActionLeftLayout;
    @BindView(R.id.share_icon)
    TextView mShareIcon;
    @BindView(R.id.join_icon)
    IconTextView mJoinIcon;
    @BindView(R.id.action_right_tv)
    TextView mActionRightTv;
    @BindView(R.id.action_right_layout)
    LinearLayout mActionRightLayout;
    @BindView(R.id.content_layout)
    LinearLayout mContentLayout;
    @BindView(R.id.avatar_iv)
    ImageView mAvatarIv;
    @BindView(R.id.host_identifier_tv)
    TextView mHostIdentifierTv;

    public TopicLayout(Context context) {
        this(context, null);
    }

    public TopicLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopicLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_topic, this, true);
        ButterKnife.bind(this);
    }

    public void setData(TopicModel topicModel) {
        // Configure topic.
        mTitleTv.setText(topicModel.title);
        mAbstractTv.setText(topicModel.abstractStr);
        Glide.with(getContext()).load(topicModel.getImageURL()).into(mImageIv);


        // Configure host
        UserModel host = topicModel.host;
        if (null != host) {
            mHostNicknameTv.setText(topicModel.host.nickname);
            mHostIntroTv.setText(host.vinfo);
            Glide.with(getContext())
                    .load(host.getAvatarURL())
                    .bitmapTransform(new CropCircleTransformation(getContext())).into(mAvatarIv);
        }

    }
}
