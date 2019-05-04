package com.gdd.network;

import com.gdd.beans.NewsSummary;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;


public interface NewsService {
//    @GET("nc/article/{type}/{id}/{startPage}-20.html")
//    Observable<Map<String, List<NewsSummary>>> getNewsList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type,
//            @Path("id") String id,
//            @Path("startPage") int startPage);

    @GET("nc/article/headline/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Path("id") String id,
            @Path("startPage") int startPage);
}
