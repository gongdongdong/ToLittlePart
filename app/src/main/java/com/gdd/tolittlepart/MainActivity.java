package com.gdd.tolittlepart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableOperator;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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

        Observable.just("one", "two", "three", "four", "five")
                .toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "FROM Observable -> Flowable " + s);
                    }
                });
        Flowable.just("one", "two", "three", "four", "five")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<String>() {
                    Subscription mSub;
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e(TAG, "onSubscribe2");
                        mSub = s;
                        mSub.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext2 " + s);
                        mSub.request(1);
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
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if("4".equals(s)){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                })
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(final String ss) throws Exception {
                        return new Publisher<String>() {
                            @Override
                            public void subscribe(Subscriber<? super String> s) {
                                s.onNext(ss + "=" + ss);
                                Log.e(TAG, "onnext -> ====== " + ss + "=" + ss);
                            }
                        };
                    }
                })
                .lift(new FlowableOperator<People, String>() {
                    @Override
                    public Subscriber<? super String> apply(final Subscriber<? super People> subscriber) throws Exception {
                        return new Subscriber<String>() {
                            Subscription mSub;
                            @Override
                            public void onSubscribe(Subscription s) {
                                mSub = s;
                                mSub.request(1);
                                Log.e(TAG, "           =============       mSub.request(1);");
                            }

                            @Override
                            public void onNext(String s) {
                                mSub.request(1);
                                Log.e(TAG, "           =============       onNext :" + s);
                                if("1=1".equals(s)){
                                    subscriber.onNext(new People(1));
                                    Log.e(TAG, "subscriber ONNEXT PEOPLE 1");
                                }

                                if("2=2".equals(s)){
                                    subscriber.onNext(new People(2));
                                    Log.e(TAG, "subscriber ONNEXT PEOPLE 2");
                                }

                                if("3=3".equals(s)){
                                    subscriber.onNext(new People(3));
                                    Log.e(TAG, "subscriber ONNEXT PEOPLE 3");
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.e(TAG, "           =============       onError ");
                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG, "LIFT : OnComplete ");
                            }
                        };
                    }
                })
                .subscribeOn(Schedulers.io())//上游io线程
                .observeOn(AndroidSchedulers.mainThread())//下游UI线程
                .subscribe(new Subscriber<People>() {

                    Subscription mSub;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        mSub = subscription;
                        mSub.request(1);//来一个吧~
                    }

                    @Override
                    public void onNext(People one) {
                        Log.e("flowable", "onNext ====>>  People : " + one.age);//来喽~~
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
