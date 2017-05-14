package com.imooc.openmind.topic;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import com.imooc.openmind.R;
import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class FeedAdapter extends BaseAdapter<TopicModel> {
    public FeedAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, TopicModel item, int position) {
        TopicLayout topicLayout = holder.getView(R.id.topic_layout);
        topicLayout.setData(item);

    }

    @Override
    protected int getItemViewLayoutId(int position, TopicModel item) {
        return R.layout.item_topic;
    }
}
