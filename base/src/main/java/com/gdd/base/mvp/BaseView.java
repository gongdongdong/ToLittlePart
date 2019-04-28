package com.gdd.base.mvp;

import com.gdd.beans.ArticleList;

public interface BaseView {
    void showLoading();
    void hideloading();
    void showData(ArticleList articleList);
}
