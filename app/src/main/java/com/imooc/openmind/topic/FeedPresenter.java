package com.imooc.openmind.topic;

import com.imooc.openmind.base.BaseData;
import com.imooc.openmind.base.BasePresenter;

import java.util.HashMap;
import java.util.List;

import rx.Observer;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class FeedPresenter extends BasePresenter {
    private FeedView mFeedView;

    public FeedPresenter(FeedView feedView) {
        mFeedView = feedView;
    }

    public void loadFeed() {
        mDataRepo.retrieveFeed(new HashMap<String, Object>())
                .subscribe(new Observer<BaseData<List<TopicModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseData<List<TopicModel>> listBaseData) {
                        mFeedView.onLoadFeeds(listBaseData);
                    }
                });
    }
}
