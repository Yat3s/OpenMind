package com.imooc.openmind.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class ExtendRecyclerView extends RecyclerView {

    private static final int VISIBLE_THRESHOLD = 3;

    private boolean isLoadMore;

    public ExtendRecyclerView(Context context) {
        this(context, null);
    }

    public ExtendRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        if (null == onLoadMoreListener) {
            return;
        }

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                            .findLastVisibleItemPosition();
                    int totalItemCount = recyclerView.getLayoutManager().getItemCount();

                    if (lastVisibleItemPosition >= totalItemCount - VISIBLE_THRESHOLD && dy > 0 && !isLoadMore) {
                        isLoadMore = true;
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void loadMoreComplete() {
        isLoadMore = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
