package com.gdd.tolittlepart.views;

import com.gdd.tolittlepart.models.entity.CookEntity.CookDetail;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/20.
 */

public interface ICookListView {

    public void onCookListUpdateRefreshSuccess(ArrayList<CookDetail> list);
    public void onCookListUpdateRefreshFaile(String msg);

    public void onCookListLoadMoreSuccess(ArrayList<CookDetail> list);
    public void onCookListLoadMoreFaile(String msg);
}
