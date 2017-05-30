package com.imooc.openmind.topicdetail;

import android.content.Context;

import com.imooc.openmind.R;
import com.imooc.openmind.user.UserModel;
import com.yat3s.library.adapter.BaseAdapter;
import com.yat3s.library.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by Yat3s on 30/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TopicGuestAdapter extends BaseAdapter<UserModel> {
    public TopicGuestAdapter(Context context, List<UserModel> data) {
        super(context, data);
    }

    @Override
    protected void bindDataToItemView(BaseViewHolder holder, UserModel item, int position) {
        holder.setText(R.id.nickname_tv, item.nickname);
    }

    @Override
    protected int getItemViewLayoutId(int position, UserModel item) {
        return R.layout.item_topic_detail_guest;
    }
}
