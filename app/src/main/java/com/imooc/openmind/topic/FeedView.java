package com.imooc.openmind.topic;

import com.imooc.openmind.base.BaseData;

import java.util.List;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public interface FeedView {
    void onLoadFeeds(BaseData<List<TopicModel>> feeds);
}
