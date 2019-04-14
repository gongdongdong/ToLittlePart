package com.gdd.tolittlepart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mytest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just("one", "two", "three", "four", "five")
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext : " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
        String[] strings = {"222222", "1111111"};
//        Flowable.fromArray(strings)
        Flowable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e(TAG, "onSubscribe2");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext2 " + s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError2");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete2");
                    }
                });


        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                for (int i = 0; i < 10; i++) {//十个客官排队
                    e.onNext(i + "");//一个个跃跃欲试啊
                }
                e.onComplete();//完成
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())//上游io线程
                .observeOn(AndroidSchedulers.mainThread())//下游UI线程
                .subscribe(new Subscriber<String>() {

                    Subscription mSub;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        mSub = subscription;
                        mSub.request(1);//来一个吧~
                    }

                    @Override
                    public void onNext(String str) {
                        Log.e("flowable", "onNext ====>>  " + str);//来喽~~
                        mSub.request(1);//那下一位客官里面请~~
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("flowable", "onError ====>>  " + t.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("flowable", "onComplete");
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
