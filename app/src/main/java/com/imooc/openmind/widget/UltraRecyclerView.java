package com.imooc.openmind.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.imooc.openmind.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class UltraRecyclerView extends FrameLayout {

    @BindView(R.id.recycler_view)
    ExtendRecyclerView mRecyclerView;
    @BindView(R.id.refresh_frame)
    PtrClassicFrameLayout mRefreshFrame;

    private OnRefreshListener mOnRefreshListener;

    public UltraRecyclerView(Context context) {
        this(context, null);
    }

    public UltraRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UltraRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_ultra_recycler_view, this, true);
        ButterKnife.bind(this);

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_refresh_header, null, false);
        mRefreshFrame.setHeaderView(headerView);
        mRefreshFrame.addPtrUIHandler(new CustomRefreshHeader());
        mRefreshFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (null != mOnRefreshListener) {
                    mOnRefreshListener.onRefresh();
                }
            }
        });
    }

    public void refreshComplete() {
        mRefreshFrame.refreshComplete();
    }

    public void loadMoreComplete() {
        mRecyclerView.loadMoreComplete();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    public void setOnLoadMoreListener(ExtendRecyclerView.OnLoadMoreListener listener) {
        mRecyclerView.setOnLoadMoreListener(listener);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }


    public class CustomRefreshHeader implements PtrUIHandler {

        @Override
        public void onUIReset(PtrFrameLayout frame) {

        }

        @Override
        public void onUIRefreshPrepare(PtrFrameLayout frame) {

        }

        @Override
        public void onUIRefreshBegin(PtrFrameLayout frame) {

        }

        @Override
        public void onUIRefreshComplete(PtrFrameLayout frame) {

        }

        @Override
        public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        }
    }
}
