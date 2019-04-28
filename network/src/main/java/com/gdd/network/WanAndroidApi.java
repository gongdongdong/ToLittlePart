package com.gdd.network;


import com.gdd.beans.ArticleList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidApi {

    @GET("article/list/{count}/json")
    Observable<ArticleList> getMainPageTitles(
            @Path("count") int count
    );
}
