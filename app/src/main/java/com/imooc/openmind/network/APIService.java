package com.imooc.openmind.network;

import com.imooc.openmind.base.BaseData;
import com.imooc.openmind.topic.TopicModel;
import com.imooc.openmind.topicdetail.TopicDetailModel;
import com.imooc.openmind.user.AuthRequestBody;
import com.imooc.openmind.user.UserModel;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Yat3s on 16/04/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public interface APIService {

    @POST("/auth/register/smsverifycode")
    Observable<String> retrieveVerifyCode(@Body AuthRequestBody body);

    @POST("/auth/signin/userpass")
    Observable<UserModel> authWithPhone(@Body AuthRequestBody body);

    @GET(API.API_FEED)
    Observable<BaseData<List<TopicModel>>> retrieveFeed(@QueryMap Map<String, Object> paramMap);

    @GET(API.API_TOPIC_DETAIL)
    Observable<TopicDetailModel> retrieveTopicDetail(@Path("topic_id") String topicId);
}
