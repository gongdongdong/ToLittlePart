package com.gdd.tolittlepart.views.main.newsfragment;

import com.gdd.base.mvp.BaseView;
import com.gdd.beans.ArticleList;
import com.gdd.beans.NewsSummary;

import java.util.List;

public interface NewsView extends BaseView {
    void showNewsData(List<NewsSummary> stringListMap);
    void showData(ArticleList articleList);
    void onError();
}
