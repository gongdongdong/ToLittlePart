package com.gdd.tolittlepart.views.main.newsfragment;

import com.gdd.base.mvp.BasePresenter;
import com.gdd.beans.ArticleList;
import com.gdd.events.NetworkEvent;
import com.gdd.network.RetrofitManager;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter<NewsView> extends BasePresenter {
    private int requestCount = 0;
    RetrofitManager retrofitManager;
    public NewsPresenter(){
        retrofitManager = RetrofitManager.getInstance();
    }

    public void doTheRequest() {
        retrofitManager.getMainPage(requestCount)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleList>() {
                    @Override
                    public void accept(ArticleList articleList) throws Exception {
                        myView.showData(articleList);
                        EventBus.getDefault().post(new NetworkEvent("gdd trigger"));
                    }
                });
        requestCount++;
    }

    public void resetRequestCount() {
        requestCount = 0;
    }
}
