package com.gdd.network;


import com.gdd.beans.ArticleList;
import com.gdd.beans.LoginRegistBean;
import com.gdd.beans.ProjectListData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WanAndroidApi {

    @GET("article/list/{count}/json")
    Observable<ArticleList> getMainPageTitles(
            @Path("count") int count
    );

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginRegistBean> doLogin(@Field("username") String username,
                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Observable<LoginRegistBean> doRegist(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("repassword") String repwd);

    @GET("project/list/{page}/json")
    Observable<ProjectListData> getProjectListData(@Path("page") int page, @Query("cid") int cid);
}
