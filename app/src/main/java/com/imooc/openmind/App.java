package com.imooc.openmind;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.imooc.openmind.topicdetail.TopicDetailActivity;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Iconify.with(new FontAwesomeModule());
    }

    public static void startTopicDetailActivity(Context context, String topicId) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra(TopicDetailActivity.EXTRA_TOPIC_ID, topicId);
        context.startActivity(intent);
    }
}
