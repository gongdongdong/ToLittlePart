package com.gdd.tolittlepart.views.main.newsfragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gdd.base.component.BaseFragment;
import com.gdd.beans.ArticleList;
import com.gdd.beans.DatasBean;
import com.gdd.tolittlepart.Adapters.RecyclerViewAdapter;
import com.gdd.tolittlepart.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

public class NewsFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, NewsView {
    private static final String TAG = "test";

    @BindView(R.id.srl_content)
    SwipeRefreshLayout srl_content;
    @BindView(R.id.rv_view)
    RecyclerView rv_view;

    RecyclerViewAdapter rvAdapter;
    private List<DatasBean> showingDatas = new LinkedList<>();
    public static NewsFragment getInstance() {
        return new NewsFragment();
    }
    private NewsPresenter<NewsView> presenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        rvAdapter = new RecyclerViewAdapter(showingDatas);
        rv_view.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_view.setAdapter(rvAdapter);
        rv_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv_view.addOnScrollListener(monScrollListener);
        srl_content.setOnRefreshListener(this);
        presenter = new NewsPresenter();
        presenter.setMyView(this);
        presenter.doTheRequest();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_layout;
    }

    //本段可直接Copy，作用是监听Recycleview是否滑动到底部
    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (rvAdapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == rvAdapter.getItemCount()) {
                    //发送网络请求获取更多数据
//                    sendMoreRequest();
                    Log.e(TAG, "=========================sendMoreRequest");
//                    rvAdapter.setIsLoadMore();
                    presenter.doTheRequest();
                }
            }
        }


    };

    @Override
    public void onRefresh() {
        showingDatas.clear();
        presenter.resetRequestCount();
        presenter.doTheRequest();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideloading() {
    }

    @Override
    public void showData(ArticleList articleList) {
        showingDatas.addAll(articleList.getData().getDatas());
        rvAdapter.notifyDataSetChanged();
        srl_content.setRefreshing(false);
    }
}
