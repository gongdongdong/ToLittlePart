package com.gdd.base.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder = null;
    @Override
    final protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());
        unbinder =ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
    }

    protected abstract int getContentViewID();
    protected abstract void initData();
    protected abstract void initView(@Nullable Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
