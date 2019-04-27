package com.gdd.tolittlepart;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gdd.tolittlepart.Adapters.RecyclerViewAdapter;
import com.gdd.tolittlepart.beans.ArticleList;
import com.gdd.tolittlepart.beans.DatasBean;
import com.gdd.tolittlepart.network.RetrofitManager;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/tolittle/main")
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "testwan";
    private SwipeRefreshLayout srl_content;
    private RecyclerView rv_view;
    RecyclerViewAdapter rvAdapter;

    private int requestCount = 0;
    private List<DatasBean> showingDatas = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srl_content = findViewById(R.id.srl_content);
        rv_view = findViewById(R.id.rv_view);
        rvAdapter = new RecyclerViewAdapter(showingDatas);
        rv_view.setLayoutManager(new LinearLayoutManager(this));
        rv_view.setAdapter(rvAdapter);
        rv_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_view.addOnScrollListener(monScrollListener);
        srl_content.setOnRefreshListener(this);
        doTheRequest();
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
                    Log.e("mytest", "=========================sendMoreRequest");
//                    rvAdapter.setIsLoadMore();
                    doTheRequest();
                }
            }
        }


    };

    private void doTheRequest(){
        Observable<ArticleList> observable = RetrofitManager.getInstance().getMainPage(requestCount);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleList>() {
                    @Override
                    public void accept(ArticleList articleList) throws Exception {
                        showingDatas.addAll(articleList.getData().getDatas());
                        rvAdapter.notifyDataSetChanged();
                        srl_content.setRefreshing(false);
                    }
                });
        requestCount++;
    }

    @Override
    public void onRefresh() {
        requestCount = 0;
        showingDatas.clear();
        doTheRequest();
    }
}
