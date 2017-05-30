package com.imooc.openmind.topicdetail;

import com.imooc.openmind.base.BasePresenter;

import rx.Observer;

/**
 * Created by Yat3s on 30/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TopicDetailPresenter extends BasePresenter {

    private TopicDetailView mView;

    public TopicDetailPresenter(TopicDetailView view) {
        mView = view;
    }

    public void loadTopicDetail(String topicId) {
        mDataRepo.retrieveTopicDetail(topicId)
                .subscribe(new Observer<TopicDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TopicDetailModel baseData) {
                        mView.onLoadTopicDetailSuccess(baseData);
                    }
                });

    }
}
