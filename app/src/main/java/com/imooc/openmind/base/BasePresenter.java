package com.imooc.openmind.base;

import com.imooc.openmind.DataRepo;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Yat3s on 09/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public abstract class BasePresenter {

    protected DataRepo mDataRepo = new DataRepo();

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void subscribe() {

    }

    public void unsubscribe() {
        mCompositeSubscription.unsubscribe();
    }
}
