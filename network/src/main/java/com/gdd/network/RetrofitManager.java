package com.gdd.network;

import android.util.Log;

import com.gdd.beans.ArticleList;
import com.gdd.beans.LoginRegistBean;
import com.gdd.beans.NewsSummary;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String TAG = "testwan";

    private static RetrofitManager instance = null;
    private Retrofit retrofit = null;
    private OkHttpClient sOkHttpClient;
    private WanAndroidApi wanService;
    private NewsService newsService;

    private RetrofitManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        wanService = retrofit.create(WanAndroidApi.class);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.NETEAST_HOST)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        newsService = retrofit.create(NewsService.class);
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
//                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
//                            .cache(cache)
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
//                            .addInterceptor(mRewriteCacheControlInterceptor)
//                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }

    public static RetrofitManager getInstance(){
        if(instance == null){
            instance = new RetrofitManager();
        }
        return instance;
    }

    public Observable<ArticleList> getMainPage(int count) {
        return wanService.getMainPageTitles(count);
    }

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.e(TAG,String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.e(TAG,String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            int code = response.code();
            return response;
        }
    };

    public Observable<LoginRegistBean> doLogin(String username, String password) {
        return wanService.doLogin(username, password);
    }

    public  Observable<LoginRegistBean> doRegist(String username, String password, String repwd) {
        return wanService.doRegist(username, password, repwd);
    }

    public  Observable<Map<String, List<NewsSummary>>> getNews(String id, int count) {
        return newsService.getNewsList(id, count * 20);
    }
}
