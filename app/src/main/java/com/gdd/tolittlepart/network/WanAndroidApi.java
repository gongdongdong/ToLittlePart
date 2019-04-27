package com.gdd.tolittlepart.network;

import io.reactivex.Observable;

import com.gdd.tolittlepart.beans.ArticleList;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidApi {

    @GET("article/list/{count}/json")
    Observable<ArticleList> getMainPageTitles(
            @Path("count") int count
    );
}
