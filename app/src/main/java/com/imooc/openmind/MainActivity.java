package com.imooc.openmind;

import com.imooc.openmind.base.BaseActivity;
import com.imooc.openmind.network.RestClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        RestClient.getInstance().getAPIService()
                .getFeed("key")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

    }
}
