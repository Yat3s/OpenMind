package com.imooc.openmind;


import com.imooc.openmind.base.BaseData;
import com.imooc.openmind.network.RestClient;
import com.imooc.openmind.topic.TopicModel;
import com.imooc.openmind.topic.topicdetail.TopicDetailModel;
import com.imooc.openmind.user.AuthRequestBody;
import com.imooc.openmind.user.UserModel;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class DataRepo {

    public Observable<String> retrieveVerifyCode(AuthRequestBody body) {
        return RestClient.getInstance()
                .getAPIService()
                .retrieveVerifyCode(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserModel> authWithPhone(AuthRequestBody body) {
        return RestClient.getInstance()
                .getAPIService()
                .authWithPhone(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseData<List<TopicModel>>> retrieveFeed(Map<String, Object> param) {
        return RestClient.getInstance()
                .getAPIService()
                .retrieveFeed(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TopicDetailModel> retrieveTopicDetail(String topicId) {
        return RestClient.getInstance()
                .getAPIService()
                .retrieveTopicDetail(topicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
