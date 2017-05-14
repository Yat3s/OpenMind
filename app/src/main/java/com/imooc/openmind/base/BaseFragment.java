package com.imooc.openmind.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Yat3s on 15/04/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;

    protected abstract int getContentLayoutResId();

    protected abstract void init(View view);

    protected abstract P providePresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentLayoutResId(), container, false);
        mPresenter = providePresenter();

        init(rootView);
        return rootView;
    }

    protected void showToast(CharSequence message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
