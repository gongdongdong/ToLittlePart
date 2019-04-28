package com.gdd.base.mvp;

public class BasePresenter<T extends BaseView> {
    public T myView;

    public void setMyView(T myView) {
        this.myView = myView;
    }
}
