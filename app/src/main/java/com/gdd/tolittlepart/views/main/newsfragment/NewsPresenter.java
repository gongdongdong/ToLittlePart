package com.gdd.tolittlepart.views.main.newsfragment;

import android.util.Log;

import com.gdd.base.mvp.BasePresenter;
import com.gdd.base.mvp.BaseView;
import com.gdd.beans.ArticleList;
import com.gdd.beans.NewsSummary;
import com.gdd.events.NetworkEvent;
import com.gdd.network.RetrofitManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter<NewsView> {
    private int requestCount = 0;
    RetrofitManager retrofitManager;
    NewsFragment newsFragment;

    public NewsPresenter(NewsFragment newsFragment) {
        retrofitManager = RetrofitManager.getInstance();
        this.newsFragment = newsFragment;
    }

    public void doTheRequest() {
//        retrofitManager.getMainPage(requestCount)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ArticleList>() {
//                    @Override
//                    public void accept(ArticleList articleList) throws Exception {
//                        myView.showData(articleList);
//                        EventBus.getDefault().post(new NetworkEvent("gdd trigger"));
//                    }
//                });

        retrofitManager.getNews(newsFragment.id, requestCount)
                .flatMap(new Function<Map<String, List<NewsSummary>>, ObservableSource<List<NewsSummary>>>() {
                    @Override
                    public ObservableSource<List<NewsSummary>> apply(Map<String, List<NewsSummary>> stringListMap) throws Exception {
                        return Flowable.fromIterable(stringListMap.values()).toObservable();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsSummary>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NewsSummary> newsSummaries) {
                        Log.e("test", "data back");
                        myView.showNewsData(newsSummaries);
                        EventBus.getDefault().post(new NetworkEvent("gdd trigger"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        myView.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        new Consumer<List<NewsSummary>>() {
//            @Override
//            public void accept(List<NewsSummary> stringListMap) throws Exception {
//                Log.e("test", "data back");
//                myView.showNewsData(stringListMap);
//                EventBus.getDefault().post(new NetworkEvent("gdd trigger"));
//            }
//        };

        requestCount++;
    }

    public void resetRequestCount() {
        requestCount = 0;
    }
}
