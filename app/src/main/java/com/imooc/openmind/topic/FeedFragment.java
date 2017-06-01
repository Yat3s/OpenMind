package com.imooc.openmind.topic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.imooc.openmind.App;
import com.imooc.openmind.R;
import com.imooc.openmind.base.BaseData;
import com.imooc.openmind.base.BaseFragment;
import com.imooc.openmind.widget.ExtendRecyclerView;
import com.imooc.openmind.widget.UltraRecyclerView;
import com.yat3s.library.adapter.AnimationType;
import com.yat3s.library.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class FeedFragment extends BaseFragment<FeedPresenter> implements FeedView {

    @BindView(R.id.feed_rv)
    UltraRecyclerView mFeedRv;
    private FeedAdapter mFeedAdapter;

    private OnNavigationActionListener mOnNavigationActionListener;

    public static FeedFragment newInstance() {

        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(this, view);

        // Configure recycle view.
        mFeedAdapter = new FeedAdapter(getContext());
        mFeedRv.getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        mFeedRv.getRecyclerView().setAdapter(mFeedAdapter);
        mFeedAdapter.setItemAnimation(AnimationType.SCALE);
        mFeedAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<TopicModel>() {
            @Override
            public void onClick(View view, TopicModel item, int position) {
                App.startTopicStageActivity(getActivity(), item.id);
            }
        });

        // Configure header view.
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.header_feed, null, false);
        mFeedAdapter.addHeaderView(headerView);

        // Configure navigation tab bar scrolling.
        mFeedRv.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null != mOnNavigationActionListener) {
                    if (dy > 0) {
                        mOnNavigationActionListener.hide();
                    } else {
                        mOnNavigationActionListener.show();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mOnNavigationActionListener.show();
                }
            }
        });

        // Configure refresh & load more.
        mFeedRv.setOnLoadMoreListener(new ExtendRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                showToast("Is Load More!!!");
                mFeedRv.loadMoreComplete();
            }
        });
        mFeedRv.setOnRefreshListener(new UltraRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showToast("Is Refresh");
                mFeedRv.refreshComplete();
            }
        });


        mPresenter.loadFeed();
    }

    @Override
    protected FeedPresenter providePresenter() {
        return new FeedPresenter(this);
    }

    @Override
    public void onLoadFeeds(BaseData<List<TopicModel>> feeds) {
        mFeedAdapter.addFirstDataSet(feeds.data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnNavigationActionListener = (OnNavigationActionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnNavigationActionListener");
        }
    }


    public interface OnNavigationActionListener {
        void hide();

        void show();
    }
}
